package com.cn.controller;

import com.cn.dao.UserDao;
import com.cn.pojo.*;
import com.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;


@Controller
public class UserController {
    // 依赖注入
    @Autowired
    private UserService userService;
    private UserDao userDao;

    //跳转到首页
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    //跳转到登陆页面
    @RequestMapping("/login")
    public String toLogin() {
        return "login";
    }

    //注销账户
    @RequestMapping("logoff")
    public String logoff(HttpSession session) {
        session.invalidate();
        return "redirect:login";
    }

    // 跳转到注册页面
    @RequestMapping("/register")
    public String toRegister() {
        return "register";
    }

    //通过用户名查找用户信息
    @RequestMapping("/checkName")
    @ResponseBody
    public User register(String username) {
        User user2 = userService.findByName(username);
        return user2;
    }

    // 校验验证码是否正确
    @RequestMapping("/checkSafeCode")
    @ResponseBody
    public int checkSafeCode(String safeCode, HttpSession session) {
        String safeCode2 = (String) session.getAttribute("verifyCodeValue");
        int i = 0;
        if (safeCode.equals(safeCode2)) {
            i = 1;
            return i;
        }
        return i;
    }

    // 判断注册有没有成功，成功则跳转到success页面，失败跳转到fail页面
    @RequestMapping("/register01")
    public String register(String username, String number, String password) {
        password = MD5Utils.getMD5(password);
        if ((username == null || username == "") || (number == null || number == "") || (password == null || password == "")) {
            return "fail";
        } else {
            int t = userService.addUser(username, number, password);
            if (t != 0) {
                return "success";
            } else return "fail";
        }
    }

    //检查用户名和密码是否存在
    @RequestMapping("/checkUser")
    @ResponseBody
    public int checkUser(String username, String password, HttpSession session) {
        password = MD5Utils.getMD5(password);
        User user = userService.findByName(username);
        int t = userService.findByPassword(password);
        // 0:用户不存在 1：密码不存在 2：用户密码正确
        if (user == null) {
            return 0;
        } else if (t == 0) {
            return 1;
        } else {
            session.setAttribute("USER", user);
            return 2;
        }
    }
}
