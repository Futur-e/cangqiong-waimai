package com.example.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.pojo.Result;
import com.example.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

import java.io.IOException;

@Slf4j
//@WebFilter("/*")
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//         获取url
        String url = req.getRequestURL().toString();
        log.info("url地址：{}", url);

//        判断请求url中是否包含login，包含是登陆操作
        if (url.contains("login")) {
            log.info("登录操作放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
//        获取请求投中的令牌
        String jwt = req.getHeader("token");
//        判断令牌是够存在
        if (jwt == null) {
            log.info("请求头为空");
            Result error = Result.error("NOT_LOGIN");
//            手动转换  对象--json ---------阿里巴巴
            String nologin = JSONObject.toJSONString(error);
            resp.getWriter().write(nologin);
            return;
        }
//        解析token
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失效，返回未登录错误信息");
            Result error = Result.error("MPT_LOGIN");
            resp.getWriter().write(error.getCode());
            return;
        }
//        放行
        log.info("令牌合法，放行");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
