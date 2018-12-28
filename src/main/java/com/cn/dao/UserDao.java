package com.cn.dao;

import com.cn.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
@Mapper
public interface UserDao {
    // 查询用户是否存在
    public User findUser(User user);

    // 根据用户名查询用户
    public User findByName(String username);

    // 添加用户，返回0为添加不成功
    public int addUser(@Param("username") String username, @Param("number") String number, @Param("password") String password);

    // 根据密码查询是否正确
    public int findByPassword(String password);

    //查询出所有的用户
    public ArrayList<User> findAllUser();

}
