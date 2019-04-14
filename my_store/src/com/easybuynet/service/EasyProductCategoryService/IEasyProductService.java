package com.easybuynet.service.EasyProductCategoryService;

import com.easybuynet.entity.Easy_product;
import com.easybuynet.entity.PageBean;
import com.easybuynet.entity.Result;
import com.easybuynet.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface IEasyProductService {
    /**
     * 获取分页数据
     *
     * @param easyProduct
     * @param currPaNo
     * @param pageSize
     * @return
     * @throws SQLException
     */
    PageBean getProducts(Easy_product easyProduct, String currPaNo, Integer pageSize) throws SQLException;

    /**
     * 分页获取商品
     *
     * @param p_name          商品名
     * @param p_categorylevel 分类id
     * @param currPaNo        当前页
     * @param pageSize        页大小
     * @return
     * @throws SQLException
     */
    List<Easy_product> getAllRroDuct(String p_name, String p_categorylevel, Integer currPaNo, Integer pageSize, Boolean flag) throws SQLException;

    /**
     * 获取分页数量
     *
     * @param p_name
     * @param p_categorylevel
     * @return
     * @throws SQLException
     */
    int getCounts(String p_name, String p_categorylevel) throws SQLException;

    /**
     * 根据id获取商品
     *
     * @param easyProduct
     * @return
     * @throws SQLException
     */
    Easy_product getEasyProductById(Easy_product easyProduct) throws SQLException;

    /**
     * 根据分类id查询商品信息
     *
     * @param CateGoryLevel
     * @return
     * @throws SQLException
     */
    List<Easy_product> getProductByCateGoryLevel(String CateGoryLevel) throws SQLException;


    /**
     * 删除商品
     *
     * @param id
     * @param user
     * @return
     * @throws SQLException
     */
    Result delProductById(String id, User user) throws SQLException;

    /**
     * 商品上下架
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
     * @param id
     * @return
     * @throws SQLException
     */
    List<Easy_product> getProductByAllCategory(Integer id) throws SQLException;
}
