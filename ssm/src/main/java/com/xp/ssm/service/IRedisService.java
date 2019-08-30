package com.xp.ssm.service;

import redis.clients.jedis.Jedis;

import java.util.Map;

public interface IRedisService {

     Jedis getResource();

     void returnResource(Jedis jedis);

     void set(String key, String value);

     String get(String key);

     boolean setHash(String key, Map<String,String> map);

     Map<String,String> getHash(String key);

}
