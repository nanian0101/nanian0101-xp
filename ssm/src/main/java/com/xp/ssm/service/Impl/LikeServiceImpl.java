package com.xp.ssm.service.Impl;

import com.xp.ssm.entity.Like;
import com.xp.ssm.mapper.LikeMapper;
import com.xp.ssm.mapper.LogMapper;
import com.xp.ssm.service.ILikeService;
import com.xp.ssm.service.ex.LikeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LikeServiceImpl implements ILikeService {

    @Autowired
    private LogMapper logMapper;
    @Autowired
    private LikeMapper likeMapper;


    @Override
    public void insertLikeInfo(Like like) {
        Integer rows = logMapper.likeLog(like.getLid());
        if(rows != 1){
            throw new LikeException("点赞失败");
        }
        like.setLike_time(new Date());
        likeMapper.addLikeInfo(like);
    }

    @Override
    public void deleteLike(Integer lid, Integer uid) {
        Integer rows = logMapper.noLikeLog(lid);
        if(rows != 1){
            throw new LikeException("取消点赞失败");
        }
        likeMapper.removeLike(lid,uid);
    }

    @Override
    public List<Integer> listlidByUid(Integer uid) {
        return likeMapper.lidByUid(uid);
    }
}
