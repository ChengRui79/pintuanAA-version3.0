package com.team.mange.config;


import com.team.mange.common.Constant;
import com.team.mange.common.exception.AuthException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author:
 * @desc: 登录拦截器
 * @Time: 2021/1/17  10:16
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        Object user = request.getSession().getAttribute(Constant.LOGIN_USER);
        if (user == null) {
            String XRequested = request.getHeader("X-Requested-With");
            if ("XMLHttpRequest".equals(XRequested)) {
                throw new AuthException("请登录");
            } else {
                response.setContentType("text/html");
                PrintWriter pw=response.getWriter();
                pw.write("<script>window.parent.location.href='/signin';</script>");
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }

}
