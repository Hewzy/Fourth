package com.cn.service.serviceImpl;

import com.cn.dao.MessageDao;
import com.cn.pojo.discuss;
import com.cn.pojo.message;
import com.cn.service.MessageServivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;

@Service
@Transactional
public class MessageServivceImpl implements MessageServivce {
    @Autowired
    private MessageDao messageDao;
    //列出所有的消息体消息内容
    public ArrayList<message> findall() {
        return (ArrayList<message>) this.messageDao.findall();
    }

    //根据id删除消息体内容
    public void delete(int id) {
        // TODO Auto-generated method stub
        messageDao.delete(id);
    }

    //根据文章id删除所有的评论
    public void deleteDiscuss(int id){ messageDao.deleteDiscuss(id); }

    //根据传入的参数修改消息体内容
    public void update(int id, String title, String content, String details) {
        // TODO Auto-generated method stub
        Date date = new Date();
        messageDao.update(id, title, content, details, date);
    }

    //根据文章标题查询所有的评论数
    public int findRemarksById(int id) {
        return messageDao.findRemarksById(id);
    }
    //根据文章查询最新的评论时间
    public Date findLastTime(int id) {
        return messageDao.findLastTime(id);
    }

    //根据id寻找评论
    public ArrayList<discuss> findRemarkById(int id) {
        return messageDao.findRemarkById(id);
    }
    //插入评论语和评论的人
    public int insertRemark(int id, String title, String remark, String username) {
        Date date = new Date();
        int t =  messageDao.insertRemark(id, title, remark, username, date);
        //根据id查找最新发评论时间
        Date date2 = messageDao.findLastTime(id);
        if (date2 != null) {
            messageDao.addLastTimeIntoMessage(id, date2);
        }
        return t;

    }
    //根据id查找消息体内容
    public message findMessageById(int id) {
        // TODO Auto-generated method stub
        return messageDao.findMessageById(id);
    }

    //添加文章
    public void addMessageById(String username, String title, String content, String details) {
        // TODO Auto-generated method stub
        Date date = new Date();
        messageDao.addMessageById(username, title, date, content, details);
    }


    //根据用户名查消息
    public ArrayList<message> findMyMessage(String username) {
        return (ArrayList<message>) this.messageDao.findMyMessage(username);
    }
}
