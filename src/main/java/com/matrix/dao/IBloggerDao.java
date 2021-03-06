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

    /**
     * 通过userId用户id来查询Blogger实体类对象
     * @param userId 用户id
     * @return Blogger实体类对象
     */
    Blogger findBloggerByUserId(@Param("userId") Integer userId);

    /**
     * 更新用户信息
     * @param blogger 用户实体类
     * @return 如果更新成功，返回1
     *         如果更新失败，则返回-1
     */
    Integer updateBlogger(Blogger blogger);
}
