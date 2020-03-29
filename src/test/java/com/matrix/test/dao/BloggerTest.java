package com.matrix.test.dao;

import com.matrix.dao.IBloggerDao;
import com.matrix.entity.Blogger;
import com.matrix.service.IBloggerService;
import com.matrix.test.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/28 22:25
 * @github https://github.com/Javen-Liu
 */
public class BloggerTest extends BaseTest {
    @Resource
    private IBloggerDao bloggerDao;

    @Test
    public void updateTest(){
        Blogger blogger = bloggerDao.findBloggerByUserId(1);
        blogger.setProfile("132");
        bloggerDao.updateBlogger(blogger);
    }
}
