package com.matrix.realm;

import com.matrix.entity.Blogger;
import com.matrix.service.IBloggerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/23 19:57
 * @github https://github.com/Javen-Liu
 */
public class BlogRealm extends AuthorizingRealm {
    @Resource
    private IBloggerService bloggerService;

    /**
     * 获取授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 登陆验证
     * @param authenticationToken 基于用户名与密码的令牌
     * @return 返回验证信息
     * @throws AuthenticationException 如果验证失败，则抛出该异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //从令牌中取出用户名
        String userName = (String) authenticationToken.getPrincipal();
        //通过shiro来进行验证账号和密码
        Blogger blogger = bloggerService.findBloggerByUserName(userName);
        if (blogger != null) {
            SecurityUtils.getSubject().getSession().setAttribute("currentUser",blogger);
            return new SimpleAuthenticationInfo(blogger.getUserName(),blogger.getPassword(),getName());
        }
        return null;
    }
}
