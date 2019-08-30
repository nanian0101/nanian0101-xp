package com.xp.ssm.util;

import java.util.List;

public class ResponseResult<T> {

    private Integer state;
    private String massage;
    private Integer nowUid;
    private List<Integer> isLike;
    private T data;

    public ResponseResult(){

    }

    public ResponseResult(Integer srate){
        this.state = srate;
    }

    public ResponseResult(Integer state,T data){
        this.state = state;
        this.data = data;
    }

    public ResponseResult(Integer state,T data,Integer nowUid){
        this.state = state;
        this.data = data;
        this.nowUid = nowUid;
    }
    public ResponseResult(Integer state,T data,Integer nowUid,List<Integer> isLike){
        this.state = state;
        this.data = data;
        this.nowUid = nowUid;
        this.isLike = isLike;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getNowUid() {
        return nowUid;
    }

    public void setNowUid(Integer nowUid) {
        this.nowUid = nowUid;
    }

    public List<Integer> getIsLike() {
        return isLike;
    }

    public void setIsLike(List<Integer> isLike) {
        this.isLike = isLike;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "state=" + state +
                ", massage='" + massage + '\'' +
                ", nowUid=" + nowUid +
                ", isLike=" + isLike +
                ", data=" + data +
                '}';
    }
}
