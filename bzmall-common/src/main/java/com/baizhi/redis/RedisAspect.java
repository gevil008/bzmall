package com.baizhi.redis;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Aspect
@Configuration
public class RedisAspect {
    @Autowired
    private RedisTemplate redisTemplate;

    @Around("@annotation(com.baizhi.redis.AddCacheAnnotation)")
    public Object addCache(ProceedingJoinPoint point) throws Throwable {
        /**
         * 获取key
         */
        String key = point.getTarget().getClass().getName();
        System.err.println("查询缓存");
        System.err.println(key);
        /**
         * 获取hashKey 方法名+形参
         */
        StringBuilder builder = new StringBuilder();
        Object[] args = point.getArgs();
        builder.append(point.getSignature().getName());
        for (Object arg:args){
            builder.append(arg);
        }
        String hashKey = builder.toString();
        System.err.println(hashKey);

        HashOperations opsForHash = redisTemplate.opsForHash();
        Object result = opsForHash.get(key, hashKey);
        if (result == null){
            System.err.println("查询数据库");
            /**
             * 缓存中没有数据 放行方法 并放入到缓存中
             */
            result = point.proceed();
            Map map = new HashMap<>();
            map.put(key,result);
            opsForHash.putAll(key, map);
            redisTemplate.expire(key,60, TimeUnit.SECONDS);
        }
        return result;
    }

    /**
     * 删除缓存
     */
    @Before("@annotation(com.baizhi.redis.DeleteCacheAnnotation)")
    public void deleteCache(JoinPoint joinPoint){
        /**
         * 获取key
         */
        String key = joinPoint.getTarget().getClass().getName();
        redisTemplate.delete(key);
    }
}
