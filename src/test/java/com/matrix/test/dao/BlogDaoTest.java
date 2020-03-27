package com.matrix.test.dao;

import com.matrix.dao.IBlogDao;
import com.matrix.dao.IBlogTypeDao;
import com.matrix.entity.Blog;
import com.matrix.entity.BlogType;
import com.matrix.test.BaseTest;
import com.matrix.utils.DateJsonValueProcessor;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/26 23:15
 * @github https://github.com/Javen-Liu
 */
public class BlogDaoTest extends BaseTest {
    @Resource
    private IBlogDao blogDao;

    @Resource
    private IBlogTypeDao blogTypeDao;

    @Test
    public void blogListTest(){
        Map<String,Object> map = new HashMap<>();
        map.put("start",0);
        map.put("size",10);
        map.put("status",0);
        List<Blog> blogs = blogDao.findBlogByMap(map);
        for (Blog b : blogs) {
            System.out.println(b);
        }
    }

    @Test
    public void blogUpdateTest(){
        Blog blog = blogDao.findBlogById(12);
        blog.setStatus(0);
        blogDao.updateBlog(blog);
    }

    @Test
    public void addBlogTest(){
        Blog blog = new Blog();
        BlogType type = blogTypeDao.findTypeById(1);
        blog.setBlogType(type);
        blog.setTitle("111");
        blog.setSummary("qwe");
        blog.setContent("2342");
        blog.setKeyword("2345");
        blogDao.addBlog(blog);
    }

    @Test
    public void jsonConfigTest(){
        Blog blog = new Blog();
        blog.setReleaseDate(new Date());
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        JSONObject object = JSONObject.fromObject(blog,config);
        System.out.println(object);
    }
}
