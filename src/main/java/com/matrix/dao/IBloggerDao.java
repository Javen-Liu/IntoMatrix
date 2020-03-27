package com.matrix.dao;

import com.matrix.entity.Blogger;
import org.apache.ibatis.annotations.Param;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/24 15:38
 * @github https://github.com/Javen-Liu
 * 博主账户持久层接口
 */
public interface IBloggerDao {
    /**
     * 通过username用户名来查询Blogger实体类对象
     * @param username 用户名
     * @return Blogger实体类对象
     */
    Blogger findBloggerByUserName(@Param("username") String username);
}
