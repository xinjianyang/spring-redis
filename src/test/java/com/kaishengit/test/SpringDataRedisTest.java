package com.kaishengit.test;

import com.kaishengit.pojo.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author NativeBoy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class SpringDataRedisTest {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    private RedisTemplate<String,User> userRedisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, User> userRedisTemplate) {
        this.userRedisTemplate = userRedisTemplate;
        userRedisTemplate.setKeySerializer(new StringRedisSerializer());
        userRedisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
    }

    @Test
    public void save(){
        redisTemplate.opsForValue().set("name009","hello ,jack");
        Assert.assertEquals("hello ,jack",redisTemplate.opsForValue().get("name009"));
    }

    @Test
    public void saveUser(){
        User user = new User(1002,"rose009","北京");
        userRedisTemplate.opsForValue().set("user1012",user);
        User user1 = userRedisTemplate.opsForValue().get("user1012");
        System.out.println(user1);
    }
}
