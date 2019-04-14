package com.easybuynet.dao.User;

import com.easybuynet.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao {
    //添加用户
    int addUser(User user) throws SQLException;

    //查询用户
    User login(String name, String pwd) throws SQLException;

    //根据用户名查找用户
    User getUserByName(String name) throws SQLException;

    //管理员登入
    User login(User user) throws SQLException;

    //分页获取用户信息
    List<User> getAllUser(User user, Integer curr, Integer pageSize, Boolean flag) throws SQLException;

    //修改冻结状态
    int updateUserIsLogin(Integer uid, Integer isLogin) throws SQLException;

    //修改删除权限
    int updateUserIsDelete(Integer uid, Integer isLogin) throws SQLException;

    //修改添加权限
    int updateUserIsAdd(Integer uid, Integer isLogin) throws SQLException;

    //修改权限
    int updateUserIsUpdate(Integer uid, Integer isLogin) throws SQLException;

    //根据邮箱修改密码
    int updatePwdByEmail(String email, String pwd, Integer is_admin) throws SQLException;

    //根据管理员id修改密码
    int updatePwdById(String pwd, Integer id) throws SQLException;

    //获取所有用户
    List<User> getAllUser(User user) throws SQLException;

    //根据id查询用户
    User getUserById(User user) throws SQLException;

    //删除用户
    int delUserById(User user) throws SQLException;
}
