package com.example.controller;

import com.example.pojo.Result;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SessionController {
//    设置Cookie
    @GetMapping("/c1")
    public Result cookei(HttpServletResponse response){
        response.addCookie(new Cookie("login_username","zhangzhang"));//设置Cookie
        return Result.success();
    }
//    获取Cookie
    @GetMapping("/c2")
    public Result cookie2(HttpServletRequest request){
//        获取所有cookie
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if (cookie.getName().equals("login_username")){
                System.out.println("Login_username" +cookie.getValue());
            }
        }
        return Result.success();
    }

    @GetMapping("/s1")
    public Result session1(HttpSession session){
        session.setAttribute("loginUser","zhang");
        return Result.success();
    }
    @GetMapping("/s2")
    public Result session2(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        Object loginUser = httpSession.getAttribute("loginUser");
        return Result.success(loginUser);
    }
}
