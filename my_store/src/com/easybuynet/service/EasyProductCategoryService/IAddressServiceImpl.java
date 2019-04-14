package com.easybuynet.service.EasyProductCategoryService;

import com.easybuynet.dao.EasyUserAddress.IEasyUserAddress;
import com.easybuynet.dao.EasyUserAddress.IEasyUserAddressImpl;
import com.easybuynet.entity.Easy_user_address;

import java.sql.SQLException;
import java.util.List;

public class IAddressServiceImpl implements IAddressService {
    private IEasyUserAddress iEasyUserAddress = new IEasyUserAddressImpl();

    /**
     * 根据用户获取用户地址
     *
     * @param easy_user_address
     * @return
     * @throws SQLException
     */
    @Override
    public List<Easy_user_address> getAllAddress(Easy_user_address easy_user_address) throws SQLException {
        return iEasyUserAddress.getAllAddress(easy_user_address);
    }

    /**
     * 添加地址
     *
     * @param easyUserAddress
     * @return
     * @throws SQLException
     */
    @Override
    public int addAddress(Easy_user_address easyUserAddress) throws SQLException {
        return iEasyUserAddress.addAddress(easyUserAddress);
    }
}
