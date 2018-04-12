package com.wowoduble.springbootdemo.redis;

import com.wowoduble.springbootdemo.config.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RedisDemo {

    @Autowired
    private RedisConfig redisConfig;

    @RequestMapping("/redis")
    @ResponseBody
    public String  redisDemo(String[] args) {
        System.out.println(redisConfig);
        Jedis jedis = new Jedis(
                redisConfig.getHost(), redisConfig.getPort());
        System.out.println(jedis);
        String j = jedis.set("hellojedis","hellojedis");
        String r = jedis.get("hellojedis");

        Map<String,String> map = new HashMap<>();
        map.put("test1","123");
        map.put("test2","456");
        jedis.hmset("testhashmap",map);


        return j+"=="+r ;
    }


}
