package com.matrix.entity;

import java.util.Date;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/27 20:37
 * @github https://github.com/Javen-Liu
 * 博客评论实体类
 */
public class Comment {
    private Integer id;
    private String userIp;
    private Blog blog;
    private String content;
    private Date commentDate;
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
