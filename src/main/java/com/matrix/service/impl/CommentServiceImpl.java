package com.matrix.service.impl;

import com.matrix.dao.ICommentDao;
import com.matrix.entity.Comment;
import com.matrix.service.ICommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/27 21:03
 * @github https://github.com/Javen-Liu
 * 博客评论服务层实现类
 */
@Service("commentService")
public class CommentServiceImpl implements ICommentService {
    @Resource
    private ICommentDao commentDao;

    @Override
    public Integer add(Comment comment) {
        try{
            commentDao.add(comment);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Override
    public Integer update(Comment comment) {
        try{
            commentDao.update(comment);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Override
    public List<Comment> findCommentByMap(Map<String, Object> paramMap) {
        return commentDao.findCommentByMap(paramMap);
    }

    @Override
    public Long findCountOfCommentByMap(Map<String, Object> paramMap) {
        return commentDao.findCountOfCommentByMap(paramMap);
    }

    @Override
    public Integer delete(Integer commentId) {
        try{
            commentDao.delete(commentId);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Override
    public Integer batchPass(String[] idArr) {
        try{
            for (String id : idArr) {
                Comment comment = commentDao.findCommentById(Integer.parseInt(id));
                comment.setStatus(1);
                commentDao.update(comment);
            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Override
    public Integer batchFail(String[] idArr) {
        try{
            for (String id : idArr) {
                Comment comment = commentDao.findCommentById(Integer.parseInt(id));
                comment.setStatus(2);
                commentDao.update(comment);
            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }
}
