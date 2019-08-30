package com.xp.ssm.controller;

import com.xp.ssm.entity.Like;
import com.xp.ssm.interceptor.CheckToken;
import com.xp.ssm.service.ILikeService;
import com.xp.ssm.util.ResponseResult;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Mapper
@RestController
@RequestMapping("like")
public class LikeController extends BaseController{

    @Autowired
    private ILikeService likeService;

    @RequestMapping("add")
    @CheckToken
    public ResponseResult<Void> addLike(Integer lid, HttpSession session,String token){
        //获取uid
        Integer uid = getUidFromToken(token);
        String usernmae = getUserNameFromToken(token);
        Like like = new Like();
        like.setLid(lid);
        like.setUid(uid);
        like.setUsername(usernmae);
        likeService.insertLikeInfo(like);
        return new ResponseResult<>(SUCCESS);
    }

    @RequestMapping("delete")
    @CheckToken
    public ResponseResult<Void> deleteLike(Integer lid, HttpSession session,String token){
        //获取uid
        Integer uid = getUidFromToken(token);
        likeService.deleteLike(lid,uid);
        return new ResponseResult<>(SUCCESS);
    }
}
