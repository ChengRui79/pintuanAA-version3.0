package com.team.mange.controller;


import com.team.mange.common.Constant;
import com.team.mange.entity.User;
import com.team.mange.utils.ServletUtils;

import javax.servlet.http.HttpServletRequest;
/**
 * 基础controller 获取登录用户信息
 * @author
 */
public class BaseController {

    /**
     * 获取request
     */
    public HttpServletRequest getRequest() {
        return ServletUtils.getRequest();
    }

    public User getUser(){
        User user = (User) getRequest().getSession().getAttribute(Constant.LOGIN_USER);
        return user;
    }

    public Integer getUserId() {
        User user = getUser();
        return user !=null ? Integer.valueOf(user.getId()) : null;
    }

}
