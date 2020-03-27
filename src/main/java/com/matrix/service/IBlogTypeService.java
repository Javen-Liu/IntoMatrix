package com.matrix.service;

import com.matrix.entity.BlogType;
import com.sun.istack.internal.NotNull;

import java.util.List;
import java.util.Map;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/24 18:03
 * @github https://github.com/Javen-Liu
 * 博客文章类型服务层接口
 */
public interface IBlogTypeService {
    /**
     * 无参查找所有博客文章类型
     * @return 返回存储了所有BlogType文章类型实体类的对象
     */
    List<BlogType> findAllType();

    /**
     * 根据文章类型的id查询对应的博客文章类型实体对象
     * @param typeId 文章类型的id
     * @return 返回BlogType博客文章类型实体类的对象
     */
    BlogType findTypeById(Integer typeId);

    /**
     * 根据不固定参数来查询博客类文章型列表
     * @param paramMap 不固定参数，类型为Map
     * @return 返回存储了所有BlogType文章类型实体类的对象
     */
    List<BlogType> findTypeByMap(Map<String, Object> paramMap);

    /**
     * 根据不固定参数来查询博客文章类型数
     * @param paramMap 不固定参数，类型为Map
     * @return 返回对应博客文章类型的总数
     */
    Long findCountOfTypeByMap(Map<String, Object> paramMap);

    /**
     * 添加一条博客文章类型
     * @param blogType 博客类型实体类对象
     * @return 如果添加成功，返回1
     *         如果添加失败，则返回-1
     */
    Integer addType(BlogType blogType);

    /**
     * 修改一条博客文章类型
     * @param blogType 博客类型实体类对象
     * @return 如果修改成功，返回1
     *         如果修改失败，则返回-1
     */
    Integer updateType(BlogType blogType);

    /**
     * 删除一条博客文章类型
     * @param typeId 博客类型实体类对象
     * @return 如果删除成功，返回1
     *         如果删除失败，则返回-1
     */
    Integer deleteType(@NotNull Integer typeId);

    /**
     * 根据前端发来的数据来进行博客文章类别的批量更新与添加
     * @param idArr 进行更新与添加的类型id数组
     * @param typeNameArr 进行更新与添加的类型名称数组
     * @param orderNoArr 进行更新与添加的类型序号数组
     * @return 如果更新与添加成功，返回1
     *         如果更新与添加失败，则返回-1
     */
    Integer batchSave(String[] idArr, String[] typeNameArr, String[] orderNoArr);

    /**
     * 批量删除
     * @param idArr 待删除的文章类型id
     * @return 如果更新与添加成功，返回1
     *         如果更新与添加失败，则返回-1
     */
    Integer batchDelete(String[] idArr);
}
