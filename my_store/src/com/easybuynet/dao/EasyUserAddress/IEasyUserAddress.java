package com.easybuynet.dao.EasyUserAddress;

import com.easybuynet.entity.Easy_user_address;

import java.sql.SQLException;
import java.util.List;

public interface IEasyUserAddress {
    //根据用户获取所有的地址
    List<Easy_user_address> getAllAddress(Easy_user_address easy_user_address) throws SQLException;

    //添加地址
    int addAddress(Easy_user_address easyUserAddress) throws SQLException;

    //用户删除地址(不显示)
    int updateAddress(String aid, String isDel, Integer uid) throws SQLException;

    //根据用户修改默认地址状态
    int updateDefaultByUid(String isDefault, Integer uid) throws SQLException;

    //根据地址id设置为默认
    int editDefaultByAid(String isDefault, Integer uid, String aid) throws SQLException;

    int updateAddress(Easy_user_address easyUserAddress, String uid) throws SQLException;
}
