package com.atguigu.interview2.aops;

import com.atguigu.interview2.annotations.MyRedisCache;
import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @auther zzyy
 * @create 2024-05-16 19:29
 */
@Component
@Aspect
public class MyRedisCacheAspect
{
    @Resource
    private RedisTemplate redisTemplate;

    //配置织入点
    @Pointcut("@annotation(com.atguigu.interview2.annotations.MyRedisCache)")
    public void cachePointCut(){}

    @Around("cachePointCut()")
    public Object doCache(ProceedingJoinPoint joinPoint)
    {
        Object result = null;


        /**
         *     @MyRedisCache(keyPrefix = "user",matchValue = "#id")
         *     public User getUserById(Integer id)
         *     {
         *         return userMapper.selectByPrimaryKey(id);
         *     }
         */


        try
        {
            //1 获得重载后的方法名
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();

            //2 确定方法名后获得该方法上面配置的注解标签MyRedisCache
            MyRedisCache myRedisCacheAnnotation = method.getAnnotation(MyRedisCache.class);

            //3 拿到了MyRedisCache这个注解标签，获得该注解上面配置的参数进行封装和调用
            String keyPrefix = myRedisCacheAnnotation.keyPrefix();
            String matchValueSpringEL = myRedisCacheAnnotation.matchValue();

            //4 SpringEL 解析器
            ExpressionParser parser = new SpelExpressionParser();
            Expression expression = parser.parseExpression(matchValueSpringEL);//#id
            EvaluationContext context = new StandardEvaluationContext();

            //5 获得方法里面的形参个数
            Object[] args = joinPoint.getArgs();
            DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
            String[] parameterNames = discoverer.getParameterNames(method);
            for (int i = 0; i < parameterNames.length; i++)
            {
                System.out.println("获得方法里参数名和值: "+parameterNames[i] + "\t" + args[i].toString());
                context.setVariable(parameterNames[i], args[i].toString());
            }

            //6 通过上述，拼接redis的最终key形式
            String key = keyPrefix + ":" + expression.getValue(context).toString();
            System.out.println("------拼接redis的最终key形式: " + key);

            //7 先去redis里面查询看有没有
            result = redisTemplate.opsForValue().get(key);
            if (result != null)
            {
                System.out.println("------redis里面有，我直接返回结果不再打扰mysql: " + result);
                return result;
            }

            //8 redis里面没有，去找msyql查询或叫进行后续业务逻辑
            //-------aop精华部分,才去找findUserById方法干活
            //userMapper.selectByPrimaryKey(id);
            result = joinPoint.proceed();//主业务逻辑查询mysql,放行放行放行

            //9 mysql步骤结束，还需要把结果存入redis一次，缓存补偿
            if (result != null)
            {
                System.out.println("------redis里面无，还需要把结果存入redis一次，缓存补偿: " + result);
                redisTemplate.opsForValue().set(key, result);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return result;
    }
}
