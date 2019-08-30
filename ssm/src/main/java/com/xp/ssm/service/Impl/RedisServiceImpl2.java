package com.xp.ssm.service.Impl;

import com.xp.ssm.entity.User;
import com.xp.ssm.mapper.UserMapper;
import com.xp.ssm.service.IRedisService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisServiceImpl2 implements IRedisService2 {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    //@Resource(name = "redisTemplate")
    HashOperations<String, String, User> hash;

    @Override
    public User getUser(String key) {
        String redKey = "user:"+key;
        User user = null;
        Boolean boo = redisTemplate.opsForHash().hasKey("user",key);
        if(boo){
            user = (User) redisTemplate.opsForHash().get("user",key);
            System.err.println("从redis" + user);
            return user;
        } else{
            user = userMapper.findByUid(Integer.valueOf(key));
//            System.err.println("从数据库");
            return user;
        }
    }

    @Override
    public void setUser(String key,User value) {
        redisTemplate.opsForHash().put("user",key,value);
    }
//////////////////////////////////////
    @Override
    public void listQueueInit(String carid) {
        String key = "pord:"+carid;//初始化key,待有多少任务完成
        redisTemplate.opsForList().leftPushAll(key,"1.商家出货","2.小哥发件","3,杭州某小区->>杭州物流园",
                "4.杭州物流园->>三峡物流园","5.三峡物流园->>某小区","6.某小区到本人");
    }

    @Override
    public void listQueueTouch(String carid) {
        String key = "pord:"+ carid +"succ";
        redisTemplate.opsForList().rightPopAndLeftPush("pord:"+carid,key);
    }

    @Override
    public List<String> listQueueSucc(String carid) {
        String key = "pord:"+carid +"succ";
        return (List<String>)(List)redisTemplate.opsForList().range(key,0,-1);
    }

    @Override
    public List<String> listQueueWait(String carid) {
        String key = "pord:"+carid;
        return (List<String>)(List)redisTemplate.opsForList().range(key,0,-1);
    }


}
