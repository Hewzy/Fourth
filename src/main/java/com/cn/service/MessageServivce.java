package com.cn.service;

import com.cn.pojo.discuss;
import com.cn.pojo.message;

import java.util.ArrayList;
import java.util.Date;

public interface MessageServivce {
    // 查询所有的消息体消息
    public ArrayList<message> findall();
    // 根据id删除消息体的消息
    public void delete(int id);
    //// 根据id更新消息体的消息内容
    public void update(int id, String title, String content, String details);
    //根据文章id删除所有的评论
    public void deleteDiscuss(int id);
    //根据id返回消息体的消息
    public message findMessageById(int id);
    //添加消息内容
    public void addMessageById(String username, String title, String content, String details);
    //根据id寻找评论
    public ArrayList<discuss> findRemarkById(int id);
    //根据文章标题查询所有的评论数
    public int findRemarksById(int id);
    //根据id查询最新的评论时间
    public Date findLastTime(int id);
    //插入评论语和评论的人
    public int insertRemark(int id, String title, String remark, String username);
    //根据用户名查出消息
    public ArrayList<message> findMyMessage(String username);

}
