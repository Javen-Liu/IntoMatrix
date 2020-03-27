package com.matrix.service;

import com.matrix.entity.Blog;
import com.matrix.entity.BlogType;
import com.sun.istack.internal.NotNull;

import java.util.List;
import java.util.Map;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/25 21:54
 * @github https://github.com/Javen-Liu
 * 博客文章服务层接口
 */
public interface IBlogService {
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

    /**
     * 批量删除到回收站
     * @param idArr 待删除到回收站的文章类型id
     * @return 如果更新与添加成功，返回1
     *         如果更新与添加失败，则返回-1
     */
    Integer batchDeleteIntoRecycle(String[] idArr);

    /**
     * 批量从回收站中撤回
     * @param idArr 待撤回的文章类型id
     * @return 如果更新与添加成功，返回1
     *         如果更新与添加失败，则返回-1
     */
    Integer batchRecoverFromRecycle(String[] idArr);


    /**
     * 批量删除
     * @param idArr 待删除的文章类型id
     * @return 如果更新与添加成功，返回1
     *         如果更新与添加失败，则返回-1
     */
    Integer batchDelete(String[] idArr);
}
