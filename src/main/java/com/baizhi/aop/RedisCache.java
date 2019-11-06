package com.baizhi.aop;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.annotation.ClearRedisCache;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Method;
import java.util.Set;

@Configuration
@Aspect
public class RedisCache {
    @Autowired
    private Jedis jedis;
    //@Around("execution(* com..baizhi.service.*.findAll(..))")
   // public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    /*    //判断注解是否存在
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        boolean b = method.isAnnotationPresent(com.baizhi.annotation.RedisCache.class);
        System.out.println("b:"+b);
        Object result = null;
        if(b){
            //存在注解
            StringBuilder sb = new StringBuilder();
            //获取类的名称
            String className = proceedingJoinPoint.getTarget().getClass().getName();
           //获取方法的名称
            String methodName = proceedingJoinPoint.getSignature().getName();
            //获取参数实参
            Object[] args = proceedingJoinPoint.getArgs();
            sb.append(className).append(".").append(methodName).append("(");
            for (int i = 0; i < args.length; i++) {
                sb.append(args[i]);
                if(i == args.length-1){
                    break;
                }
                sb.append(",");

            }
            sb.append(")");
            for (Object arg : args) {
                sb.append(arg);
            }
            String key = sb.toString();
            System.out.println("key:"+key);
            //判断redis中是否含有这个key
            if(jedis.exists(key)){
                //缓存中存在该key
                String json = jedis.get(key);
                result = JSONObject.parse(json);
            }else {
                //缓存中不含有该key，放行方法
                result = proceedingJoinPoint.proceed();
                //将查询的结果放入到redis中缓存起来
                jedis.set(key,JSONObject.toJSONString(result));
            }
            jedis.close();
            return result;
       }else {
            //不存在注解
            result = proceedingJoinPoint.proceed();
        }
        jedis.close();
        return result;
    }

    //执行增删改方法之后清除当前业务层的查询缓存
    @After("execution(* com.baizhi.service.*.*(..))&&!execution(* com.baizhi.service.*.findAll(..))")
    public void after(JoinPoint joinPoint){
        Object target = joinPoint.getTarget();
        String className = target.getClass().getName();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        boolean b = method.isAnnotationPresent(ClearRedisCache.class);
        if(b){
            //获取redis缓存中的所有key
            Set<String> keys = jedis.keys("*");
            for (String key : keys) {
                //当前遍历的key 包含当前执行的目标方法的全类名
                if(key.startsWith(className)){
                    jedis.del(key);
                }
            }
            jedis.close();

        }

    }
*/
}
