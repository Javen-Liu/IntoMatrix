package com.matrix.controller;

import com.matrix.entity.Blogger;
import com.matrix.service.IBloggerService;
import com.matrix.utils.CryptographyUtils;
import com.matrix.utils.ResponseUtils;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
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
    @Resource
    private IBloggerService bloggerService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam(name = "loginName") String username,
                        @RequestParam(name = "password") String password,
                        HttpServletRequest request,
                        HttpServletResponse response) {
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

    @RequestMapping("/user_info")
    public void userInfo(@RequestParam("id") String id,
                         HttpServletResponse response) throws IOException {
        Blogger blogger = bloggerService.findBloggerByUserId(Integer.parseInt(id));
        JSONObject result = new JSONObject();
        if(blogger != null){
            result.put("success", Boolean.TRUE);
            result.put("user",blogger);
        }else {
            result.put("error", Boolean.FALSE);
        }
        ResponseUtils.write(response, result);
    }

    @RequestMapping("/update")
    public void update(@RequestParam("id") String id,
                       @RequestParam("profile") String profile,
                       @RequestParam("nickName") String nickName,
                       @RequestParam("sign") String sign,
                       HttpServletResponse response) throws IOException {
        Blogger blogger = bloggerService.findBloggerByUserId(Integer.parseInt(id));
        blogger.setProfile(profile);
        blogger.setNickName(nickName);
        blogger.setSign(sign);
        Integer flag = bloggerService.updateBlogger(blogger);
        JSONObject result = new JSONObject();
        if (flag > 0) {
            result.put("success", Boolean.TRUE);
        } else {
            result.put("success", Boolean.FALSE);
        }
        ResponseUtils.write(response, result);
    }

    @RequestMapping("/verifyPassword")
    public String verify(@RequestParam("old-password") String oldPassword,
                         @RequestParam("id") String id,
                         HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        Blogger blogger = bloggerService.findBloggerByUserId(Integer.parseInt(id));
        if (blogger.getPassword().equals(CryptographyUtils.md5Encryption(oldPassword, CryptographyUtils.SALT))) {
            return "admin/blogger/changePasswordManager";
        } else {
            request.setAttribute("old-password",oldPassword);
            request.setAttribute("errorInfo","密码错误");
            return "admin/blogger/verifyPasswordManager";
        }
    }

    @RequestMapping("/changePassword")
    public void change(@RequestParam("password") String password,
                       @RequestParam("id") String id,
                       HttpServletResponse response) throws IOException {
        String newPassword = CryptographyUtils.md5Encryption(password, CryptographyUtils.SALT);
        Blogger blogger = bloggerService.findBloggerByUserId(Integer.parseInt(id));
        JSONObject result = new JSONObject();
        if (!blogger.getPassword().equals(newPassword)) {
            blogger.setPassword(CryptographyUtils.md5Encryption(password, CryptographyUtils.SALT));
            Integer flag = bloggerService.updateBlogger(blogger);
            if (flag > 0) {
                result.put("success", Boolean.TRUE);
            } else {
                result.put("success", Boolean.FALSE);
            }
        } else {
            result.put("success", Boolean.FALSE);
            result.put("errorInfo","新密码不能与原密码相同");
        }
        ResponseUtils.write(response, result);
    }

    @RequestMapping("/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "login";
    }
}
