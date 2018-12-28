package com.cn.dao;

import com.cn.pojo.discuss;
import com.cn.pojo.message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Date;

@Repository
@Mapper
public interface MessageDao {
    // 查询所有的消息体消息
    public ArrayList<message> findall();

    // 根据id删除消息体的消息
    public void delete(int id);

    //根据文章id删除所有的评论
    public void deleteDiscuss(int id);

    //// 根据id更新消息体的消息内容
    public void update(@Param("id") int id, @Param("title") String title, @Param("content") String content, @Param("details") String details, @Param("date") Date date);

    //根据id返回消息体的消息
    public message findMessageById(int id);

    //根据用户名查消息
    public ArrayList<message> findMyMessage(String username);

    //添加消息内容
    public void addMessageById(@Param("username") String username, @Param("title") String title, @Param("date") Date date, @Param("content") String content, @Param("details") String details);

    //根据id寻找评论
    public ArrayList<discuss> findRemarkById(int id);

    //根据id查询所有的评论数
    public int findRemarksById(int id);

    //根据id查询最新的评论时间
    public Date findLastTime(int id);

    //插入评论语和评论的人
    public int insertRemark(@Param("id") int id, @Param("title") String title, @Param("remark") String remark, @Param("username") String username, @Param("date") Date date);


    //根据用户名和时间内查询相应的
    public int findIdByUsernameAndDate(@Param("username") String username, @Param("date") Date date);

    //新增添加最新评论时间插入文章表
    public void addLastTimeIntoMessage(@Param("id") int id, @Param("date2") Date date2);
}
