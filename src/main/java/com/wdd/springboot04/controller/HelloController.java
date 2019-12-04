package com.wdd.springboot04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "hello web";
    }

    @RequestMapping("/success")
    public String success(){
        return "success";
    }
    @RequestMapping("/success2")
    public String success2(Map<String,Object> map){
        map.put("hello","<h1>你好</h1>");
        map.put("users", Arrays.asList("zhangsan,lisi,wangwu"));
        return "success";
    }
    /*@RequestMapping({"/","/index.html"})
    public  String index(){
        return "index";
    }*/
}
