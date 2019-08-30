package com.xp.ssm.service.Impl;

import com.xp.ssm.entity.Comment;
import com.xp.ssm.entity.User;
import com.xp.ssm.mapper.CommentMapper;
import com.xp.ssm.mapper.LogMapper;
import com.xp.ssm.mapper.UserMapper;
import com.xp.ssm.service.ICommentService;
import com.xp.ssm.service.ex.InsertException;
import com.xp.ssm.service.ex.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class ComentServiceImpl implements ICommentService {

    @Autowired
    private UserMapper usermapper;
    @Autowired
    private LogMapper logMapper;
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void InsertComments(Comment comment) {
        User result = usermapper.findByUid(comment.getUid());
        if(result == null) {
            throw new UserNotFoundException("用户不存在");
        }
        comment.setCommTime(new Date());
        Integer rows = commentMapper.addComments(comment);
        if(rows != 1){
            throw new InsertException("评论失败,请稍后再试");
        }
    }
}
