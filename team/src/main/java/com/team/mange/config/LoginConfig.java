package com.team.mange.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;


/***
 *
 * @Description: 登录拦截器配置
 * @author:
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {
    /***不拦截路径*/
    private static final List<String> EXCLUDE_PATH = Arrays.asList("/static/**","/","/signin","/signup","/register",
            "/login", "/logout", "/loginByPhone", "/team/index", "/team/listByPage",
            "/joinRecord/index", "/joinRecord/listByPage"
    );

    @Override
    public void addInterceptors(InterceptorRegistry registry)  {
        //注册TestInterceptor拦截器
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_PATH);
    }
}
