package com.xp.ssm.service;

import com.xp.ssm.entity.Like;

import java.util.List;

public interface ILikeService {

    /**
     * 添加点赞详情信息
     * @param like 点赞信息
     * @return 受影响行数
     */
    void insertLikeInfo(Like like);

    /**
     * 取消点赞
     * @param lid 日志id
     * @param uid 用户id
     * @return 受影响行数
     */
    void deleteLike(Integer lid,Integer uid);

    /**
     * 查询用户点过赞的日志
     * @param uid 用户id
     * @return 点过赞的日志集合
     */
    List<Integer> listlidByUid(Integer uid);
}
