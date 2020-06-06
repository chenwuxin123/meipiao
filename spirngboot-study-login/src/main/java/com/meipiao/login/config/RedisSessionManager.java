package com.meipiao.login.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Author: Chenwx
 * @Date: 2020/6/6 9:43
 */
@Configuration
@EnableRedisHttpSession(redisNamespace = "redis:session")     //开启全局redis session 管理
public class RedisSessionManager {

}
