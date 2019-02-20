package com.qh.water_management.modules.controller.sys;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.qh.water_management.modules.common.controller.BaseController;
import com.qh.water_management.modules.common.utils.Result;
import com.qh.water_management.modules.common.utils.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author: qh
 * @Date: 2018/11/16 10:30
 * @Description: 登录controller
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    private Producer captchaProducer;

    @GetMapping(value = "/")
    public String login() {
     return "thymeleaf/login";
    }

    /**
     * 获取登录验证码
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/login/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set to expire far in the past.
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");

        // return a jpeg
        response.setContentType("image/jpeg");

        // create the text for the image
        String capText = captchaProducer.createText();

        // store the text in the session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        // create the image with the text
        BufferedImage bi = captchaProducer.createImage(capText);

        ServletOutputStream out = response.getOutputStream();

        // write the data out
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    /**
     * 登录
     * @param username
     * @param password
     * @param captcha
     * @param isRememberMe
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping(value = "/login")
    public Result login(String username, String password, String captcha, boolean isRememberMe) throws IOException {
        /*String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if (!captcha.equalsIgnoreCase(kaptcha)) {
            return Result.error("验证码不正确");
        }*/

        try {
            //得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(isRememberMe);
            //登录，即身份验证,其会自动委托给 SecurityManager.login 方法进行登录；
            //调用subject.login方法进行登录，其会自动委托给SecurityManager.login方法进行登录；
            subject.login(token);
        } catch (UnknownAccountException e) {
            return Result.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return Result.error(" 账号或密码不正确");
        } catch (LockedAccountException e) {
            return Result.error("账号已被锁定,请联系管理员");
        } catch (AuthenticationException e) {
            return Result.error("账户验证失败");
        }
        return Result.ok();
    }

    /**
     * 方法logOut的功能描述：退出登录
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        //其会自动委托给 SecurityManager.logout 方法退出。
        ShiroUtils.logout();
        return "redirect:/";
    }

    /**
     * 首页视图
     * @return
     */
    @GetMapping(value = "/index")
    public String index() {
        return "thymeleaf/index";
    }
}
