package com.easybuynet.service.User;

import com.easybuynet.dao.User.IUserDao;
import com.easybuynet.dao.User.IUserDaoImpl;
import com.easybuynet.entity.Constants;
import com.easybuynet.entity.Result;
import com.easybuynet.entity.User;
import com.easybuynet.utils.Md5;
import com.easybuynet.web.pre.SessionMap;

import java.sql.SQLException;
import java.util.List;

public class IUserServiceImpl implements IUserService {
    private IUserDao iUserDao = new IUserDaoImpl();

    @Override
    public int addUser(User user) throws SQLException {
        return iUserDao.addUser(user);
    }

    @Override
    public Boolean getUserByName(String name) throws SQLException {
        return iUserDao.getUserByName(name) == null ? true : false;
    }

    @Override
    public User login(String name, String pwd) throws SQLException {
        return iUserDao.login(name, pwd);
    }

    @Override
    public User login(User user) throws SQLException {
        return iUserDao.login(user);
    }

    @Override
    public List<User> getAllUser(User user, Integer curr, Integer pageSize) throws SQLException {
        return iUserDao.getAllUser(user, curr, pageSize, true);
    }

    @Override
    public int getAllUserCount(User user) throws SQLException {
        return iUserDao.getAllUser(user, 0, 0, false).size();
    }

    @Override
    public Result checkUser(String uid, String checkType, String aClass) throws SQLException {
        checkType = checkType.trim();
        Result result = new Result();
        if (null == uid || "".equals(uid) || null == aClass || "".equals(aClass)) {
            return result.Fail("选择用户时发生异常");
        }
        User u = new User();
        u.setU_id(Integer.parseInt(uid));
        User userById = iUserDao.getUserById(u);
        if (Constants.ROOT.equals(userById.getU_isadmin())) {
            return result.Fail("不能修改超级管理员权限");
        }
        if ("user".equals(checkType)) {
            int i = iUserDao.updateUserIsLogin(Integer.parseInt(uid), Integer.parseInt(aClass));
            if (i <= 0) {
                return result.Fail("发生异常");
            }
        } else if ("del".equals(checkType)) {
            int i = iUserDao.updateUserIsDelete(Integer.parseInt(uid), Integer.parseInt(aClass));
            if (i <= 0) {
                return result.Fail("发生异常");
            }
        } else if ("add".equals(checkType)) {
            int i = iUserDao.updateUserIsAdd(Integer.parseInt(uid), Integer.parseInt(aClass));
            if (i <= 0) {
                return result.Fail("发生异常");
            }
        } else if ("update".equals(checkType)) {
            int i = iUserDao.updateUserIsUpdate(Integer.parseInt(uid), Integer.parseInt(aClass));
            if (i <= 0) {
                return result.Fail("发生异常");
            }
        } else if ("setLogin".equals(checkType)) {
            int i = iUserDao.updateUserIsLogin(Integer.parseInt(uid), Integer.parseInt(aClass));
            if (i <= 0) {
                return result.Fail("发生异常");
            }
            User user = new User();
            user.setU_id(Integer.parseInt(uid));
            SessionMap.moveAdminSession(user);
        }
        return result.Success();
    }

    @Override
    public int updatePwdByEmail(String email, String pwd) throws SQLException {
        return iUserDao.updatePwdByEmail(email, Md5.toMD5(pwd), Constants.USER);
    }

    @Override
    public List<User> getAllUser(User user) throws SQLException {
        user.setU_isadmin(Constants.USER);
        return iUserDao.getAllUser(user);
    }

    @Override
    public User getUserById(User user) throws SQLException {
        return iUserDao.getUserById(user);
    }

    @Override
    public int delUserById(User user) throws SQLException {
        return iUserDao.delUserById(user);
    }

    @Override
    public int editPwdById(String pwd, Integer uid) throws SQLException {
        return iUserDao.updatePwdById(pwd, uid);
    }
}
