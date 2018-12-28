package com.cn.controller;

import com.cn.pojo.User;
import com.cn.pojo.role;
import com.cn.service.PowerService;
import com.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class PowerController {
    @Autowired
    private PowerService powerService;
    private UserService userService;

    //跳转用户权限修改
    @RequestMapping("/roleEdit")
    public ModelAndView roleEdit(HttpSession session) {
        User user3 = (User) session.getAttribute("USER");
        String username = user3.getUsername();
        ArrayList<String> list8 = powerService.getPermission(username);
        for (int i = 0; i < list8.size(); i++) {
            if ("管人".equals(list8.get(i))) {
                ModelAndView mv3 = new ModelAndView("allRole");
                //查出所有的用户
                ArrayList<User> list4 = userService.findAllUser();
                //查出所有的角色
                ArrayList<role> list6 = powerService.findAllRole();
                mv3.addObject("list6", list6);
                mv3.addObject("list4", list4);
                mv3.addObject("USER", username);
                return mv3;
            }
        }
        ModelAndView mv3 = new ModelAndView("NoPower");
        mv3.addObject("msg", "你没有权限修改，请联系管理提升你的权限！");
        return mv3;
    }

    //修改用户角色
    @RequestMapping("/editPower")
    @ResponseBody
    public int editPower(String username, String role) {
        int id = powerService.findIdByRole(role);
        return powerService.editPower(username, id);
    }

    //查询角色是否存在
    @RequestMapping("/checkRole")
    @ResponseBody
    public int checkRole(String role) {
        role r = powerService.checkRole(role);
        if (r != null) {
            return 1;
        } else {
            return 0;
        }
    }

    //创建新角色
    @RequestMapping("/creatNewRole")
    @ResponseBody
    public int creatNewRole(String role, @RequestParam(value = "str[]") String str[]) {
        //创建角色 t = 1表示创建成功
        int t = powerService.creatNewRole(role);
        //通过角色查询其相应id
        int id1 = powerService.findIdByRole(role);
        if (t != 0) {
            //循环遍历所选中的权限
            for (int i = 0; i < str.length; i++) {
                //通过权限查询相应id
                int id2 = powerService.findIdByPower(str[i]);
                //插入角色和权限的关联表中，id对应 k = 1 表示添加成功
                int k = powerService.creatNewRolePower(id1, id2);
                if (k == 0) {
                    return 0;
                }
            }
            return 1;
        }
        return 0;
    }

    //修改用户权限
    @RequestMapping("/changePermission")
    @ResponseBody
    public int changePermission(String role, @RequestParam(value = "str[]") String str[]) {
        int id_Role = powerService.findIdByRole(role);
        if (id_Role == 0) {
            return 0;
        } else {
            powerService.deleteAllRoleAndPowerById(id_Role);
            for (int i = 0; i < str.length; i++) {
                int id_permission = powerService.findPowerId(str[i]);
                int t = powerService.updateRolePower(id_Role, id_permission);
                if (t == 0) {
                    return 0;
                }
            }
            return 1;
        }
    }
}
