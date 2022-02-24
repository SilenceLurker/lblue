package com.cn.emio.sl.lblue.test.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Silence_Lurker
 */
@Entity
@Table(name = "moodtest")
public class Mood implements Serializable {
    @Id
    private String id;
    private String content;
    private String userId;
    private Integer praiseNum;
    private Date publishTime;

    // Date类型待测试

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public String getUserId() {
        return userId;
    }

}
