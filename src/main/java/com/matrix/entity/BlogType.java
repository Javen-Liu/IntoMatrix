package com.matrix.entity;

import java.io.Serializable;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/24 16:56
 * @github https://github.com/Javen-Liu
 * 博客文章类型实体类
 */
public class BlogType implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String typeName;
    private Integer orderNo;
    private Integer articleCount;

    public BlogType(Integer id, String typeName, Integer orderNo) {
        this.id = id;
        this.typeName = typeName;
        this.orderNo = orderNo;
    }

    public BlogType(String typeName, Integer orderNo) {
        this.typeName = typeName;
        this.orderNo = orderNo;
    }

    public BlogType() {
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "BlogType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", orderNo=" + orderNo +
                ", articleCount=" + articleCount +
                '}';
    }
}
