package com.matrix.service.impl;

import com.matrix.dao.IBloggerDao;
import com.matrix.entity.Blogger;
import com.matrix.service.IBloggerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/24 15:51
 * @github https://github.com/Javen-Liu
 * 博主账户服务层实现类
 */
@Service("bloggerService")
public class BloggerServiceImpl implements IBloggerService {
    @Resource
    private IBloggerDao bloggerDao;

    @Override
    public Blogger findBloggerByUserName(String username) {
        return bloggerDao.findBloggerByUserName(username);
    }
}
