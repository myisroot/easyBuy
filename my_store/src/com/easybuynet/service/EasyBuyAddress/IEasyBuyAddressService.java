package com.easybuynet.service.EasyBuyAddress;

import com.easybuynet.entity.Easy_user_address;
import com.easybuynet.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface IEasyBuyAddressService {

    /**
     * flag(true) 添加地址
     * flag(false) 修改地址
     *
     * @param easyUserAddress
     * @param user
     * @return
     * @throws SQLException
     */
    int addAddress(Easy_user_address easyUserAddress, User user, Boolean flag) throws SQLException;

    /**
     * 获取所有地址
     *
     * @param easyUserAddress
     * @return
     */
    List<Easy_user_address> getAllAddress(Easy_user_address easyUserAddress) throws SQLException;

    /**
     * 用户删除地址(不显示)
     *
     * @param aid
     * @param isDel
     * @param uid
     * @return
     * @throws SQLException
     */
    int updateAddress(String aid, String isDel, Integer uid) throws SQLException;

    /**
     * 修改默认地址
     *
     * @param isDefault
     * @param uid
     * @param aid
     * @return
     * @throws SQLException
     */
    int editDefaultByAid(String isDefault, Integer uid, String aid) throws SQLException;

    int updateAddress(Easy_user_address easyUserAddress, String uid) throws SQLException;
}
