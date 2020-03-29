package com.matrix.service;

import com.matrix.entity.Comment;

import java.util.List;
import java.util.Map;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/27 21:03
 * @github https://github.com/Javen-Liu
 * 博客评论服务层接口
 */
public interface ICommentService {
    /**
     * 添加一条评论
     * @param comment 评论实体类对象
     */
    Integer add(Comment comment);

    /**
     * 更新一条评论
     * @param comment 评论实体类对象
     */
    Integer update(Comment comment);

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
     * 删除一条评论
     * @param commentId 评论id
     */
    Integer delete(Integer commentId);

    /**
     * 批量评论审核通过
     * @param idArr 评论id数组
     * @return 如果成功，返回1
     *         如果失败，则返回-1
     */
    Integer batchPass(String[] idArr);

    /**
     * 批量评论审核不通过
     * @param idArr 评论id数组
     * @return 如果成功，返回1
     *         如果失败，则返回-1
     */
    Integer batchFail(String[] idArr);

    /**
     * 批量删除评论
     * @param idArr 评论id数组
     * @return 如果成功，返回1
     *         如果失败，则返回-1
     */
    Integer batchDelete(String[] idArr);
}
