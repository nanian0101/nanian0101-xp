package com.xp.ssm.service.Impl;

import com.xp.ssm.entity.Log;
import com.xp.ssm.entity.VO.UserComment;
import com.xp.ssm.mapper.CommentMapper;
import com.xp.ssm.mapper.LogMapper;
import com.xp.ssm.mapper.UserMapper;
import com.xp.ssm.mapper.VO.UserCommentMapper;
import com.xp.ssm.service.VO.IUserCommentService;
import com.xp.ssm.service.ex.LogNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCommentImpl implements IUserCommentService {

    @Autowired
    private UserMapper usermapper;
    @Autowired
    private LogMapper logMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserCommentMapper userCommentMapper;

    @Override
    public List<UserComment> listByLid(Integer lid) {
        Log result = logMapper.findLogByLid(lid);
        if(result == null){
            throw new LogNotFoundException("该日志不存在");
        }
        return userCommentMapper.findByLid(lid);
    }
}
