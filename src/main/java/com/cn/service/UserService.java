package com.cn.service;

import com.cn.pojo.*;
import java.util.ArrayList;

public interface UserService {
	// �����û��� �����ѯ�û�
	public User findUser(String username, String password);
	// �����û�����ѯ�û�
	public User findByName(String username);
	// ����û�������0Ϊ��Ӳ��ɹ�
	public int addUser(String username, String number, String password);
	// ���������ѯ�Ƿ���ȷ
	public int findByPassword(String password);
	//��ѯ�����е��û�
	public ArrayList<User> findAllUser();

}
