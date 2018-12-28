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
    // ����ע��
    @Autowired
    private UserService userService;
    private UserDao userDao;

    //��ת����ҳ
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    //��ת����½ҳ��
    @RequestMapping("/login")
    public String toLogin() {
        return "login";
    }

    //ע���˻�
    @RequestMapping("logoff")
    public String logoff(HttpSession session) {
        session.invalidate();
        return "redirect:login";
    }

    // ��ת��ע��ҳ��
    @RequestMapping("/register")
    public String toRegister() {
        return "register";
    }

    //ͨ���û��������û���Ϣ
    @RequestMapping("/checkName")
    @ResponseBody
    public User register(String username) {
        User user2 = userService.findByName(username);
        return user2;
    }

    // У����֤���Ƿ���ȷ
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

    // �ж�ע����û�гɹ����ɹ�����ת��successҳ�棬ʧ����ת��failҳ��
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

    //����û����������Ƿ����
    @RequestMapping("/checkUser")
    @ResponseBody
    public int checkUser(String username, String password, HttpSession session) {
        password = MD5Utils.getMD5(password);
        User user = userService.findByName(username);
        int t = userService.findByPassword(password);
        // 0:�û������� 1�����벻���� 2���û�������ȷ
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
