package com.xp.ssm.controller;

import com.auth0.jwt.JWT;
import com.xp.ssm.service.ex.*;
import com.xp.ssm.util.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class BaseController {

    /**
     * 表示响应成功,用户的操作是正确的
     */
    protected static final Integer SUCCESS = 200;

    /**
     * 从Session中获取uid
     * @param session
     * @return uid
     */
    protected final Integer getUidFromToken(String token) {
        return Integer.valueOf(JWT.decode(token).getClaim("uid").asString());
    }

    /**
     * 从Session中获取username
     * @param session
     * @return uid
     */
    protected final String getUserNameFromToken(String token) {
        return JWT.decode(token).getClaim("username").asString();
    }

    @ExceptionHandler
    public ResponseResult<Void> handlerException(Throwable e){
        ResponseResult<Void> rr = new ResponseResult<Void>();
        rr.setMassage(e.getMessage());
        if (e instanceof UserNotFoundException){
            rr.setState(405);
            //用户名不存在
        } else if(e instanceof PasswordException){
            rr.setState(406);
            //密码错误
        } else if(e instanceof InsertException){
            rr.setState(407);
            //注册失败
        } else if(e instanceof FileException){
            rr.setState(408);
            //文件异常
        } else if(e instanceof UpdateException){
            rr.setState(409);
            //修改异常
        } else if(e instanceof RuntimeException){
            rr.setState(410);
            //修改异常
        }
        return rr;
    }
}
