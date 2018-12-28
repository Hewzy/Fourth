package com.cn.service;

import com.cn.pojo.discuss;
import com.cn.pojo.role;

import java.util.ArrayList;
import java.util.Date;

public interface PowerService {
    //根据用户名查询所有的权限
    public ArrayList<String> getPermission(String username);
    //根据用户名查找相应角色的Id
    public int findIdBypermission(String permission);
    //查询角色是否存在
    public role checkRole(String role);
    //插入新的角色
    public int creatNewRole(String role);
    //根据角色查询相应的Id
    public int findIdByRole(String role);
    //根据下拉框的选择更改权限
    public int editPower(String username,int id);
    //根据权限查ID
    public int findIdByPower(String power);
    //添加新的角色权限关系
    public int creatNewRolePower(int id1, int id2);
    //查出所有的角色
    public ArrayList<role> findAllRole();
    //更改角色权限的关系
    public int updateRolePower(int id_role, int id_permission);
    //修改角色权限之前，这一步是先清空角色权限之前关系
    public void deleteAllRoleAndPowerById(int id_role);
    //根据用户权限找出相应的Id
    public int findPowerId(String permission);
}
