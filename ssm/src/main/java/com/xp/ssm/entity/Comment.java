package com.xp.ssm.entity;

import java.util.Date;

public class Comment {

    private Integer cid;
    private Integer lid;
    private Integer uid;
    private String content;
    private String commUser;
    private Date commTime;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommUser() {
        return commUser;
    }

    public void setCommUser(String commUser) {
        this.commUser = commUser;
    }

    public Date getCommTime() {
        return commTime;
    }

    public void setCommTime(Date commTime) {
        this.commTime = commTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "cid=" + cid +
                ", lid=" + lid +
                ", uid=" + uid +
                ", content='" + content + '\'' +
                ", commUser='" + commUser + '\'' +
                ", commTime=" + commTime +
                '}';
    }
}
