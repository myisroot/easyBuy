package com.easybuynet.service.EasyProductCategoryService;

import com.easybuynet.entity.Easy_user_address;

import java.sql.SQLException;
import java.util.List;

public interface IAddressService {
    //获取所有地址
    List<Easy_user_address> getAllAddress(Easy_user_address easy_user_address) throws SQLException;

    //添加地址
    int addAddress(Easy_user_address easyUserAddress) throws SQLException;
}
