package com.qh.water_management.component.shiro;

import com.qh.water_management.modules.common.utils.ShiroUtils;
import com.qh.water_management.modules.entity.sys.User;
import com.qh.water_management.modules.service.sys.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * @Author: qh
 * @Date: 2018/11/15 16:19
 * @Description: Shiro认证
 * shiro的主要模块分别就是授权和认证和会话管理。
 * 从 Realm 得到用户相应的角色/权限进行验证用户是否能进行操作,可以把 Realm 看成 DataSource，即安全数据源。
 * 认证就是验证用户。比如用户登录的时候验证账号密码是否正确。
 * 我们可以把对登录的验证交给shiro。我们执行要查询相应的用户信息，并传给shiro。
 * 域，shiro从realm获取安全数据（如用户，角色，权限），就是说 SecurityManager 要验证用户身份，
 * 那么它需要从 Realm 获取相应的用户进行比较以确定用户身份是否合法；也需要从 Realm 得到用户相应的角色 / 权限进行验证用户是否能进行操作；可以把 Realm 看成 DataSource，即安全数据源。
 * 也就是说对于我们而言，最简单的一个 Shiro 应用：
 * 1、应用代码通过 Subject 来进行认证和授权，而 Subject 又委托给 SecurityManager；
 * 2、我们需要给 Shiro 的 SecurityManager 注入 Realm，从而让 SecurityManager 能得到合法的用户及其权限进行判断。
 * Shiro 不提供维护用户 / 权限，而是通过 Realm 让开发人员自己注入。
 * 以后一般继承 AuthorizingRealm（授权）即可；其继承了 AuthenticatingRealm（即身份验证），而且也间接继承了 CachingRealm（带有缓存实现）。
 *
 */
public class AuthRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(AuthRealm.class);

    @Autowired
    private UserService userService;

    /**
     * 授权,也叫访问控制，即在应用中控制谁能访问哪些资源（如访问页面/编辑数据/页面操作等）
     * principals：身份，即主体的标识属性，可以是任何东西，如用户名、邮箱等，唯一即可。一个主体可以有多个 principals，但只有一个 Primary principals，一般是用户名 / 密码 / 手机号
     * credentials：证明 / 凭证，即只有主体知道的安全值，如密码 / 数字证书等。
     * 因为我们可以在Shiro中同时配置多个Realm，所以呢身份信息可能就有多个；因此其提供了PrincipalCollection用于聚合这些身份信息
     * @param principals
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
 //       User user = (User) principals.getPrimaryPrincipal();
        Object principal = principals.getPrimaryPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            if (user != null) {
                //根据用户id查询该用户所有的角色,并加入到shiro的SimpleAuthorizationInfo
                //根据用户ID查询角色（role），放入到Authorization里
            }
        }
        return info;
    }

    /**
     * 认证信息，主要针对用户登录
     * 如果身份验证失败请捕获AuthenticationException或其子类
     * 常见的如： DisabledAccountException（禁用的帐号）、LockedAccountException（锁定的帐号）、UnknownAccountException（错误的帐号）、ExcessiveAttemptsException（登录失败次数过多）、IncorrectCredentialsException （错误的凭证）、ExpiredCredentialsException（过期的凭证）等，具体请查看其继承关系
     * @param token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userLoginName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        User user = userService.findUserByLoginName(userLoginName);
        if (user == null) {
            throw new AuthenticationException("账号密码错误");
        }
        if (com.qh.modules.common.common.Constant.ABLE_STATUS.NO.getValue().equals(user.getStatus())) {
            throw new AuthenticationException("账号被禁用，请联系管理员!");
        }
        String md5Pwd = new Md5Hash(password, userLoginName).toHex();
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
        //登录成功后使用Subject.getSession()即可获取会话；其等价于Subject.getSession(true)，
        // 即如果当前没有创建Session对象会创建一个；另外Subject.getSession(false)，如果当前没有创建Session则返回null（不过默认情况下如果启用会话存储功能的话在创建Subject时会主动创建一个Session）
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("userSession", user);
        session.setAttribute("userSessionId", user.getId());
        return simpleAuthenticationInfo;
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * 所以我们需要修改下doGetAuthenticationInfo中的代码;
     * HashedCredentialsMatcher实现密码验证服务
     * Shiro提供了CredentialsMatcher的散列实现HashedCredentialsMatcher，和之前的PasswordMatcher不同的是，它只用于密码验证，且可以提供自己的盐，而不是随机生成盐，且生成密码散列值的算法需要自己写，因为能提供自己的盐。
     *
     * @param credentialsMatcher
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher  = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName(ShiroUtils.algorithmName);  //散列算法:这里使用MD5算法;
        shaCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);    //散列的次数，比如散列两次，相当于 md5(md5(""));
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }

     /*1.LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。主要是AuthorizingRealm类的子类，以及EhCacheManager类。
    2.HashedCredentialsMatcher，这个类是为了对密码进行编码的，防止密码在数据库里明码保存，当然在登陆认证的生活，这个类也负责对form里输入的密码进行编码。
    3.ShiroRealm，这是个自定义的认证类，继承自AuthorizingRealm，负责用户的认证和权限的处理，可以参考JdbcRealm的实现。
    4.EhCacheManager，缓存管理，用户登陆成功后，把用户信息和权限信息缓存起来，然后每次用户请求时，放入用户的session中，如果不设置这个bean，每个请求都会查询一次数据库。
    5.SecurityManager，权限管理，这个类组合了登陆，登出，权限，session的处理，是个比较重要的类。
    6.ShiroFilterFactoryBean，是个factorybean，为了生成ShiroFilter。它主要保持了三项数据，securityManager，filters，filterChainDefinitionManager。
    7.DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。
    8.AuthorizationAttributeSourceAdvisor，shiro里实现的Advisor类，内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法。*/


    /*
    Shiro提供SessionDAO用于会话的CRUD，即DAO（Data Access Object）模式实现：
    //如DefaultSessionManager在创建完session后会调用该方法；如保存到关系数据库/文件系统/NoSQL数据库；即可以实现会话的持久化；返回会话ID；主要此处返回的ID.equals(session.getId())；
    Serializable create(Session session);
    //根据会话ID获取会话
    Session readSession(Serializable sessionId) throws UnknownSessionException;
    //更新会话；如更新会话最后访问时间/停止会话/设置超时时间/设置移除属性等会调用
    void update(Session session) throws UnknownSessionException;
    //删除会话；当会话过期/会话停止（如用户退出时）会调用
    void delete(Session session);
    //获取当前所有活跃用户，如果用户量多此方法影响性能
    Collection<Session> getActiveSessions();
    */

    /**
     * springboot集成shiro时认证出现报错无非就是密码不匹配
     *
     * 可能发生的原因：
     *
     * 1. 前端传的密码是明文，而后台存储的是hash值，导致先后台不匹配报错
     * 如果数据库储存的密码是加密的 那么要 从前端获取密码后，在Java里将其转换成hash值
     * 2. 如果java已经将其加密，但仍然报错那就去ShiroConfig里面看凭证匹配器是不是set了hashIterations(2)
     *
     */
    /*@Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(ShiroUtils.algorithmName);
        hashedCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
        return hashedCredentialsMatcher;
    }*/
}
