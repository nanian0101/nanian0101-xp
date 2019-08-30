package com.xp.ssm.entity;

import java.util.Date;

public class Like {
    private Integer kid;
    private Integer lid;
    private Integer uid;
    private String username;
    private Date like_time;

    public Integer getKid() {
        return kid;
    }

    public void setKid(Integer kid) {
        this.kid = kid;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLike_time() {
        return like_time;
    }

    public void setLike_time(Date like_time) {
        this.like_time = like_time;
    }

    @Override
    public String toString() {
        return "Like{" +
                "kid=" + kid +
                ", lid=" + lid +
                ", ruid=" + uid +
                ", username='" + username + '\'' +
                ", like_time=" + like_time +
                '}';
    }
}
