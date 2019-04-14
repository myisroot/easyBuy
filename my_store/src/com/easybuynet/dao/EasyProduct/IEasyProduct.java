package com.easybuynet.dao.EasyProduct;

import com.easybuynet.entity.Easy_product;

import java.sql.SQLException;
import java.util.List;

public interface IEasyProduct {
    /**
     * 分页获取商品数据
     *
     * @param p_name
     * @param p_categorylevel
     * @param currPaNo
     * @param pageSize
     * @return
     * @throws SQLException
     */
    List<Easy_product> getAllRroDuct(String p_name, String p_categorylevel, Integer currPaNo, Integer pageSize, Boolean flag) throws SQLException;


    /**
     * 根据id查询商品
     *
     * @param easyProduct
     * @return
     * @throws SQLException
     */
    Easy_product getEasyProductById(Easy_product easyProduct) throws SQLException;

    /**
     * 更新商品库存
     *
     * @param id
     * @param stock
     * @return
     */
    int updateStock(Integer id, Integer stock) throws SQLException;


    /**
     * 根据分类id查询商品信息
     *
     * @param CateGoryLevel
     * @return
     * @throws SQLException
     */
    List<Easy_product> getProductByCateGoryLevel(String CateGoryLevel) throws SQLException;

    /**
     * 根据商品名分页获取商品
     *
     * @param easyProduct
     * @param currPaNo
     * @param pageSize
     * @param flag
     * @return
     */
    List<Easy_product> getProductByName(Easy_product easyProduct, Integer currPaNo, Integer pageSize, Boolean flag) throws SQLException;

    /**
     * 根据id删除商品
     *
     * @param easyProduct
     * @return
     */
    int delProductById(Easy_product easyProduct) throws SQLException;

    /**
     * 修改isDelete字段
     *
     * @param id
     * @param type
     * @return
     * @throws SQLException
     */
    int isDelete(Integer id, String type) throws SQLException;

    /**
     * 添加商品
     *
     * @param easyProduct
     * @return
     */
    int addProduct(Easy_product easyProduct) throws SQLException;

    /**
     * 修改商品
     *
     * @param easyProduct
     * @return
     */
    int updateProduct(Easy_product easyProduct) throws SQLException;

    /**
     * 根据分类查询商品
     *
     * @param CateGoryLevel1
     * @param flag
     * @return
     * @throws SQLException
     */
    List<Easy_product> getProductByCateGoryLevel1(Integer CateGoryLevel1, Boolean flag) throws SQLException;

}
