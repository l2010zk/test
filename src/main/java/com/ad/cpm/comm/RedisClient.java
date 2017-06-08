package com.ad.cpm.comm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;

/**
 * Created by lzk on 2017/3/13.
 */
@Component
public class RedisClient {

    @Autowired
    private JedisPool jedisPool;

    public void set(String key, String value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        } finally {
            //返还到连接池
            jedis.close();
        }
    }

    public String get(String key) throws Exception  {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally {
            //返还到连接池
            jedis.close();
        }
    }

    /**
     * 获得列表数据
     * @param key
     * @return
     */
    public ArrayList<String> getList(String key ){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return (ArrayList<String>) jedis.lrange(key,0,100000000);
        } finally {
            //返还到连接池
            jedis.close();
        }
    }
}
