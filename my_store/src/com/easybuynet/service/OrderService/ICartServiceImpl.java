package com.easybuynet.service.OrderService;

import com.easybuynet.utils.Cart;
import com.easybuynet.utils.ShoppingItem;

import java.math.BigDecimal;

public class ICartServiceImpl implements ICartService {
    /**
     * 修改购物车商品数量
     *
     * @param p_id
     * @param number
     * @param cart
     * @return
     */
    @Override
    public Cart change(String p_id, Integer number, Cart cart) {
        for (ShoppingItem item : cart.getShopItems()) {
            if (p_id.equals(item.getEasyProduct().getP_id().toString())) {
                //判断商品数量是否小于0
                if (number <= 0) {
                    //删除商品
                    cart.getShopItems().remove(item);
                    break;
                } else {
                    //设置商品数量
                    item.setQuantity(number);
                    break;
                }
            }
        }
        return changeCost(cart);
    }

    /**
     * 重新计算商品金额
     *
     * @param cart
     * @return
     */
    @Override
    public Cart changeCost(Cart cart) {
        double sum = 0.00;
        for (ShoppingItem item : cart.getShopItems()) {
            //计算总金额
            sum += item.getQuantity() * Integer.parseInt(item.getEasyProduct().getP_price());
            //重新设置每个商品的小计
            item.setCost(new BigDecimal(item.getQuantity() * Integer.parseInt(item.getEasyProduct().getP_price())));
        }
        //设置总金额
        cart.setSum(new BigDecimal(sum));
        return cart;
    }

    /**
     * 删除购物车商品
     *
     * @param p_id
     * @param cart
     * @return
     */
    @Override
    public Cart delShopItem(String p_id, Cart cart) {
        for (ShoppingItem item : cart.getShopItems()) {
            if (p_id.equals(item.getEasyProduct().getP_id().toString())) {
                cart.getShopItems().remove(item);
                break;
            }
        }
        return changeCost(cart);
    }
}
