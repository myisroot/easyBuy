package com.easybuynet.service.EasyProductCategoryService;

import com.easybuynet.entity.Easy_product_category;
import com.easybuynet.entity.Result;
import com.easybuynet.entity.User;
import com.easybuynet.utils.Easy_product_categoryVO;

import java.sql.SQLException;
import java.util.List;

public interface IEasyProductCategoryService {
    /**
     * 获取所有商品分类
     *
     * @param object
     * @return
     * @throws SQLException
     */
    List<Easy_product_category> getAllEasyProductCategory(Easy_product_category object, Integer curr, Integer pageSize, Boolean flag) throws SQLException;

    /**
     * 获取所有分类
     *
     * @return
     * @throws SQLException
     */
    List<Easy_product_categoryVO> getAllVO() throws SQLException;

    /**
     * 根据parent获取商品分类
     *
     * @param parentid
     * @return
     * @throws SQLException
     */
    List<Easy_product_category> getCategoryByParentId(String parentid) throws SQLException;


    /**
     * 添加分类
     *
     * @param easyProductCategory
     * @return
     * @throws SQLException
     */
    int addCategory(Easy_product_category easyProductCategory) throws SQLException;

    /**
     * 删除分类
     *
     * @param parentid
     * @return
     */
    Result delCategory(String parentid, User user) throws SQLException;

    Easy_product_category getCateGoryById(Integer id) throws SQLException;


    int updateCategory(Easy_product_category easyProductCategory) throws SQLException;
}
