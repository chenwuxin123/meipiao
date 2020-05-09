package com.meipiao.controller;

import com.meipiao.redis.RedisUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @Author: Chenwx
 * @Date: 2020/5/8 15:45
 */
@RestController
public class RedisController {
    private static int ExpireTime = 10;   // redis中存储的过期时间10s

    @Resource
    RedisUtil redisUtil;

    @RequestMapping("/save")
    public String save(String key, String value) {
        redisUtil.lSet(key, value);

        return "success";
    }

    @GetMapping("/test/{key}")
    public String test(@PathVariable String key){
        String data = null;
        List<Object> objects = redisUtil.lGet(key, 1, 1);
        for (Object object : objects) {
            data = object.toString();
        }
        return data;
    }

    @GetMapping("/get/{key}")
    public String get(@PathVariable("key") String key) {
        Set<Object> data = redisUtil.sGet(key);
        for (Object datum : data) {
            System.out.println("set中的数据为" + datum);
        }
        return "data";
    }


    @DeleteMapping("/delete/{key}/{f}")
    public String delete(@PathVariable("key") String key,
                         @PathVariable("f") String f) {
        redisUtil.hdel(key, f);
        return "delete";
    }

    @GetMapping("/getSize/{key}")
    public String sGetSetSize(@PathVariable("key") String key) {
        long size = redisUtil.sGetSetSize(key);
        System.err.println("长度为" + size);
        return "success";
    }
}
