package com.xp.ssm;

import com.xp.ssm.mapper.UserMapper;
import com.xp.ssm.service.IRedisService2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTests2 {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    IRedisService2 redisService2;

//    @Autowired
//    private RedisTemplate<Object, Object> redisTemplate;

    @Test
    public void contextLoads() {

    }

    @Test
    public void t1(){
        String carid = "1001";
        //付款成功,生成任务队列,一次
        //redisService2.listQueueInit(carid);
        System.err.println("==========**快递===========");
        System.err.println("==========当前任务队列");
        List<String> list =  redisService2.listQueueWait(carid);
        for(String s : list){
            System.err.println(s);
        }
        System.err.println("==========外卖小哥触发事件");
        redisService2.listQueueTouch(carid);
        System.err.println("==========当前任务队列");
        List<String> list2 =  redisService2.listQueueWait(carid);
        for(String s : list2){
            System.err.println(s);
        }
        System.err.println("==========已完成任务队列");
        List<String> list1 =  redisService2.listQueueSucc(carid);
        for(String s : list1){
            System.err.println(s);
        }
    }
}
