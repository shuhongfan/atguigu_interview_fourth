package com.atguigu.interview2.junit.auto;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther zzyy
 * @create 2024-05-05 10:05
 *
 * Junit+反射+注解浅谈自动测试框架设计
 *
 * 需求
 * 1 我们自定义注解@AtguiguTest
 * 2 将注解AtguiguTest加入需要测试的方法
 * 3 类AutoTestClient通过反射检查，哪些方法头上标注了@AtguiguTest注解会自动进行单元测试
 */
@Slf4j
public class AutoTestClient
{
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException
    {
        //家庭作业，抽取一个方法，（class，p....）
        CalcHelpDemo calcHelpDemo = new CalcHelpDemo();
        int para1 = 10;
        int para2 = 0;


        Method[] methods = calcHelpDemo.getClass().getMethods();
        AtomicInteger bugCount = new AtomicInteger();
        // 要写入的文件路径（如果文件不存在，会创建该文件）
        String filePath = "BugReport"+ (DateUtil.format(new Date(), "yyyyMMddHHmmss"))+".txt";


        for (int i = 0; i < methods.length; i++)
        {
            if (methods[i].isAnnotationPresent(AtguiguTest.class))
            {
                try
                {
                    methods[i].invoke(calcHelpDemo,para1,para2);//放行
                } catch (Exception e) {
                    bugCount.getAndIncrement();
                    log.info("异常名称:{},异常原因:{}",e.getCause().getClass().getSimpleName(),e.getCause().getMessage());


                    FileUtil.writeString(methods[i].getName()+"\t"+"出现了异常"+"\n", filePath, "UTF-8");
                    FileUtil.appendString("异常名称:"+e.getCause().getClass().getSimpleName()+"\n", filePath, "UTF-8");
                    FileUtil.appendString("异常原因:"+e.getCause().getMessage()+"\n", filePath, "UTF-8");
                }finally {
                    FileUtil.appendString("异常数:"+bugCount.get()+"\n", filePath, "UTF-8");
                }
            }
        }
    }
}




/**
 * 在Hutool工具包中，使用FileUtil类进行文件操作时，通常不需要显式地“关闭”文件。
 * 这是因为Hutool在内部处理文件I/O时，已经考虑了资源的自动管理和释放。
 *
 * 具体来说，当你使用FileUtil的静态方法（如writeString、appendString、readUtf8String等）时，
 * 这些方法会在执行完毕后自动关闭与文件相关的流和资源。因此，你不需要（也不能）
 * 调用类似于close这样的方法来关闭文件。
 *
 * 这是因为这些静态方法通常使用Java的try-with-resources语句或其他类似的机制来确保资源在
 * 不再需要时得到释放。try-with-resources是Java 7及更高版本中引入的一个特性，
 * 它允许你在try语句块结束时自动关闭实现了AutoCloseable或Closeable接口的资源。
 *
 * 所以，当你使用Hutool的FileUtil类进行文件操作时，你可以放心地编写代码，
 * 而无需担心资源泄露或忘记关闭文件的问题。Hutool已经为你处理了这些细节。
 */