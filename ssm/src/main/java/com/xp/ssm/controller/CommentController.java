package com.xp.ssm.controller;

import com.xp.ssm.entity.Comment;
import com.xp.ssm.entity.VO.UserComment;
import com.xp.ssm.interceptor.CheckToken;
import com.xp.ssm.interceptor.LoginToken;
import com.xp.ssm.service.ICommentService;
import com.xp.ssm.service.VO.IUserCommentService;
import com.xp.ssm.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("comment")
public class CommentController extends BaseController {

    @Autowired
    private ICommentService commentService;
    @Autowired
    private IUserCommentService userCommentService;

    @RequestMapping("insert")
    @CheckToken
    public ResponseResult<Void> insertComment(Comment comment, HttpSession session,String token){
        //获取uid和username
        Integer uid = getUidFromToken(token);
        String username = getUserNameFromToken(token);
        comment.setUid(uid);
        comment.setCommUser(username);
        commentService.InsertComments(comment);
        return new ResponseResult<Void>(SUCCESS);
    }

    @RequestMapping("listcomment")
    @LoginToken
    public ResponseResult<List<UserComment>> listcomment(Integer lid){
        List<UserComment> data = userCommentService.listByLid(lid);
        return new ResponseResult<>(SUCCESS,data);
    }
}

