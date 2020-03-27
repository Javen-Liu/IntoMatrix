package com.matrix.test.service;

import com.matrix.entity.BlogType;
import com.matrix.service.IBlogTypeService;
import com.matrix.test.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/24 23:15
 * @github https://github.com/Javen-Liu
 */
public class BlogTypeServiceTest extends BaseTest {
    @Resource
    private IBlogTypeService blogTypeService;

    @Test
    public void findTypeByMapTest(){
        Map<String,Object> map = new HashMap<>();
        map.put("start", 0);
        map.put("size", 10);
        List<BlogType> types = blogTypeService.findTypeByMap(map);
        System.out.println(types);
    }
}
