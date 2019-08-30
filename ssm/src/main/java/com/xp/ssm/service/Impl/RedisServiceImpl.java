package com.xp.ssm.service.Impl;

import com.xp.ssm.service.IRedisService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@Service
public class RedisServiceImpl implements IRedisService {

    private static Logger logger = Logger.getLogger(RedisServiceImpl.class);

    @Autowired
    private JedisPool jedisPool;    //jedisPool不属于springboot框架支持的redis类,所以需要自行注入到spring中。
                                    // 通过配置类RedisConfig类注入的

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Override
    public Jedis getResource() {
        return jedisPool.getResource();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void returnResource(Jedis jedis) {
        if(jedis != null){
            jedisPool.returnResourceObject(jedis);
        }
    }

    @Override
    public void set(String key, String value) {
        Jedis jedis=null;
        try{
            jedis = getResource();
            jedis.set(key, value);
            logger.info("Redis set success - " + key + ", value:" + value);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + value);
        }finally{
            returnResource(jedis);
        }
    }

    @Override
    public String get(String key) {
//        String result = null;
//        Jedis jedis=null;
//        try{
//            jedis = getResource();
//            if(jedis.exists(key)){
//                result = jedis.get(key);
//            }
//            logger.info("Redis get success - " + key + ", value:" + result);
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + result);
//        }finally{
//            returnResource(jedis);
//        }
//        return result;
//        redisTemplate.setKeySerializer();
        ValueOperations<String, String> string = redisTemplate.opsForValue();
        if (redisTemplate.hasKey(key)){
            System.err.println("在redis中取出的数据");
            return string.get(key);
        } else {
            String str = "练习";
            string.set(key,str);
            System.err.println("在MySql中取出的数据");
            return str;
        }
    }

    @Override
    public boolean setHash(String key, Map<String, String> map) {
        Jedis jedis=null;
        try {
            jedis = getResource();
            jedis.hmset(key,map);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
        return true;
    }

    @Override
    public Map<String, String> getHash(String key) {
        Map<String, String> map  = null;
        Jedis jedis=null;
        try{
            jedis = getResource();
            if(jedis.exists(key)){
                map = jedis.hgetAll(key);
            }
            //logger.info("Redis get success - " + key + ", value:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + result);
        }finally{
            returnResource(jedis);
        }
        return map;
    }

}
