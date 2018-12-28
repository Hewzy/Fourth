package com.cn.service.serviceImpl;

import com.cn.dao.UserDao;
import com.cn.pojo.*;
import com.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;



@Service
@Transactional
public class UserServiceImpl implements UserService {
    // 注入UserDao
    @Autowired
    private UserDao userDao;

    // 通过用户名和密码查询用户
    public User findUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User retunUser = userDao.findUser(user);
        return retunUser;
    }

    //通过用户名查询用户并返回
    public User findByName(String username) {
        // TODO Auto-generated method stub
        User user = new User();
        user.setUsername(username);
        User user2 = userDao.findByName(username);
        return user2;
    }

    //传入用户名，手机号，密码，添加用户信息
    public int addUser(String username, String number, String password) {
        // TODO Auto-generated method stub
        int t = userDao.addUser(username, number, password);
        return t;
    }

    //通过密码查询是否存在，返回值用于判断
    public int findByPassword(String password) {
        int t = userDao.findByPassword(password);
        if (t != 0) {
            return 1;
        } else {
            return 0;
        }
    }



    //查询出所有的用户
    public ArrayList<User> findAllUser() {
        return userDao.findAllUser();
    }


}