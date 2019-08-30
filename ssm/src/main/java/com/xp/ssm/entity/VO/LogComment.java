package com.xp.ssm.entity.VO;

import java.util.Date;

public class LogComment {
    private Integer cid;
    private Integer lid;
    private Integer uid;
    private String content;
    private String commUser;
    private Date commTime;
    private String username;
    private String image;
    private String title;
    private Integer type;
    private Integer num;
    private String createUser;
    private Date createTime;
    private String modifiedUser;
    private Date modifiedTime;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public String toString() {
        return "LogComment{" +
                "cid=" + cid +
                ", lid=" + lid +
                ", uid=" + uid +
                ", content='" + content + '\'' +
                ", commUser='" + commUser + '\'' +
                ", commTime=" + commTime +
                ", username='" + username + '\'' +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", num=" + num +
                ", createUser='" + createUser + '\'' +
                ", createTime=" + createTime +
                ", modifiedUser='" + modifiedUser + '\'' +
                ", modifiedTime=" + modifiedTime +
                '}';
    }
}
