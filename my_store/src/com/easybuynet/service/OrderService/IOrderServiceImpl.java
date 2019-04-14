package com.easybuynet.service.OrderService;

import com.easybuynet.dao.EasyOrder.IEasyOrder;
import com.easybuynet.dao.EasyOrder.IEasyOrderImpl;
import com.easybuynet.dao.EasyOrderDetail.IEasyOrderDetail;
import com.easybuynet.dao.EasyOrderDetail.IEasyOrderDetailImpl;
import com.easybuynet.dao.EasyProduct.IEasyProduct;
import com.easybuynet.dao.EasyProduct.IEasyProductImpl;
import com.easybuynet.dao.OrderAddress.IOrderAddressDao;
import com.easybuynet.dao.OrderAddress.IOrderAddressDaoImpl;
import com.easybuynet.dao.OrderInfo.IOrderInfo;
import com.easybuynet.dao.OrderInfo.IOrderInfoImpl;
import com.easybuynet.entity.*;
import com.easybuynet.utils.Cart;
import com.easybuynet.utils.OrderVo;
import com.easybuynet.utils.ShoppingItem;
import com.easybuynet.utils.Tools;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IOrderServiceImpl implements IOrderService {
    private IEasyOrder iEasyOrder = new IEasyOrderImpl();
    private IEasyProduct iEasyProduct = new IEasyProductImpl();
    private IEasyOrderDetail iEasyOrderDetail = new IEasyOrderDetailImpl();
    private IOrderInfo iOrderInfo = new IOrderInfoImpl();
    private IOrderAddressDao iOrderAddressDao = new IOrderAddressDaoImpl();

    /**
     * 订单生成
     * 添加订单
     * 添加订单详情
     * 库存修改
     *
     * @param user
     * @param address
     * @param cart
     * @return
     */
    @Override
    public Easy_order addOrder(User user, String address, Cart cart, Integer aid) {
        Easy_order easy_order = new Easy_order();
        try {
            //设置用户id
            easy_order.setU_id(user.getU_id());
            //设置用户地址
            easy_order.setU_address(address);
            //设置创建时间
            easy_order.setO_createtime(Tools.format(new Date()));
            //设置登入名
            easy_order.setU_loginname(user.getU_loginname());
            //设置订单的总金额
            BigDecimal sum = cart.getSum();
            if (null == sum) {
                sum = new BigDecimal(0);
            } else {
                easy_order.setO_cost(sum.toString());
            }
            //设置订单编号
            easy_order.setO_serialnumber(Tools.getUUID());
            easy_order.setA_id(aid);
            //添加到订单表中以及获取添加后生成的id
            int id = iEasyOrder.saveOrder(easy_order);
            if (id > 0) {
                for (ShoppingItem item : cart.getShopItems()) {
                    Easy_order_detail easyOrderDetail = new Easy_order_detail();
                    //设置每个商品订单订单id
                    easyOrderDetail.setO_id(id);
                    //设置每一个商品订单的金额
                    easyOrderDetail.setD_cost(item.getCost().toString());
                    //设置每个商品的数量
                    easyOrderDetail.setD_quantity(item.getQuantity());
                    //设置每个商品的id
                    easyOrderDetail.setP_id(item.getEasyProduct().getP_id());
                    easyOrderDetail.setP_name(item.getEasyProduct().getP_name());
                    //添加到订单详情
                    int row = iEasyOrderDetail.saveOrderDetail(easyOrderDetail);
                    if (row > 0) {
                        Integer quan = Integer.parseInt(item.getEasyProduct().getP_stock());
                        iEasyProduct.updateStock(item.getEasyProduct().getP_id(), quan - item.getQuantity());
                    } else {
                        return null;
                    }
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
        return easy_order;
    }


    /**
     * 删除订单
     *
     * @param easy_order
     * @return 受影响的行数
     * @throws SQLException
     */
    @Override
    public int delOrder(Easy_order easy_order) throws SQLException {
        Easy_order_detail easyOrderDetail = new Easy_order_detail();
        easyOrderDetail.setO_id(easy_order.getO_id());
        int row = iEasyOrderDetail.delOrderInfoByOid(easyOrderDetail);
        if (row > 0) {
            return iEasyOrder.delOrder(easy_order);
        }
        return 0;
    }

    @Override
    public List<OrderVo> getOrderVo(Order_address easy_order, Integer curr, Integer pageSize, Boolean flag) throws SQLException {
        List<OrderVo> list = new ArrayList<>();
        List<Order_address> orders = iOrderAddressDao.getOrderByUid(easy_order, curr, pageSize, true);
        for (Order_address order : orders) {
            OrderVo orderVo = new OrderVo();
            orderVo.setOrder(order);
            List<Order_info> orderInfos = iOrderInfo.getOrderInfoByOid(order.getO_id());
            orderVo.setDetails(orderInfos);
            list.add(orderVo);
        }
        return list;
    }

    @Override
    public int getOrderCount(Order_address easy_order) throws SQLException {
        List<Order_address> orderByUid = iOrderAddressDao.getOrderByUid(easy_order, 0, 0, false);
        if (null != orderByUid && orderByUid.size() > 0) {
            return orderByUid.size();
        }
        return 0;
    }

    @Override
    public List<Order_info> getOrderInfoByOid(Integer oid) throws SQLException {
        return iOrderInfo.getOrderInfoByOid(oid);
    }

    @Override
    public List<Order_address> getOrder(Order_address easy_order, Integer curr, Integer pageSize, Boolean flag) throws SQLException {
        return iOrderAddressDao.getOrderByUid(easy_order, curr, pageSize, flag);
    }
}
