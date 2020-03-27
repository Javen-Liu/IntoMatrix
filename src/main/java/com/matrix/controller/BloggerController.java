package com.matrix.controller;

import com.matrix.entity.Blogger;
import com.matrix.utils.CryptographyUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/24 14:58
 * @github https://github.com/Javen-Liu
 * 博客登陆控制器
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController {
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam(name = "loginName") String username,
                        @RequestParam(name = "password") String password,
                        HttpServletRequest request,
                        HttpServletResponse response) throws IOException {
        String encryptedPassword = CryptographyUtils.md5Encryption(password,CryptographyUtils.SALT);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,encryptedPassword);
        try{
            subject.login(token);
            response.sendRedirect(request.getContextPath()+"/pages/admin/main.jsp");
        }catch (Exception e){
            Blogger blogger = new Blogger();
            blogger.setUserName(username);
            blogger.setPassword(password);
            request.setAttribute("blogger",blogger);
            request.setAttribute("errorInfo","用户名密码错误");
        }
        return "login";
    }
}
