package com.matrix.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/25 20:51
 * @github https://github.com/Javen-Liu
 * 博客文章实体类
 */
public class Blog implements Serializable {
    private static final long serialVersionUID = 2L;
    private Integer id;
    private String title;
    private String summary;
    //文章发布时间，Date类对象
    private Date releaseDate;
    //文章发布时间，String类
    private String releaseDateStr;
    private Integer clickHit;
    private Integer replyHit;
    //文本内容
    private String content;
    //纯文本
    private String contentWithNoTag;
    private BlogType blogType;
    private String keyword;
    private Integer blogCount;
    private Integer status;

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDateStr() {
        return releaseDateStr;
    }

    public void setReleaseDateStr(String releaseDateStr) {
        this.releaseDateStr = releaseDateStr;
    }

    public Integer getClickHit() {
        return clickHit;
    }

    public void setClickHit(Integer clickHit) {
        this.clickHit = clickHit;
    }

    public Integer getReplyHit() {
        return replyHit;
    }

    public void setReplyHit(Integer replyHit) {
        this.replyHit = replyHit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentWithNoTag() {
        return contentWithNoTag;
    }

    public void setContentWithNoTag(String contentWithNoTag) {
        this.contentWithNoTag = contentWithNoTag;
    }

    public BlogType getBlogType() {
        return blogType;
    }

    public void setBlogType(BlogType blogType) {
        this.blogType = blogType;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", releaseDate=" + releaseDate +
                ", releaseDateStr='" + releaseDateStr + '\'' +
                ", clickHit=" + clickHit +
                ", replyHit=" + replyHit +
                ", blogType=" + blogType +
                ", keyword='" + keyword + '\'' +
                ", blogCount=" + blogCount +
                ", status=" + status +
                '}';
    }
}
