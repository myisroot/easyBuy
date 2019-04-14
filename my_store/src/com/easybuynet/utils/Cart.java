package com.easybuynet.utils;

import com.easybuynet.entity.Easy_product;
import com.easybuynet.entity.Result;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    //购物车数据
    private List<ShoppingItem> shopItems = new ArrayList<>();
    //购物车总金额
    private BigDecimal sum;

    public List<ShoppingItem> getShopItems() {
        return shopItems;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    //添加商品
    public Result addItem(Easy_product easyProduct, Integer quantity) {
        Boolean flag = false;
        Result result = new Result();
        //购物车已存在此商品
        for (int i = 0; i < shopItems.size(); i++) {
            //判断购物车类里面是否存在商品
            if (shopItems.get(i).getEasyProduct().getP_id().equals(easyProduct.getP_id())) {
                //判断购物车类里的商品的数量加上要添加的数量是否大于数据库内商品的数量
                if (shopItems.get(i).getQuantity() + quantity > Integer.parseInt(easyProduct.getP_stock())) {
                    //返回错误信息
                    return result.Fail("商品数量不足");
                } else {
                    shopItems.get(i).setQuantity(shopItems.get(i).getQuantity() + quantity);
                    flag = true;
                }
            }
        }
        //购物车不存在此商品
        if (!flag) {
            shopItems.add(new ShoppingItem(easyProduct, quantity));
        }
        //返回成功数据
        return result.Success(easyProduct);
    }

    //删除商品
    public void removeItem(int index) {
        shopItems.remove(index);
    }

    //修改数量
    public void updateQuantity(int index, int quantity) {
        shopItems.get(index).setQuantity(quantity);
    }

}
