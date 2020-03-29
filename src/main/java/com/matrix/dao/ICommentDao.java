package com.matrix.dao;

import com.matrix.entity.Comment;

import java.util.List;
import java.util.Map;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/27 20:40
 * @github https://github.com/Javen-Liu
 * 博客评论持久层接口
 */
public interface ICommentDao {
    /**
     * 添加一条评论
     * @param comment 评论实体类对象
     */
    void add(Comment comment);

    /**
     * 更新一条评论
     * @param comment 评论实体类对象
     */
    void update(Comment comment);

    /**
     * 根据不固定参数来查询博客评论
     * @param paramMap 不固定参数，类型为Map
     * @return 返回存储了所有Comment评论实体类对象的集合
     */
    List<Comment> findCommentByMap(Map<String, Object> paramMap);

    /**
     * 根据不固定参数来查询博客评论数量
     * @param paramMap 不固定参数，类型为Map
     * @return 评论的数量
     */
    Long findCountOfCommentByMap(Map<String, Object> paramMap);

    /**
     * 根据评论id查找评论
     * @param commentId 评论id
     * @return 评论实体类
     */
    Comment findCommentById(Integer commentId);

    /**
     * 删除一条评论
     * @param commentId 评论id
     */
    void delete(Integer commentId);

    /**
     * 根据博客id删除评论
     * @param blogId 博客id
     */
    void deleteByBlogId(Integer blogId);
}
