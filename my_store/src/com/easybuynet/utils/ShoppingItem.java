package com.easybuynet.utils;

import com.easybuynet.entity.Easy_product;

import java.io.Serializable;
import java.math.BigDecimal;

public class ShoppingItem implements Serializable {
    //商品
    private Easy_product easyProduct;
    //数量
    private Integer quantity;
    //单个商品的总金额
    private BigDecimal cost;

    public ShoppingItem(Easy_product easyProduct, Integer quantity) {
        this.easyProduct = easyProduct;
        this.quantity = quantity;
        cost = new BigDecimal(Integer.parseInt(easyProduct.getP_price()) * quantity);
    }

    public Easy_product getEasyProduct() {
        return easyProduct;
    }

    public void setEasyProduct(Easy_product easyProduct) {
        this.easyProduct = easyProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        cost = new BigDecimal(Integer.parseInt(easyProduct.getP_price()) * this.quantity);
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
