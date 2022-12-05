package com.atguigu.util;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.util.RedisTemplate
 */
@EnableScheduling
@EnableAsync
public class RedisUtil {

    //查找验证码
    public static Map<String, Object> findCode(String phone) {
        Jedis jedis = null;
        try {
            //从连接池获取Jedis对象
            JedisPool pool = JedisPoolUtil.getJedisPoolInstance();
            jedis = pool.getResource();

            //选择数据库
            jedis.select(0);

            //对redis进行操作
            //获取value的值
            String code = jedis.get(phone);
            //获取value的过期时间
            Long timeout = jedis.ttl(phone);

            //返回指定格式
            HashMap<String, Object> result = new HashMap<>();
            result.put("code",code);
            result.put("timeout",timeout);

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            jedis.close();
        }
        return null;
    }

    //插入验证码
    public static Boolean addCode(String phone, String code, Integer timeout) {
        Jedis jedis = null;
        try {
            //从连接池获取Jedis对象
            JedisPool pool = JedisPoolUtil.getJedisPoolInstance();
            jedis = pool.getResource();

            //选择数据库
            jedis.select(0);

            //通过代码对redis进行操作
            jedis.setex(phone, timeout, code);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            jedis.close();
        }
        return false;
    }

}