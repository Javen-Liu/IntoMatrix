package com.matrix.test.dao;

import com.matrix.dao.IBlogTypeDao;
import com.matrix.entity.BlogType;
import com.matrix.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/24 17:29
 * @github https://github.com/Javen-Liu
 * BlogType持久层测试
 */
public class BlogTypeDaoTest extends BaseTest {
    @Autowired
    private IBlogTypeDao blogTypeDao;

    @Test
    public void findAllTypeTest(){
        List<BlogType> types = blogTypeDao.findAllType();
        for (BlogType t : types) {
            System.out.println(t);
        }
    }

    @Test
    public void findTypeById(){
        BlogType type = blogTypeDao.findTypeById(2);
        System.out.println(type);
    }

    @Test
    public void findTypeByMapTest(){
        Map<String,Object> map = new HashMap<>();
        map.put("start", 0);
        map.put("size", 6);
        List<BlogType> types = blogTypeDao.findTypeByMap(map);
        System.out.println(types);
    }

    @Test
    public void updateTypeTest(){
        BlogType blogType = new BlogType(1,"JavaSE",1);
        blogTypeDao.updateType(blogType);
    }
}
