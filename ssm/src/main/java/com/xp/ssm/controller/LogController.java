package com.xp.ssm.controller;

import com.xp.ssm.entity.Log;
import com.xp.ssm.interceptor.CheckToken;
import com.xp.ssm.interceptor.LoginToken;
import com.xp.ssm.service.ILikeService;
import com.xp.ssm.service.ILogService;
import com.xp.ssm.service.ex.UserNotFoundException;
import com.xp.ssm.util.ResponseResult;
import com.xp.ssm.util.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("log")
public class LogController extends BaseController{

    @Autowired
    private ILogService logService;
    @Autowired
    private ILikeService likeService;

    @RequestMapping("insert")
    @CheckToken
    public ResponseResult<Void> insertLog(@RequestParam("images")MultipartFile images, String title,
                                          String content, Integer type, HttpSession session,String token){
        UploadFile uplad = new UploadFile();
        String name = uplad.FileNmae(images,session,"images");
        //获取uid
        Integer uid = getUidFromToken(token);
        //封装数据
        Log log = new Log();
        String FILE_NAME = "/images/" + name;
        log.setUid(uid);
        log.setTitle(title);
        log.setContent(content);
        log.setImage(FILE_NAME);
        log.setType(type);
        //执行操作
        logService.insertLog(log);
        return new ResponseResult<Void>(SUCCESS);
    }

    @RequestMapping("mylog")
    @CheckToken
    public ResponseResult<List<Log>> listMyLog(Integer curr,Integer limit,String token){
        //获取uid
        Integer uid = getUidFromToken(token);
        List<Log> data = logService.listMyLog(uid,curr,limit);
        return new ResponseResult<>(SUCCESS,data);
    }

    @RequestMapping("countlog")
    @CheckToken
    public ResponseResult<Integer> countog(String token){
        //获取uid
        Integer uid = getUidFromToken(token);
        Integer data = logService.numLog(uid);
        return new ResponseResult<>(SUCCESS,data);
    }

    @RequestMapping("listmylog")
    @CheckToken
    public ResponseResult<Log> listMyLog(Integer lid,String token){
        //获取uid
        Integer uid = getUidFromToken(token);
        Log data = logService.selectLogByLid(lid,uid);
        return new ResponseResult<>(SUCCESS,data);
    }

    @RequestMapping("countalllog")
    @LoginToken
    public ResponseResult<Integer> counalltog(){
        //获取uid
        Integer data = logService.selectAllLogCount();
        return new ResponseResult<>(SUCCESS,data);
    }

    @RequestMapping("alllog")
    @LoginToken
    public ResponseResult<List<Log>> listallLog(Integer curr,Integer limit,HttpSession session,String token){
        List<Log> data = logService.selectAllLog(curr,limit);
        List<Integer> isLike = likeService.listlidByUid(getUidFromToken(token));
        return new ResponseResult<>(SUCCESS,data,getUidFromToken(token),isLike);
    }

    @RequestMapping("delete")
    @CheckToken
    public ResponseResult<Void> deleteLog(Integer lid,Integer uid,HttpSession session,String token){
        if(uid != getUidFromToken(token)){
            throw new UserNotFoundException("您不是该日志发表人,不能删除");
        }
        logService.DeleteByLid(lid);
        return new ResponseResult<>(SUCCESS);
    }
}
