package com.matrix;

import com.matrix.entity.Blogger;
import com.matrix.service.IBloggerService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/30 21:08
 * @github https://github.com/Javen-Liu
 * 初始化配置信息
 */
@Component
public class InitComponent implements ServletContextListener, ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext application = servletContextEvent.getServletContext();
        IBloggerService bloggerService = (IBloggerService) applicationContext.getBean("bloggerService");
        Blogger blogger = bloggerService.findBloggerByUserId(1);
        application.setAttribute("blogger",blogger);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        InitComponent.applicationContext = applicationContext;
    }
}
