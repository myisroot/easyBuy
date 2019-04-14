package com.easybuynet.dao.User;

import com.easybuynet.entity.Constants;
import com.easybuynet.entity.User;
import com.easybuynet.utils.JdbcUtils;
import com.easybuynet.utils.SqlParam;
import com.easybuynet.utils.SqlUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class IUserDaoImpl implements IUserDao {
    private QueryRunner qr = JdbcUtils.getQueryRunner();
    private SqlUtil jdbcUtils = JdbcUtils.getSqlUtil();

    /**
     * 添加用户
     * Test
     *
     * @param user 用户对象
     * @return 受影响的行数 int
     * @throws SQLException
     */
    @Override
    public int addUser(User user) throws SQLException {
        return qr.update("insert into user values (?,?,?,?,?,?,?,?,?,?,-1,-1,-1,1);",
                user.getU_id(), user.getU_loginname(), user.getU_pwd(), user.getU_email(), user.getU_phone(),
                user.getU_isadmin(), user.getU_sex(), user.getU_name(), user.getU_identitycods(), user.getU_createtime());
    }

    /**
     * 登入
     *
     * @param name 用户名
     * @param pwd  密码
     * @return 用户对象User
     * @throws SQLException
     */
    @Override
    public User login(String name, String pwd) throws SQLException {
        return qr.query("select * from user where u_loginname=? and u_pwd=? and u_isadmin=-1", new BeanHandler<User>(User.class), name, pwd);
    }

    /**
     * 根据用户名查找用户
     *
     * @param name 用户名
     * @return User
     * @throws SQLException
     */
    @Override
    public User getUserByName(String name) throws SQLException {
        return qr.query("select  * from user where u_loginname=? and u_isadmin=-1", new BeanHandler<User>(User.class), name);
    }


    @Override
    public User login(User user) throws SQLException {
        StringBuffer sql = jdbcUtils.getSql(user, SqlParam.SELECT);
        List<Object> sqlParams = jdbcUtils.getSqlParams(user, SqlParam.SELECT);
        sql.append(" and u_isadmin !=-1");
        return qr.query(sql.toString(), new BeanHandler<>(User.class), sqlParams.toArray());
    }

    @Override
    public List<User> getAllUser(User user, Integer curr, Integer pageSize, Boolean flag) throws SQLException {
        Integer u_isadmin = user.getU_isadmin();
        StringBuffer s = new StringBuffer(" ");
        if (!Constants.USER.equals(u_isadmin)) {
            user.setU_isadmin(null);
            if (null == user.getU_loginname()) {
                s = s.append(" where u_isadmin != " + Constants.USER);
            } else {
                s = s.append(" and u_isadmin != " + Constants.USER);
            }
        }
        StringBuffer sql = jdbcUtils.getSql(user, SqlParam.SELECT);
        List<Object> sqlParams = jdbcUtils.getSqlParams(user, SqlParam.SELECT);
        sql.append(s);
        if (flag) {
            sql.append("  limit ? ,?;");
            sqlParams.add((curr - 1) * pageSize);
            sqlParams.add(pageSize);
        }
        return qr.query(sql.toString(), new BeanListHandler<>(User.class), sqlParams.toArray());
    }

    @Override
    public int updateUserIsLogin(Integer uid, Integer isLogin) throws SQLException {
        StringBuffer sql = new StringBuffer("update user set u_islogin =? where u_id=?");
        return qr.update(sql.toString(), isLogin, uid);
    }

    @Override
    public int updateUserIsDelete(Integer uid, Integer isLogin) throws SQLException {
        StringBuffer sql = new StringBuffer("update user set u_isdelete =? where u_id=?");
        return qr.update(sql.toString(), isLogin, uid);
    }

    @Override
    public int updateUserIsAdd(Integer uid, Integer isLogin) throws SQLException {
        StringBuffer sql = new StringBuffer("update user set u_isadd =? where u_id=?");
        return qr.update(sql.toString(), isLogin, uid);
    }

    @Override
    public int updateUserIsUpdate(Integer uid, Integer isLogin) throws SQLException {
        StringBuffer sql = new StringBuffer("update user set u_isupdate =? where u_id=?");
        return qr.update(sql.toString(), isLogin, uid);
    }

    @Override
    public int updatePwdByEmail(String email, String pwd, Integer is_admin) throws SQLException {
        StringBuffer sql = new StringBuffer("update user set u_pwd =? where u_phone= ? and u_isadmin=?");
        return qr.update(sql.toString(), pwd, email, is_admin);
    }

    @Override
    public int updatePwdById(String pwd, Integer id) throws SQLException {
        StringBuffer sql = new StringBuffer("update user set u_pwd =? u_id=?");
        return qr.update(sql.toString(), pwd, id);
    }

    @Override
    public List<User> getAllUser(User user) throws SQLException {
        StringBuffer sql = jdbcUtils.getSql(user, SqlParam.SELECT);
        List<Object> sqlParams = jdbcUtils.getSqlParams(user, SqlParam.SELECT);
        return qr.query(sql.toString(), new BeanListHandler<>(User.class), sqlParams.toArray());
    }

    @Override
    public User getUserById(User user) throws SQLException {
        StringBuffer sql = jdbcUtils.getSql(user, SqlParam.SELECT);
        List<Object> sqlParams = jdbcUtils.getSqlParams(user, SqlParam.SELECT);
        return qr.query(sql.toString(), new BeanHandler<>(User.class), sqlParams.toArray());
    }

    @Override
    public int delUserById(User user) throws SQLException {
        StringBuffer sql = jdbcUtils.getSql(user, SqlParam.DELETE);
        List<Object> sqlParams = jdbcUtils.getSqlParams(user, SqlParam.DELETE);
        return qr.update(sql.toString(), sqlParams.toArray());
    }
}
