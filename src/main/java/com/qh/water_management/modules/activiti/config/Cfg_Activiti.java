package com.qh.water_management.modules.activiti.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.qh.water_management.config.druid.ActivitiDataSourceProperties;
import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @Author: qh
 * @Date: 2019/1/3 16:47
 * @Description: activiti工作流配置,Activiti与spring整合xml配置文件
 */
@Configuration
public class Cfg_Activiti {

   /* @Autowired
    private ActivitiDataSourceProperties activitiDataSourceProperties;


    @Bean
    @Primary
    public DataSource activitiDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(activitiDataSourceProperties.getUrl());
        druidDataSource.setDriverClassName(activitiDataSourceProperties.getDriverClassName());
        druidDataSource.setPassword(activitiDataSourceProperties.getPassword());
        druidDataSource.setUsername(activitiDataSourceProperties.getUsername());
        return druidDataSource;
    }*/


    /**
     * 工作流引擎配置
     *
     * 流程配置，与Spring整合采用SpringProcessEngineConfiguration这个类实现
     * Activiti的数据库名称都以ACT_开头。第二部分是表的用例的双字符标识。此用例也将大致匹配服务API
     * ACT_RE_ *：RE代表存储库。具有此前缀的表包含静态信息，例如流程定义和流程资源（图像，规则等）。
     * ACT_RU_ *：RU代表运行时。这些是包含流程实例，用户任务，变量，作业等的运行时数据的运行时表.Activiti仅在流程实例执行期间存储运行时数据，并在流程实例结束时删除记录。这使运行时表保持小而快
     * ACT_ID_ *：ID代表身份。这些表包含身份信息，例如用户，组等。
     * ACT_HI_ *：HI代表历史。这些是包含历史数据的表，例如过去的流程实例，变量，任务等。
     * ACT_GE_ *：一般数据，用于各种用例。
     * @param secondaryDataSource 数据源
     * @param transactionManager 数据源的事务管理器
     * @return
     */
    @Bean
    @Primary
    public ProcessEngineConfiguration processEngineConfiguration(DataSource secondaryDataSource, PlatformTransactionManager transactionManager) {
        SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
        //配置Activiti引擎将使用的数据库
        //基准测试表明，在处理大量并发请求时，MyBatis连接池不是最有效或最有弹性的。因此，建议我们使用javax.sql.DataSource实现并将其注入流程引擎配置（例如DBCP，C3P0，Hikari，Tomcat连接池等）
        processEngineConfiguration.setDataSource(secondaryDataSource);
        //允许设置策略以在流程引擎启动和关闭时处理数据库模式
        //databaseSchemaUpdata:设置流程引擎启动和关闭时如何处理数据库表：
        //false（默认值）：在创建流程引擎时检查数据库表的版本和依赖库的版本，如果版本不匹配则抛出异常
        //true：构建流程引擎时，将执行检查，并在必要时执行架构更新。如果表不存在，则创建它
        //create-drop：在创建流程引擎时创建数据库表，并在关闭流程引擎时删除这些表。
        // 默认情况下，每次创建流程引擎时都会执行版本检查。这通常在您的应用程序或Activiti Web应用程序的启动时发生一次。如果Activiti库注意到库版本与Activiti数据库表的版本之间存在差异，则会引发异常。
        processEngineConfiguration.setDatabaseSchemaUpdate("true");
        //通常不需要指定此属性，因为它是从数据库连接元数据中自动分析的。只应在自动检测失败的情况下指定。可能的值：{h2，mysql，oracle，postgres，mssql，db2}。此设置将确定将使用哪些创建/删除脚本和查询。
        processEngineConfiguration.setDatabaseType("mysql");
        //属性asyncExecutorActivate指示Activiti Engine在启动时启动Async执行程序
      //  processEngineConfiguration.setAsyncExecutorActivate(false);
        //启用Job执行器,JobExecutor是管理一系列线程的组件，可以触发定时器（也包含后续的异步消息）,
        //默认，JobExecutor在流程引擎启动时就会激活。 如果不想在流程引擎启动后自动激活JobExecutor，可以设置false
        processEngineConfiguration.setJobExecutorActivate(false);
        processEngineConfiguration.setTransactionManager(transactionManager);
        //邮件服务器配置
        /*processEngineConfiguration.setMailServerHost("mail.my-corp.com");
        processEngineConfiguration.setMailServerPort(5025);*/

        //流程图字体
        processEngineConfiguration.setActivityFontName("宋体");
        processEngineConfiguration.setAnnotationFontName("宋体");
        processEngineConfiguration.setLabelFontName("宋体");

        processEngineConfiguration.setProcessDiagramGenerator(new DefaultProcessDiagramGenerator());
        return processEngineConfiguration;
    }


    /**
     * 工作流引擎
     * 在Activiti中，在创建核心的流程引擎对象时会自动建表。如果程序正常执行，mysql会自动建库，然后创建23张表
     * 流程引擎，与spring整合使用factoryBean
     * @param processEngineConfiguration
     * @return
     */
    @Bean
    public ProcessEngineFactoryBean processEngineFactoryBean(ProcessEngineConfiguration processEngineConfiguration) {
        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
        return processEngineFactoryBean;
    }

    //八大接口(原生服务)

    /**
     * RepositoryService可能是使用Activiti引擎时所需的第一个服务。此服务提供用于管理和操作部署和流程定义的操作。
     * 流程定义是BPMN 2.0流程的Java对应物。它表示过程的每个步骤的结构和行为。部署是Activiti引擎中的包装单位。
     * 部署可以包含多个BPMN 2.0 xml文件和任何其他资源。选择包含在一个部署中的内容取决于开发人员。它可以从单个流程BPMN 2.0 xml文件
     * 到整个流程包和相关资源（例如，部署hr-processes可以包含与hr流程相关的所有内容）。 RepositoryService允许部署此类包。部署部署
     * 意味着将其上载到引擎，在引擎中，所有进程在存储到数据库之前都会进行检查和解析。从那时起，系统就知道部署，现在可以启动部署中包含的任何进程。
     * @param processEngine
     * @return
     */
    @Bean
    public RepositoryService repositoryService(ProcessEngine processEngine) {
        return processEngine.getRepositoryService();
    }

    /**
     * RuntimeService恰恰相反。它涉及启动流程定义的新流程实例。如上所述，流程定义定义流程中不同步骤的结构和行为。流程实例是这种流程定义的一次执行。
     * 对于每个流程定义，通常会有许多实例同时运行。 RuntimeService也是用于检索和存储流程变量的服务。这是特定于给定流程实例的数据，并且可以由流程
     * 中的各种构造使用（例如，专用网关通常使用流程变量来确定选择哪个路径来继续该流程）。 Runtimeservice还允许查询流程实例和执行。执行是
     * BPMN 2.0的“令牌”概念的表示。基本上，执行是指向流程实例当前所在位置的指针。最后，只要流程实例正在等待外部触发器并且需要继续该流程，
     * 就会使用RuntimeService。流程实例可以具有各种等待状态，并且该服务包含各种操作以向实例发信号通知接收到外部触发并且可以继续流程实例。
     * @param processEngine
     * @return
     */
    @Bean
    public RuntimeService runtimeService(ProcessEngine processEngine) {
        return processEngine.getRuntimeService();
    }

    @Bean
    public TaskService taskService(ProcessEngine processEngine) {
        return processEngine.getTaskService();
    }

    /**
     * HistoryService公开Activiti引擎收集的所有历史数据。执行进程时，引擎可以保存大量数据（这是可配置的），
     * 例如流程实例启动时间，执行哪些任务，完成任务需要多长时间，每个流程实例中遵循哪条路径等等该服务主要公开查询功能以访问此数据。
     * @param processEngine
     * @return
     */
    @Bean
    public HistoryService historyService(ProcessEngine processEngine) {
        return processEngine.getHistoryService();
    }

    /**
     * FormService是一项可选服务。这意味着Activiti可以在没有牺牲任何功能的情况下完美地使用它。该服务引入了开始表单和任务表单的概念。
     * 开始表单是在启动流程实例之前向用户显示的表单，而任务表单是用户想要填写表单时显示的表单。
     * Activiti允许在BPMN 2.0流程定义中定义这些表单。此服务以简单的方式公开此数据。但同样，这是可选的，因为表单不需要嵌入到流程定义中。
     * @param processEngine
     * @return
     */
    @Bean
    public FormService formService(ProcessEngine processEngine) {
        return processEngine.getFormService();
    }

    /**
     * IdentityService非常简单。它允许组和用户的管理（创建，更新，删除，查询......）。重要的是要了解Activiti实际上不会在运行时对用户进行任何检查。
     * 例如，可以将任务分配给任何用户，但引擎不会验证该用户是否为系统所知。这是因为Activiti引擎还可以与LDAP，Active Directory等服务结合使用。
     * @param processEngine
     * @return
     */
    @Bean
    public IdentityService identityService(ProcessEngine processEngine) {
        return processEngine.getIdentityService();
    }

    /**
     * 使用Activiti编写自定义应用程序时，通常不需要ManagementService。它允许检索有关数据库表和表元数据的信息。
     * 此外，它还公开了作业的查询功能和管理操作。
     * 在Activiti中使用作业来处理各种事物，例如计时器，异步延续，延迟暂停/激活等。稍后，将更详细地讨论这些主题。
     * @param processEngine
     * @return
     */
    @Bean
    public ManagementService managementService(ProcessEngine processEngine) {
        return processEngine.getManagementService();
    }

    /**
     * DynamicBpmnService可用于更改流程定义的一部分，而无需重新部署它。例如，您可以更改流程定义中用户任务的受理人定义，或更改服务任务的类名称。
     * @param processEngine
     * @return
     */
    @Bean
    public DynamicBpmnService dynamicBpmnService(ProcessEngine processEngine) {
        return processEngine.getDynamicBpmnService();
    }

    //八大接口 end

}
