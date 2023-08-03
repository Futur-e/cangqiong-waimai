package com.example.intercepetor;

import com.alibaba.fastjson.JSONObject;
import com.example.pojo.Result;
import com.example.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Properties;

@Component
@Slf4j
public class LoginCheckIntercepetor implements HandlerInterceptor {
    @Override
//    目标资源方法运行钱运行，返回true放行，反之不放行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
//         获取url
        String url = req.getRequestURL().toString();
        log.info("url地址：{}", url);

//        判断请求url中是否包含login，包含是登陆操作
        if (url.contains("login")) {
            log.info("登录操作放行");
            return true;
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
            return false;
        }
//        解析token
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失效，返回未登录错误信息");
            Result error = Result.error("NOT_LOGIN");
            resp.getWriter().write(error.getCode());
            return false;
        }
//        放行
        return true;
    }

    @Override
//    目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
//    视图渲染完毕后运行，最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
