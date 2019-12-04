package com.wdd.springboot04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    //可以使用Restful风格的注解
   // @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @PostMapping(value="/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map,
                        HttpSession session){
        if(!StringUtils.isEmpty(username) && "1".equals(password)){
            session.setAttribute("loginUser",username);
            //登录成功,为了防止表单重复提交，将重定向到dashboard
            return "redirect:/main.html";
        }else{
            //登录失败
            map.put("msg","用户名密码错误");
        }
       return "login";
    }
}
