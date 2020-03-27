package com.matrix.service;

import com.matrix.entity.Blogger;
import org.apache.ibatis.annotations.Param;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/24 15:49
 * @github https://github.com/Javen-Liu
 * 博主账户服务层接口
 */
public interface IBloggerService {
    /**
     * 通过username用户名来调用dao层接口查询Blogger博主信息
     * @param username 用户名
     * @return Blogger实体类对象
     */
    Blogger findBloggerByUserName(String username);
}
