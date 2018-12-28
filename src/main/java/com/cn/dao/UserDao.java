package com.cn.dao;

import com.cn.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
@Mapper
public interface UserDao {
    // ��ѯ�û��Ƿ����
    public User findUser(User user);

    // �����û�����ѯ�û�
    public User findByName(String username);

    // ����û�������0Ϊ��Ӳ��ɹ�
    public int addUser(@Param("username") String username, @Param("number") String number, @Param("password") String password);

    // ���������ѯ�Ƿ���ȷ
    public int findByPassword(String password);

    //��ѯ�����е��û�
    public ArrayList<User> findAllUser();

}
