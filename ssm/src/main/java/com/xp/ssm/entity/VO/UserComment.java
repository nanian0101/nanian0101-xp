package com.xp.ssm.entity.VO;

import java.util.Date;

public class UserComment {
    private String phone;
    private String email;
    private Integer gender;
    private String avatar;
    private String resume;
    private Integer cid;
    private Integer lid;
    private Integer uid;
    private String content;
    private String commUser;
    private Date commTime;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

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
        return "UserComment{" +
                "phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", avatar='" + avatar + '\'' +
                ", resume='" + resume + '\'' +
                ", cid=" + cid +
                ", lid=" + lid +
                ", uid=" + uid +
                ", content='" + content + '\'' +
                ", commUser='" + commUser + '\'' +
                ", commTime=" + commTime +
                '}';
    }
}
