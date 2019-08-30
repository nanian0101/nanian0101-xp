package com.xp.ssm.service.Impl;

import com.xp.ssm.entity.User;
import com.xp.ssm.mapper.UserMapper;
import com.xp.ssm.service.IRedisService2;
import com.xp.ssm.service.IUserService;
import com.xp.ssm.service.ex.InsertException;
import com.xp.ssm.service.ex.PasswordException;
import com.xp.ssm.service.ex.UpdateException;
import com.xp.ssm.service.ex.UserNotFoundException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IRedisService2 redisService;

    @Autowired
    private UserMapper usermapper;

    @Override
    public User selectByPhone(String phone,String password) {
        Integer count = 1;
        User result = usermapper.findByPhone(phone);
        if (result == null){
            throw new UserNotFoundException("登录失败,用户不存在");
        }
        String pwd = getMD5Password(password,result.getSalt());
        if (!result.getPassword().equals(pwd)){
            throw new PasswordException("密码错误");
        }
        return result;
    }

    @Override
    public User selectByUid(Integer uid) {
        User result = usermapper.findByUid(uid);
        if (result == null){
            throw new UserNotFoundException("用户不存在");
        }
        return usermapper.findByUid(uid);
    }

    @Override
    public void InsertUser(User user) {
        User result = usermapper.findByPhone(user.getPhone());
        if(result != null){
            throw new UserNotFoundException(user.getPhone()+"该手机好已经被注册,请直接登录");
        }
        if(usermapper.findByEmail(user.getEmail()) != null){
            throw new UserNotFoundException(user.getEmail()+"该邮箱已经被注册");
        }
        if(usermapper.findByUserNama(user.getUsername()) != null){
            throw new UserNotFoundException(user.getUsername()+"该用户名已经被注册,请重新输入");
        }
        //随机获取salt(UUID)
        String salt = UUID.randomUUID().toString().toUpperCase();
        //获取加密后密码
        String MD5Pwd = getMD5Password(user.getPassword(),salt);
        //对数封装
        user.setSalt(salt);
        user.setPassword(MD5Pwd);
        user.setCreateUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        user.setCreateTime(new Date());
        user.setModifiedTime(new Date());
        //执行操作
        Integer rows = usermapper.addUser(user);
        if(rows != 1){
            throw new InsertException("注册失败,请稍后再试");
        }
    }

    @Override
    public void UpdateUser(User user) {
        User result = usermapper.findByUid(user.getUid());
        if (result == null){
            throw new UserNotFoundException("用户不存在");
        }
        user.setModifiedUser(result.getUsername());
        user.setModifiedTime(new Date());
        Integer rows = usermapper.changeUser(user);
        if (rows != 1){
            throw new UpdateException("修改信息异常");
        }
        redisService.setUser(result.getUid()+"",user);
    }

    @Autowired
    RedisTemplate<String,String> redisTemplate;
    /**
     * 通过key得值 string测试
     * 如果key不存在到数据库查询
     * 如果存在,到redis查询
     * @param key
     * @return
     */
    @Override
    public String getString(String key) {
        ValueOperations<String , String > string =  redisTemplate.opsForValue();
        //判断key是否存在
        if (redisTemplate.hasKey(key)){
            //在redis中去
            System.err.println("在redis中取出返回");
            return string.get(key);
        } else {
            //查询数据
            String result = "RedisTemplate模板练习";
            string.set(key, result);
            System.err.println("在Mysql中取出返回");
            return result;
        }
    }

    /**
     * 获取MD5摘要加密后的密码
     * @param password 原密码
     * @param salt 盐值
     * @return 加密后的密码
     */
    private String getMD5Password(String password,String salt) {
        //加密规则:原密码左右两侧都拼接盐值,并循环加密5次
        String str = salt + password + salt;
        for(int i =0;i<5;i++) {
            str = DigestUtils.md5Hex(str).toUpperCase();
        }
        return str;
    }
}
