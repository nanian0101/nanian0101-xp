package com.xp.ssm.service.Impl;

import com.xp.ssm.entity.Log;
import com.xp.ssm.entity.User;
import com.xp.ssm.mapper.LogMapper;
import com.xp.ssm.mapper.UserMapper;
import com.xp.ssm.service.ILogService;
import com.xp.ssm.service.ex.InsertException;
import com.xp.ssm.service.ex.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LogServiceImpl implements ILogService {

    @Autowired
    private LogMapper logMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertLog(Log log) {
        User result = userMapper.findByUid(log.getUid());
        if(result == null) {
            throw new UserNotFoundException("用户失效,请重新登录");
        }
        //获取当前操作人
        String username = result.getUsername();
        //继续封装数据
        log.setUsername(username);
        log.setCreateUser(username);
        log.setModifiedUser(username);
        log.setCreateTime(new Date());
        log.setModifiedTime(new Date());
        Integer rows = logMapper.addLog(log);
        if(rows != 1){
            throw new InsertException("出现未知异常,请稍后再试");
        }
    }

    @Override
    public Integer numLog(Integer uid) {
        User result = userMapper.findByUid(uid);
        if(result == null) {
            throw new UserNotFoundException("用户失效,请重新登录");
        }
        return logMapper.countLog(uid);
    }

    @Override
    public List<Log> listMyLog(Integer uid,Integer curr,Integer limit) {
        User result = userMapper.findByUid(uid);
        if(result == null) {
            throw new UserNotFoundException("用户失效,请重新登录");
        }
        curr = (curr - 1)*limit;
        return logMapper.findMyLog(uid,curr,limit);
    }

    @Override
    public Log selectLogByLid(Integer lid ,Integer uid) {
        User Result = userMapper.findByUid(uid);
        if(Result == null){
            throw new UserNotFoundException("用户失效,请重新登录");
        }
        return logMapper.findLogByLid(lid);
    }

    @Override
    public void DeleteByLid(Integer lid) {
        logMapper.RemoveByLid(lid);
    }

    @Override
    public List<Log> selectAllLog(Integer curr,Integer limit) {
        curr = (curr - 1)*limit;
        return logMapper.findAllLog(curr,limit);
    }

    @Override
    public Integer selectAllLogCount() {
        return logMapper.findAllLogCount();
    }
}
