package com.atguigu.interview2.controller;

import com.atguigu.interview2.annotations.MethodExporter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @auther zzyy
 * @create 2024-05-16 18:55
 */
@RestController
@Slf4j
public class MethodExporterController
{

    //http://localhost:24618/method/list?page=1&rows=7
    @GetMapping(value = "/method/list")
    @MethodExporter
    public Map list(@RequestParam(value = "page",defaultValue = "1") int page,
                    @RequestParam(value = "rows",defaultValue = "5") int rows)
    {

        Map<String,String> result = new LinkedHashMap<>();
        result.put("code","200");
        result.put("message","success");

        //暂停毫秒
        try { TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000)); } catch (InterruptedException e) { e.printStackTrace(); }

        return result;
    }

    @MethodExporter
    @GetMapping(value = "/method/get")
    public Map get()
    {
        Map<String,String> result = new LinkedHashMap<>();
        result.put("code","404");
        result.put("message","not-found");

        //暂停毫秒
        try { TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000)); } catch (InterruptedException e) { e.printStackTrace(); }

        return result;
    }

    @GetMapping(value = "/method/update")
    public String update()
    {
        System.out.println("update method without     @MethodExporter");
        return "ok update";
    }

}
