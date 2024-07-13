package com.atguigu.interview2.mytest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @auther zzyy
 * @create 2024-05-24 19:52
 */
@Slf4j
public class SpringELDemo
{
    public static void main(String[] args)
    {

        //1 log日志占位符{}替换
        log.info("log:{}","abcd");//redis - user:{}
        System.out.println();




        //2 String.format占位符替换
        String result = String.format("%s,java","尚硅谷 study");
        System.out.println(result);
        System.out.println();

        //3 SpringELExpress表达式,#号后面的内容可以被具体值替换
        String var = "#userid";

        SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
        Expression expression = spelExpressionParser.parseExpression(var);

        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("userid","1253");

        String s = expression.getValue(context).toString();

        System.out.println(s);


    }
}
