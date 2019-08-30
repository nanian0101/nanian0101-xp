package com.xp.ssm.controller;

import com.auth0.jwt.JWT;
import com.xp.ssm.entity.User;
import com.xp.ssm.interceptor.CheckToken;
import com.xp.ssm.interceptor.LoginToken;
import com.xp.ssm.service.IRedisService2;
import com.xp.ssm.service.IUserService;
import com.xp.ssm.util.JwtUtil;
import com.xp.ssm.util.ResponseResult;
import com.xp.ssm.util.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController extends BaseController{

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRedisService2 redisService;

    @RequestMapping("login")
    @LoginToken
    public ResponseResult<String> login(String phone, String password){
        User data = userService.selectByPhone(phone,password);
        String token = JwtUtil.createJWT(1000*60*60*24,data);
        ValueOperations<Object, Object> string = redisTemplate.opsForValue();
        //将用户信息保存到redsi做缓存
        Boolean boo = redisTemplate.opsForHash().hasKey("user",data.getUid()+"");
        if(!boo){
            redisService.setUser(data.getUid()+"",data);
        }
        return new ResponseResult<>(SUCCESS,token);
    }

    @RequestMapping("reg")
    @LoginToken
    public ResponseResult<Void> reg(User user){
        userService.InsertUser(user);
        return new ResponseResult<>(SUCCESS);
    }

    @RequestMapping("update")
    @CheckToken
    public ResponseResult<Void> updateMyInfo(@RequestParam("avatar") MultipartFile avatar, String username,
                                             String resume, HttpSession session,String token){
        UploadFile upload = new UploadFile();
        String name = upload.FileNmae(avatar,session,"avatar");
        //从token获取uid
        Integer uid  = Integer.valueOf(JWT.decode(token).getClaim("uid").asString());
        //Integer uid = getUidFromSession(session);
        //封装数据
        User user = redisService.getUser(uid+"");
        user.setAvatar("/avatar/"+name);
        user.setUsername(username);
        user.setResume(resume);
        userService.UpdateUser(user);
        return new ResponseResult<Void>(SUCCESS);
    }

    @RequestMapping("myinfo")
    @CheckToken
    public ResponseResult<User> MyInfo(HttpSession session,String token){
        //从token获取uid
        Integer uid  = Integer.valueOf(JWT.decode(token).getClaim("uid").asString());
        Boolean boo = redisTemplate.opsForHash().hasKey("user",uid+"");
        User data = null;
        if(boo){
            data = redisService.getUser(uid+"");
        } else {
            data = userService.selectByUid(uid);
        }
        return new ResponseResult<User>(SUCCESS,data);
    }
}
