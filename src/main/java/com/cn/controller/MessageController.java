package com.cn.controller;

import com.cn.pojo.User;
import com.cn.pojo.discuss;
import com.cn.pojo.message;
import com.cn.service.MessageServivce;
import com.cn.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class MessageController {

    @Autowired
    private MessageServivce messageServivce;
    private PowerService powerService;

    //跳转到添加消息页面
    @RequestMapping("/add")
    public String addMessage() {
        return "add";
    }

    //判断用户的权限跳转到不同的文章首页
    @RequestMapping("/messageAll")
    public ModelAndView handleMessage(HttpSession session) {
        System.out.println("09090909");
        User user2 = (User) session.getAttribute("USER");
        String username = user2.getUsername();
        System.out.println("666666"+username);
        //查询出用户所有的权限
        ArrayList<String> list8 = powerService.getPermission(username);
        //查出所有的文章
        ArrayList<message> list2 = this.messageServivce.findall();
        if (list8.contains("管人") || list8.contains("修改") && list8.contains("删除")) {
            ModelAndView mv = new ModelAndView("message");
            mv.addObject("list", list2);
            mv.addObject("userService", messageServivce);
            mv.addObject("USER", username);
            mv.addObject("HIDDEN", 0);
            return mv;
        } else {
            if (list8.contains("修改")) {
                ModelAndView mv = new ModelAndView("message");
                mv.addObject("list", list2);
                mv.addObject("userService", messageServivce);
                mv.addObject("USER", username);
                mv.addObject("HIDDEN", 1);
                return mv;
            } else if (list8.contains("删除")) {
                ModelAndView mv = new ModelAndView("message");
                mv.addObject("list", list2);
                mv.addObject("userService", messageServivce);
                mv.addObject("USER", username);
                mv.addObject("HIDDEN", 2);
                return mv;
            }
        }
        ModelAndView mv2 = new ModelAndView("normalMessage");
        mv2.addObject("userService", messageServivce);
        mv2.addObject("list3", list2);
        mv2.addObject("USER", username);
        return mv2;
    }
    //根据用户名查出对应的消息  @RequestParam("name") String name)
    @RequestMapping("/myMessage")
    public ModelAndView myMessage(HttpSession session) {
        User user = (User) session.getAttribute("USER");
        String username = user.getUsername();
        ArrayList<message> list2 = messageServivce.findMyMessage(username);
        ModelAndView mv = new ModelAndView("userMessage");
        mv.addObject("list2", list2);
        mv.addObject("userService", messageServivce);
        mv.addObject("USER", username);
        return mv;
    }

    //根据Id删除消息体内容，这是文章总部的
    @RequestMapping("/delete")
    public String deletMessage(int id) {
        messageServivce.delete(id);
        //根据文章id删除所有的评论
        messageServivce.deleteDiscuss(id);
        return "redirect:messageAll";
    }
    //根据Id删除消息体内容,这是个人页的
    @RequestMapping("/myDelete")
    public String myDelete(int id) {
        messageServivce.delete(id);
        //根据文章id删除所有的评论
        messageServivce.deleteDiscuss(id);
        return "redirect:myMessage";
    }

    //根据Id更新文章信息
    @RequestMapping("/update")
    public String update(int id, String title, String content, String details) {
        messageServivce.update(id, title, content, details);
        return "redirect:messageAll";
    }


    // 根据Id跳转到相应的编辑页面
    @RequestMapping("/editMessage")
    public ModelAndView editMessage(int id) {
        //传入数据到edit视图
        ModelAndView mv = new ModelAndView("edit");
        message mes = new message();
        mes = messageServivce.findMessageById(id);
        mv.addObject("mes", mes);
        return mv;
    }

    //根据id跳转到相应的消息编辑页面
    @RequestMapping("/addMessage")
    public String addMessage(String title, String content, String details, HttpSession session) {
        User user = (User) session.getAttribute("USER");
        String username = user.getUsername();
        messageServivce.addMessageById(username, title, content, details);
        return "redirect:myMessage";

    }

    //查询相应id的文章内容
    @RequestMapping("/detail")
    @ResponseBody
    public ModelAndView detail(int id, String details, String title, HttpSession session) {
        //根据Id查出所有的评论时间人等信息
        ArrayList<discuss> dis = messageServivce.findRemarkById(id);
        //查出现在的用户
        User user3 = (User) session.getAttribute("USER");
        String username = user3.getUsername();
        //跳转到文章详细页面
        ModelAndView mv3 = new ModelAndView("messageDetails");
        mv3.addObject("detail", details);
        mv3.addObject("title", title);
        mv3.addObject("discuss", dis);
        mv3.addObject("ID", id);
        mv3.addObject("USER", username);
        //将值存入session，方便刷新
        session.setAttribute("refresh1", details);
        session.setAttribute("refresh2", title);
        session.setAttribute("refresh3", id);
        return mv3;
    }

    //文章内容刷新页面
    @RequestMapping("/detailRefresh")
    public ModelAndView detailRefresh(HttpSession session) {
        //查出现在的用户
        User user3 = (User) session.getAttribute("USER");
        String username = user3.getUsername();
        //从session中取值刷新
        String details = (String) session.getAttribute("refresh1");
        String title = (String) session.getAttribute("refresh2");
        int id = (int) session.getAttribute("refresh3");
        //查询出所有的评论
        ArrayList<discuss> dis = messageServivce.findRemarkById(id);
        ModelAndView mv4 = new ModelAndView("messageDetails");
        mv4.addObject("detail", details);
        mv4.addObject("title", title);
        mv4.addObject("discuss", dis);
        mv4.addObject("ID", id);
        mv4.addObject("USER", username);
        return mv4;
    }

    //插入评论语和评论的人
    @RequestMapping("/insertRemark")
    @ResponseBody
    public int insertRemark(String remark, String title, int id, HttpSession session) {
        User user = (User) session.getAttribute("USER");
        String username = user.getUsername();
        int t = messageServivce.insertRemark(id, title, remark, username);
        return t;
    }
}
