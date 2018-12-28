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
    // ע��UserDao
    @Autowired
    private UserDao userDao;

    // ͨ���û����������ѯ�û�
    public User findUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User retunUser = userDao.findUser(user);
        return retunUser;
    }

    //ͨ���û�����ѯ�û�������
    public User findByName(String username) {
        // TODO Auto-generated method stub
        User user = new User();
        user.setUsername(username);
        User user2 = userDao.findByName(username);
        return user2;
    }

    //�����û������ֻ��ţ����룬����û���Ϣ
    public int addUser(String username, String number, String password) {
        // TODO Auto-generated method stub
        int t = userDao.addUser(username, number, password);
        return t;
    }

    //ͨ�������ѯ�Ƿ���ڣ�����ֵ�����ж�
    public int findByPassword(String password) {
        int t = userDao.findByPassword(password);
        if (t != 0) {
            return 1;
        } else {
            return 0;
        }
    }



    //��ѯ�����е��û�
    public ArrayList<User> findAllUser() {
        return userDao.findAllUser();
    }


}