package com.matrix.dao;

import com.matrix.entity.Blog;
import com.sun.istack.internal.NotNull;

import java.util.List;
import java.util.Map;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/25 21:01
 * @github https://github.com/Javen-Liu
 * 博客文章持久层接口
 */
public interface IBlogDao {
    /**
     * 无参查找所有博客文章（供首页使用）
     * @return 返回存储了所有Blog文章实体类的对象
     */
    List<Blog> findAllBlog();

    /**
     * 根据文章类型的id查询对应的博客实体对象
     * @param blogId 文章类型的id
     * @return 返回Blog博客文章实体类的对象
     */
    Blog findBlogById(Integer blogId);

    /**
     * 根据不固定参数来查询博客文章列表
     * @param paramMap 不固定参数，类型为Map
     * @return 返回存储了所有Blog文章实体类的对象
     */
    List<Blog> findBlogByMap(Map<String, Object> paramMap);

    /**
     * 根据不固定参数来查询博客文章数
     * @param paramMap 不固定参数，类型为Map
     * @return 返回对应博客文章的总数
     */
    Long findCountOfBlogByMap(Map<String, Object> paramMap);

    /**
     * 添加一条博客文章
     * @param blog 博客实体类对象
     * @return 如果添加成功，返回1
     *         如果添加失败，则返回-1
     */
    Integer addBlog(Blog blog);

    /**
     * 修改一条博客文章
     * @param blog 博客实体类对象
     * @return 如果修改成功，返回1
     *         如果修改失败，则返回-1
     */
    Integer updateBlog(Blog blog);

    /**
     * 删除一条博客文章
     * @param blogId 博客实体类对象
     * @return 如果删除成功，返回1
     *         如果删除失败，则返回-1
     */
    Integer deleteBlog(@NotNull Integer blogId);
}
