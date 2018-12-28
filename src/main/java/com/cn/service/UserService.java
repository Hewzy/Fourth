package com.cn.service;

import com.cn.pojo.*;
import java.util.ArrayList;

public interface UserService {
	// 根据用户名 密码查询用户
	public User findUser(String username, String password);
	// 根据用户名查询用户
	public User findByName(String username);
	// 添加用户，返回0为添加不成功
	public int addUser(String username, String number, String password);
	// 根据密码查询是否正确
	public int findByPassword(String password);
	//查询出所有的用户
	public ArrayList<User> findAllUser();

}
