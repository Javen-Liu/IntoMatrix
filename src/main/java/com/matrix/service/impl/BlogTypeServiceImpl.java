package com.matrix.service.impl;

import com.matrix.dao.IBlogTypeDao;
import com.matrix.entity.BlogType;
import com.matrix.service.IBlogTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/24 18:04
 * @github https://github.com/Javen-Liu
 * 博客文章类型服务层实现类
 */
@Service("blogTypeService")
public class BlogTypeServiceImpl implements IBlogTypeService {
    @Resource
    private IBlogTypeDao blogTypeDao;

    @Override
    public List<BlogType> findAllType() {
        return blogTypeDao.findAllType();
    }

    @Override
    public BlogType findTypeById(Integer typeId) {
        return blogTypeDao.findTypeById(typeId);
    }

    @Override
    public List<BlogType> findTypeByMap(Map<String, Object> paramMap) {
        return blogTypeDao.findTypeByMap(paramMap);
    }

    @Override
    public Long findCountOfTypeByMap(Map<String, Object> paramMap) {
        return blogTypeDao.findCountOfTypeByMap(paramMap);
    }

    @Override
    public Integer addType(BlogType blogType) {
        try{
            blogTypeDao.addType(blogType);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Override
    public Integer updateType(BlogType blogType) {
        try{
            blogTypeDao.updateType(blogType);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Override
    public Integer deleteType(Integer typeId) {
        try{
            blogTypeDao.deleteType(typeId);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Override
    public Integer batchSave(String[] idArr, String[] typeNameArr, String[] orderNoArr) {
        if(orderNoArr.length != typeNameArr.length || idArr.length != orderNoArr.length){
            return -1;
        }
        int size = idArr.length;
        for (int i = 0; i < size; i++) {
            BlogType blogType;
            if(!"on".equals(idArr[i])){
                blogType = new BlogType(Integer.parseInt(idArr[i]), typeNameArr[i], Integer.parseInt(orderNoArr[i]));
                blogTypeDao.updateType(blogType);
            }else{
                blogType = new BlogType(typeNameArr[i], Integer.parseInt(orderNoArr[i]));
                blogTypeDao.addType(blogType);
            }
        }
        return 1;
    }

    @Override
    public Integer batchDelete(String[] idArr) {
        try{
            for (String id : idArr) {
                blogTypeDao.deleteType(Integer.parseInt(id));
            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }
}
