package com.atguigu.interview2.aops;

import com.atguigu.interview2.annotations.MethodExporter;
import com.atguigu.interview2.annotations.MyRedisCache;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @auther zzyy
 * @create 2024-05-16 18:52
 */
@Aspect
@Component
@Slf4j
public class MethodExporterAspect
{
    //容器捞鱼，谁带着使用了MethodExporter注解将会触发Around业务逻辑
    @Around("@annotation(com.atguigu.interview2.annotations.MethodExporter)")
    public Object methodExporter(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        Object retValue = null;

        long startTime = System.currentTimeMillis();
        System.out.println("-----@Around环绕通知AAA-methodExporter");

        retValue = proceedingJoinPoint.proceed(); //放行

        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;

        //1 获得重载后的方法名
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();

        //2 确定方法名后获得该方法上面配置的注解标签MyRedisCache
        MethodExporter methodExporterAnnotation = method.getAnnotation(MethodExporter.class);

        if (methodExporterAnnotation != null)
        {
            //3 获得方法里面的形参信息
            StringBuffer jsonInputParam = new StringBuffer();
            Object[] args = proceedingJoinPoint.getArgs();
            DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
            String[] parameterNames = discoverer.getParameterNames(method);
            for (int i = 0; i < parameterNames.length; i++)
            {
                jsonInputParam.append(parameterNames[i] + "\t" + args[i].toString()+";");
            }
            //4 将返回结果retValue序列化
            String jsonResult = null;
            if(retValue != null){
                jsonResult = new ObjectMapper().writeValueAsString(retValue);
            }else{
                jsonResult = "null";
            }

            log.info("\n方法分析上报中 " +
                    "\n类名方法名:"+proceedingJoinPoint.getTarget().getClass().getName()+"."+proceedingJoinPoint.getSignature().getName()+"()"+
                    "\n执行耗时:"+costTime+"毫秒"+
                    "\n输入参数:"+jsonInputParam+""+
                    "\n返回结果:"+jsonResult+"" +
                    "\nover"
            );
            System.out.println("-----@Around环绕通知BBB-methodExporter");
        }

        return retValue;
    }
}

