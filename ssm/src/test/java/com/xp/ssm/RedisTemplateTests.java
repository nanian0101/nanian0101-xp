package com.xp.ssm;

import com.xp.ssm.entity.User;
import com.xp.ssm.mapper.UserMapper;
import com.xp.ssm.service.IRedisService;
import com.xp.ssm.util.JsonUtils;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTests {

    private JSONObject json = new JSONObject();

    @Autowired
    private IRedisService redisService;
    @Autowired
    private UserMapper userMapper;



    @Test
    public void contextLoads() {
    }

    /**
     * 测试string RedisTemplate 存字符串
     */
    @Test
    public void t1(){
        User user = userMapper.findByUid(1);
        String value = user.toString();
        redisService.set("xp", value);
    }

    /**
     * 取字符串
     */
    @Test
    public void t2(){
        System.err.println(redisService.get("xp"));
    }

    /**
     * 存对象
     */
    @Test
    public void t3(){
        Integer uid = 2;
        String key = "user:"+uid;
        if(redisService.getHash(key) == null){
            //redis没有,从数据库里拿,并将数据插入redis
            User user = userMapper.findByUid(uid);
            System.err.println("这是从数据库里拿到的数据:"+user);
            Map<String,String> map = new HashMap<>();
            map.put("uid",user.getUid()+"");
            map.put("username",user.getUsername());
            map.put("phone",user.getPhone());
            redisService.setHash(key,map);
        } else {
            Map<String,String> map = redisService.getHash(key);
            for (Map.Entry<String,String> entity: map.entrySet()) {
                System.err.println(entity.getKey()+","+entity.getValue());
            }
            String str = JsonUtils.mapToJson(map);
            System.err.println("这是从redis里拿到的数据:"+map);
            System.err.println(str);
        }
    }

}
