package com.itheima.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mqy
 * @version 1.0
 * @date 2023/12/13 10:07
 */
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override//目标资源方法运行前运行，true:放行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

        //1.获取请求的url
        String url = req.getRequestURL().toString();
        log.info("请求的url：{}", url);

        //2.判断请求url中是否包含login，如果包含，说明是登陆操作，就放行
        if (url.contains("login")) {
            log.info("登陆操作，放行");
            return true;
        }

        //3.获取请求头中的令牌（token）
        String jwt = req.getHeader("token");

        //4.判断令牌是否存在，如果不存在，返回错误结果（未登陆）
        if (!StringUtils.hasLength(jwt)) {//--判断null和空串情况
            log.info("请求头为空，返回未登陆");
            Result error = Result.error("NOT_LOGIN");
            //前端要求返回JSON格式
            //手动转换 对象-->json -------alibaba jsonFAST
            String notLoginResult = JSONObject.toJSONString(error);
            resp.getWriter().write(notLoginResult);
            return false;
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
            return false;
        }

        //6.放行
        log.info("令牌合法，放行");
        return true;


    }

    //目标资源方法运行后运行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandler");
    }

    //视图渲染完毕后运行，最后运行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterHandler");
    }
}
