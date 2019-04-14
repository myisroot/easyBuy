package com.easybuynet.service.User;

import com.easybuynet.entity.Result;
import com.easybuynet.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserService {
    //添加用户
    int addUser(User user) throws SQLException;

    //根据用户名获取用户
    Boolean getUserByName(String name) throws SQLException;

    //判断用户名是否登入成功
    User login(String name, String pwd) throws SQLException;

    //管理员登入
    User login(User user) throws SQLException;

    //分页获取用户
    List<User> getAllUser(User user, Integer curr, Integer pageSize) throws SQLException;

    //获取数量
    int getAllUserCount(User user) throws SQLException;

    //修改权限
    Result checkUser(String uid, String checkType, String aClass) throws SQLException;

    //根据邮箱修改密码
    int updatePwdByEmail(String email, String pwd) throws SQLException;

    //获取所有用户
    List<User> getAllUser(User user) throws SQLException;

    //根据id查询用户
    User getUserById(User user) throws SQLException;

    //删除用户
    int delUserById(User user) throws SQLException;

    //根据id修改密码
    int editPwdById(String pwd, Integer uid) throws SQLException;
}
