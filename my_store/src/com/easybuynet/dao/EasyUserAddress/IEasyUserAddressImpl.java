package com.easybuynet.dao.EasyUserAddress;

import com.easybuynet.entity.Easy_user_address;
import com.easybuynet.utils.JdbcUtils;
import com.easybuynet.utils.SqlParam;
import com.easybuynet.utils.SqlUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class IEasyUserAddressImpl implements IEasyUserAddress {
    private QueryRunner qr = JdbcUtils.getQueryRunner();
    private SqlUtil sqlUtil = new SqlUtil();

    //根据用户获取所有的地址
    @Override
    public List<Easy_user_address> getAllAddress(Easy_user_address easy_user_address) throws SQLException {
        StringBuffer sql = sqlUtil.getSql(easy_user_address, SqlParam.SELECT);
        List<Object> sqlParams = sqlUtil.getSqlParams(easy_user_address, SqlParam.SELECT);
        return qr.query(sql.toString(), new BeanListHandler<>(Easy_user_address.class), sqlParams.toArray());
    }

    //添加地址
    @Override
    public int addAddress(Easy_user_address easyUserAddress) throws SQLException {
        StringBuffer sql = sqlUtil.getSql(easyUserAddress, SqlParam.INSERT);
        List<Object> sqlParams = sqlUtil.getSqlParams(easyUserAddress, SqlParam.INSERT);
        return JdbcUtils.getPrimaryKey(sql.toString(), sqlParams.toArray());
    }

    @Override
    public int updateAddress(String aid, String isDel, Integer uid) throws SQLException {
        StringBuffer sql = new StringBuffer("update easy_user_address set a_isdelete =? where u_id=? and a_id =?");
        return qr.update(sql.toString(), isDel, uid, aid);
    }

    @Override
    public int updateDefaultByUid(String isDefault, Integer uid) throws SQLException {
        StringBuffer sql = new StringBuffer("update easy_user_address set a_isDefault =? where u_id=?");
        return qr.update(sql.toString(), isDefault, uid);
    }

    @Override
    public int editDefaultByAid(String isDefault, Integer uid, String aid) throws SQLException {
        StringBuffer sql = new StringBuffer("update easy_user_address set a_isDefault =? where u_id=? and a_id =?");
        return qr.update(sql.toString(), isDefault, uid, aid);
    }

    @Override
    public int updateAddress(Easy_user_address easyUserAddress, String uid) throws SQLException {
        StringBuffer sql = sqlUtil.getSql(easyUserAddress, SqlParam.UPDATE);
        sql.append(" and u_id = ? ");
        List<Object> sqlParams = sqlUtil.getSqlParams(easyUserAddress, SqlParam.UPDATE);
        sqlParams.add(uid);
        return qr.update(sql.toString(), sqlParams.toArray());
    }
}
