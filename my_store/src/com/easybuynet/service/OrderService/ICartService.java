package com.easybuynet.service.OrderService;

import com.easybuynet.utils.Cart;

public interface ICartService {
    /**
     * 修改购物车商品数量
     *
     * @param p_id
     * @param number
     * @param cart
     * @return
     */
    Cart change(String p_id, Integer number, Cart cart);

    /**
     * 重新计算商品金额
     *
     * @param cart
     * @return
     */
    Cart changeCost(Cart cart);

    /**
     * 删除购物车商品
     *
     * @param p_id
     * @param cart
     * @return
     */
    Cart delShopItem(String p_id, Cart cart);
}
