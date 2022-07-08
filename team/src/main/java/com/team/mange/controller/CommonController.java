package com.team.mange.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.mange.common.Constant;
import com.team.mange.entity.User;
import com.team.mange.service.UserService;
import com.team.mange.utils.MD5util;
import com.team.mange.utils.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommonController extends BaseController {


    @Autowired
    private UserService userService;


    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "redirect:/team/index";
    }

    /**
     * 去登录
     *
     * @return
     */
    @RequestMapping("/signin")
    public String toLogin() {
        return "login";
    }

    /**
     * 登录后首页
     *
     * @return
     */
    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/signup")
    public String signup() {
        return "register";
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public R register(@RequestBody User user){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername());
        User db = userService.getOne(wrapper);
        if (db != null) {
            return R.error("用户名已存在");
        }

        QueryWrapper<User> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("phone", user.getPhone());
        User db1 = userService.getOne(wrapper1);
        if (db1 != null) {
            return R.error("手机号已存在");
        }
        user.setPassword(MD5util.getMD5Str(user.getPassword()));
        user.setRole(0);
        userService.save(user);
        return R.ok();
    }

    /**
     * 账号登录
     *
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public R login(HttpServletRequest request, String username, String password) {
        if (StringUtils.isEmpty(username)) {
            return R.error("账号不能为空");
        }
        if (StringUtils.isEmpty(password)) {
            return R.error("密码不能为空");
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        password=MD5util.getMD5Str(password);
        wrapper.eq("password", password);
        User user = userService.getOne(wrapper);
        if (user == null) {
            return R.error("账号或密码错误");
        }
        request.getSession().setAttribute(Constant.LOGIN_USER, user);
        request.getSession().setAttribute(Constant.ROLE, user.getRole());
        return R.ok();
    }

    /**
     * 手机号登录
     *
     * @return
     */
    @PostMapping("/loginByPhone")
    @ResponseBody
    public R loginByPhone(HttpServletRequest request, String phone, String password) {
        if (StringUtils.isEmpty(phone)) {
            return R.error("手机号不能为空");
        }
        if (StringUtils.isEmpty(password)) {
            return R.error("密码不能为空");
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone);
        password= MD5util.getMD5Str(password);
        wrapper.eq("password", password);
        User user = userService.getOne(wrapper);
        if (user == null) {
            return R.error("账号或密码错误");
        }
        request.getSession().setAttribute(Constant.LOGIN_USER, user);
        return R.ok();
    }

    @RequestMapping("/logout")
    @ResponseBody
    public R logout(HttpServletRequest request) {
        request.getSession().removeAttribute(Constant.LOGIN_USER);
        return R.ok();
    }


}
