package com.easybuynet.service.EasyBuyAddress;

import com.easybuynet.dao.EasyUserAddress.IEasyUserAddress;
import com.easybuynet.dao.EasyUserAddress.IEasyUserAddressImpl;
import com.easybuynet.entity.Constants;
import com.easybuynet.entity.Easy_user_address;
import com.easybuynet.entity.User;
import com.easybuynet.utils.Tools;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class IEasyBuyAddressServiceImpl implements IEasyBuyAddressService {
    private IEasyUserAddress iEasyUserAddress = new IEasyUserAddressImpl();

    /**
     * 添加地址
     *
     * @param easyUserAddress
     * @param user
     * @return
     * @throws SQLException
     */
    @Override
    public int addAddress(Easy_user_address easyUserAddress, User user,Boolean flag) throws SQLException {
        if (flag){
            easyUserAddress.setU_id(user.getU_id());
            easyUserAddress.setA_isdefault("0");
            easyUserAddress.setA_creattime(Tools.format(new Date()));
            easyUserAddress.setA_isdelete(Constants.NODELADDRESS);
            return iEasyUserAddress.addAddress(easyUserAddress);
        }else {
            easyUserAddress.setU_id(user.getU_id());
            easyUserAddress.setA_isdefault("0");
            easyUserAddress.setA_isdelete(Constants.NODELADDRESS);
            return iEasyUserAddress.updateAddress(easyUserAddress,user.getU_id().toString());
        }
    }

    /**
     * 查询所有地址
     *
     * @param easyUserAddress
     * @return
     * @throws SQLException
     */
    @Override
    public List<Easy_user_address> getAllAddress(Easy_user_address easyUserAddress) throws SQLException {
        return iEasyUserAddress.getAllAddress(easyUserAddress);
    }

    /**
     * 修改地址为用户不可见（用户删除）
     *
     * @param aid
     * @param isDel
     * @param uid
     * @return
     * @throws SQLException
     */
    @Override
    public int updateAddress(String aid, String isDel, Integer uid) throws SQLException {
        return iEasyUserAddress.updateAddress(aid, isDel, uid);
    }

    @Override
    public int editDefaultByAid(String isDefault, Integer uid, String aid) throws SQLException {
        int i = iEasyUserAddress.updateDefaultByUid(Constants.NODEFAULT, uid);
        if (i > 0) {
            int row = iEasyUserAddress.editDefaultByAid(isDefault, uid, aid);
            return row;
        }
        return 0;
    }

    @Override
    public int updateAddress(Easy_user_address easyUserAddress, String uid) throws SQLException {
        return iEasyUserAddress.updateAddress(easyUserAddress, uid);
    }
}
