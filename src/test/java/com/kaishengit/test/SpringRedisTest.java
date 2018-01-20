package com.kaishengit.test;

import com.alibaba.fastjson.JSON;
import com.kaishengit.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author NativeBoy
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class SpringRedisTest {


    @Autowired
    private JedisPool jedisPool;

    @Test
    public void save(){
        Jedis jedis = jedisPool.getResource();
        jedis.set("query","北京欢迎你");
    }

    @Test
    public void get(){
        Jedis jedis = jedisPool.getResource();
        String str = jedis.get("query");
        System.out.println(str);
    }

    @Test
    public void saveUser(){
        User user = new User(002,"rose","加利福尼亚-USA");
        Jedis jedis = jedisPool.getResource();
        jedis.set("user-002",user.toString());
    }

    @Test
    public void getUser(){
        Jedis jedis = jedisPool.getResource();
        String res = jedis.get("user-002");
        //User user = JSON.parseObject(res,User.class);
        System.out.println(res);
    }
}
