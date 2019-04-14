package com.easybuynet.utils;

import com.easybuynet.entity.Easy_product;
import com.easybuynet.entity.Easy_product_category;

import java.util.List;

public class Easy_product_categoryVO {
    private Easy_product_category easy_product_category;
    private List<Easy_product_categoryVO> list;
    private List<Easy_product> products;

    public List<Easy_product> getProducts() {
        return products;
    }

    public void setProducts(List<Easy_product> products) {
        this.products = products;
    }


    public Easy_product_category getEasy_product_category() {
        return easy_product_category;
    }

    public void setEasy_product_category(Easy_product_category easy_product_category) {
        this.easy_product_category = easy_product_category;
    }

    public List<Easy_product_categoryVO> getList() {
        return list;
    }

    public void setList(List<Easy_product_categoryVO> list) {
        this.list = list;
    }
}
