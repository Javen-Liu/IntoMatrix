package com.matrix.service.impl;

import com.matrix.dao.IBlogDao;
import com.matrix.entity.Blog;
import com.matrix.entity.BlogType;
import com.matrix.service.IBlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/25 21:55
 * @github https://github.com/Javen-Liu
 * 博客文章服务层实现类
 */
@Service("blogService")
public class BlogServiceImpl implements IBlogService {
    @Resource
    private IBlogDao blogDao;

    @Override
    public List<Blog> findAllBlog() {
        return blogDao.findAllBlog();
    }

    @Override
    public Blog findBlogById(Integer blogId) {
        return blogDao.findBlogById(blogId);
    }

    @Override
    public List<Blog> findBlogByMap(Map<String, Object> paramMap) {
        return blogDao.findBlogByMap(paramMap);
    }

    @Override
    public Long findCountOfBlogByMap(Map<String, Object> paramMap) {
        return blogDao.findCountOfBlogByMap(paramMap);
    }

    @Override
    public Integer addBlog(Blog blog) {
        try{
            blogDao.addBlog(blog);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Override
    public Integer updateBlog(Blog blog) {
        try{
            blogDao.updateBlog(blog);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Override
    public Integer deleteBlog(Integer blogId) {
        try{
            blogDao.deleteBlog(blogId);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Override
    public Integer batchDeleteIntoRecycle(String[] idArr) {
        try{
            for (String id : idArr) {
                Blog blog = blogDao.findBlogById(Integer.parseInt(id));
                blog.setStatus(1);
                blogDao.updateBlog(blog);
            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Override
    public Integer batchRecoverFromRecycle(String[] idArr) {
        try{
            for (String id : idArr) {
                Blog blog = blogDao.findBlogById(Integer.parseInt(id));
                blog.setStatus(0);
                blogDao.updateBlog(blog);
            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Override
    public Integer batchDelete(String[] idArr) {
        try{
            for (String id : idArr) {
                blogDao.deleteBlog(Integer.parseInt(id));
            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }
}
