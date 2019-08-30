package com.xp.ssm.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {

    private static JedisPool pool;
    static{
        String host = "212.64.122.214";
        int port = 6379;
        //连接池Redis POOL 基本配置信息
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(5);//最大连接数
        poolConfig.setMaxTotal(1);//最大空闲数
        //连接池
        pool = new JedisPool(poolConfig,host,port);
    }

    //获取jedis
    public static Jedis getJedis(){
        Jedis jedis = pool.getResource();
        jedis.auth("123456");
        return jedis;
    }

    //关闭redis
    public static void close(Jedis jedis){
        jedis.close();
    }
}
