package com.xp.ssm;

import com.xp.ssm.entity.User;
import com.xp.ssm.mapper.UserMapper;
import com.xp.ssm.util.RedisPool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
    }

    /**
     * 字符串操作
     */
    @Test
    public void t1(){
        Jedis jedis = RedisPool.getJedis();
        String key = "applicatioonName";
        if(jedis.exists(key)){
            String result = jedis.get(key);
            System.out.println("Redis中查到的数据:"+result);
        } else {
            //在数据库查询
            String result = "微信开发会以达人";
            jedis.set(key,result);
            System.out.println("Mysql数据库中查到的数据:"+result);
        }
        RedisPool.close(jedis);
    }

    @Test
    public void t2(){
        Jedis jedis = RedisPool.getJedis();
        Integer uid = 1;
        String key = "users" + uid;
        if(jedis.exists(key)){
            Map<String,String> hash = jedis.hgetAll(key);
            User user = new User();
            user.setUid(Integer.valueOf(hash.get("uid")));
            user.setUsername(hash.get("username"));
            System.err.println("从Redis中获取:"+user);
        } else {
            //从数据库查找
            User user = userMapper.findByUid(uid);
            //赋值查询
            Map<String,String> hash = new HashMap<>();
            hash.put("uid",user.getUid()+"");
            hash.put("username",user.getUsername());
            jedis.hmset(key,hash);
            System.err.println("从MySQL中获取:"+user);
        }
        RedisPool.close(jedis);
    }

}
