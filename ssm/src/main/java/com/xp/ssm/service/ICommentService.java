package com.xp.ssm.service;


import com.xp.ssm.entity.Comment;

public interface ICommentService {

    /**
     * 添加评论
     * @param comment 评论信息
     * @return 受影响行数
     */
    void InsertComments(Comment comment);

}
