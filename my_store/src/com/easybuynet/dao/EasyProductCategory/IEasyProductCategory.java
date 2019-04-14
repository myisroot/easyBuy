package com.easybuynet.dao.EasyProductCategory;

import com.easybuynet.entity.Easy_product_category;

import java.sql.SQLException;
import java.util.List;

public interface IEasyProductCategory {


    /**
     * 根据parentid获取商品分类
     *
     * @param object
     * @return
     * @throws SQLException
     */
    List<Easy_product_category> getAllEasyProductCategory(Easy_product_category object, Integer curr, Integer pageSize, Boolean flag) throws SQLException;

    /**
     * 根据parentid获取商品分类
     *
     * @param parentId
     * @return
     * @throws SQLException
     */
    List<Easy_product_category> getCategoryByParentId(String parentId) throws SQLException;

    /**
     * 根据parentid查询分类信息
     *
     * @param parentId 父级分类id
     * @return
     */
    Easy_product_category getCateGoryById(Integer parentId) throws SQLException;

    /**
     * 根据id删除分类
     *
     * @param id
     * @return
     */
    int delCategoryById(Integer id) throws SQLException;

    /**
     * 添加分类
     *
     * @param easyProductCategory
     * @return
     */
    int addCategory(Easy_product_category easyProductCategory) throws SQLException;

    /**
     * 根据分类级别查询分类
     *
     * @param type
     * @return
     * @throws SQLException
     */
    List<Easy_product_category> getCategoryByType(String type) throws SQLException;

    /**
     * 修改分类
     *
     * @param easyProductCategory
     * @return
     * @throws SQLException
     */
    int updateCategory(Easy_product_category easyProductCategory) throws SQLException;
}
