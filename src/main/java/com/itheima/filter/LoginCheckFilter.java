package com.itheima.filter;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mqy
 * @version 1.0
 * @date 2023/12/12 21:35
 */
@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

//    @Override//初始化的方法，只会被调用一次
//    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("初始化了");
//    }

    @Override//拦截到请求后调用，调用多次
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        //1.获取请求的url
        String url = req.getRequestURL().toString();
        log.info("请求的url：{}", url);

        //2.判断请求url中是否包含login，如果包含，说明是登陆操作，就放行
        if (url.contains("login")) {
            log.info("登陆操作，放行");
            chain.doFilter(request, response);
            return;
        }

        //3.获取请求头中的令牌（token）
        String jwt = req.getHeader("token");

        //4.判断令牌是否存在，如果不存在，返回错误结果（未登陆）
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头为空，返回未登陆");
            Result error = Result.error("NOT_LOGIN");
            //手动转换 对象-->json -------alibaba jsonFAST
            String notLoginResult = JSONObject.toJSONString(error);
            resp.getWriter().write(notLoginResult);
            return;
        }

        //5.解析token，如果解析失败，也返回错误结果（未登陆）
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {//jwt解析失败
            e.printStackTrace();
            log.info("解析失败，返回未登陆");
            Result error = Result.error("NOT_LOGIN");
            //手动转换 对象-->json -------alibaba jsonFAST
            String notLoginResult = JSONObject.toJSONString(error);
            resp.getWriter().write(notLoginResult);
            return;
        }

        //6.放行
        log.info("令牌合法，放行");
        chain.doFilter(request, response);

    }

//    @Override//销毁方法，只会被调用一次
//    public void destroy() {
//        System.out.println("销毁了");
//    }
}
