package com.cn.service.serviceImpl;

import com.cn.dao.PowerDao;
import com.cn.dao.UserDao;
import com.cn.pojo.discuss;
import com.cn.pojo.role;
import com.cn.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class PowerServiceImpl implements PowerService {
    @Autowired
    private PowerDao powerDao;
    //根据用户名查询所有的权限
    public ArrayList<String> getPermission(String username) {
        return powerDao.getPermission(username);
    }

    //根据用户名查找相应角色的Id
    public int findIdBypermission(String permission) {
        return powerDao.findIdBypermission(permission);
    }

    //查询角色是否存在
    public role checkRole(String role) {
        return powerDao.checkRole(role);
    }

    //插入新的角色
    public int creatNewRole(String role) {
        return powerDao.creatNewRole(role);
    }

    //根据角色查询相应的Id
    public int findIdByRole(String role) {
        return powerDao.findIdByRole(role);
    }

    //根据下拉框的选择更改权限
    public int editPower(String username, int id) {
        return powerDao.editPower(username, id);
    }

    //根据权限查ID
    public int findIdByPower(String power) {
        return powerDao.findIdByPower(power);
    }


    //添加新的角色权限关系
    public int creatNewRolePower(int id1, int id2) {
        return powerDao.creatNewRolePower(id1, id2);
    }

    //查出所有的角色
    public ArrayList<role> findAllRole() {
        return powerDao.findAllRole();
    }

    //更改角色权限的关系
    public int updateRolePower(int id_role, int id_permission) {
        return powerDao.updateRolePower(id_role, id_permission);
    }
    //修改角色权限之前，这一步是先清空角色权限之前关系
    public void deleteAllRoleAndPowerById(int id_role){
        powerDao.deleteAllRoleAndPowerById(id_role);
    }

    //根据用户权限朝找相应的Id
    public int findPowerId(String permission){
        return powerDao.findPowerId(permission);
    }

}
