package com.easybuynet.service.EasyProductCategoryService;

import com.easybuynet.dao.EasyProduct.IEasyProduct;
import com.easybuynet.dao.EasyProduct.IEasyProductImpl;
import com.easybuynet.dao.EasyProductCategory.IEasyProductCategory;
import com.easybuynet.dao.EasyProductCategory.IEasyProductCategoryImpl;
import com.easybuynet.entity.*;
import com.easybuynet.utils.Tools;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IEasyProductServiceImpl implements IEasyProductService {
    private IEasyProduct iEasyProduct = new IEasyProductImpl();
    private IEasyProductCategory iEasyProductCategory = new IEasyProductCategoryImpl();

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
    @Override
    public List<Easy_product> getAllRroDuct(String p_name, String p_categorylevel, Integer currPaNo, Integer pageSize, Boolean flag) throws SQLException {
        return iEasyProduct.getAllRroDuct(p_name, p_categorylevel, currPaNo, pageSize, flag);
    }

    /**
     * 获取分页数量
     *
     * @param p_name
     * @param p_categorylevel
     * @return
     * @throws SQLException
     */
    @Override
    public int getCounts(String p_name, String p_categorylevel) throws SQLException {
        return iEasyProduct.getAllRroDuct(p_name, p_categorylevel, 0, 0, false).size();
    }

    /**
     * 根据id获取商品
     *
     * @param easyProduct
     * @return
     * @throws SQLException
     */
    @Override
    public Easy_product getEasyProductById(Easy_product easyProduct) throws SQLException {
        return iEasyProduct.getEasyProductById(easyProduct);
    }

    /**
     * 根据分类id查询商品信息
     *
     * @param CateGoryLevel
     * @return
     * @throws SQLException
     */
    @Override
    public List<Easy_product> getProductByCateGoryLevel(String CateGoryLevel) throws SQLException {
        return iEasyProduct.getProductByCateGoryLevel(CateGoryLevel);
    }

    /**
     * 根据商品名分页获取商品
     *
     * @param easyProduct
     * @param currPaNo
     * @param pageSize
     * @return
     */
    @Override
    public PageBean getProducts(Easy_product easyProduct, String currPaNo, Integer pageSize) throws SQLException {
        PageBean pageBean = new PageBean();
        Integer curr = currPaNo == null || "".equals(currPaNo.trim()) ? 1 : Integer.parseInt(currPaNo);
        String pName = easyProduct.getP_name();
        if (null != pName && !"".equals(pName.trim())) {
            easyProduct.setP_name(Tools.toFuzzyParam(pName));
        } else {
            easyProduct.setP_name(null);
        }
        //获取商品分页数据
        //判断查询字段
        List<Easy_product> products = iEasyProduct.getProductByName(easyProduct, curr, pageSize, true);
        int count = iEasyProduct.getProductByName(easyProduct, 0, 0, false).size();
        if (null == products || products.size() <= 0) {
            easyProduct.setP_name(null);
            easyProduct.setP_price(Tools.toFuzzyParam(pName));
            products = iEasyProduct.getProductByName(easyProduct, curr, pageSize, true);
            count = iEasyProduct.getProductByName(easyProduct, 0, 0, false).size();
        }
        if (null == products || products.size() <= 0) {
            easyProduct.setP_price(null);
            easyProduct.setP_stock(Tools.toFuzzyParam(pName));
            products = iEasyProduct.getProductByName(easyProduct, curr, pageSize, true);
            count = iEasyProduct.getProductByName(easyProduct, 0, 0, false).size();
        }
        pageBean.setAllCounts(count);
        pageBean.setCurrPaNo(curr);
        pageBean.setPageSize(pageSize);
        pageBean.setData(products);
        return pageBean;
    }


    @Override
    public Result delProductById(String pid, User user) throws SQLException {
        Result result = new Result();
        Easy_product easyProduct = new Easy_product();
        if (Constants.NOREMOVE.equals(user.getU_isdelete())) {
            return result.Fail("请联系管理员给予删除权限");
        }
        if (null == pid) {
            return result.Fail("选择商品时发生异常");
        } else {
            easyProduct.setP_id(Integer.parseInt(pid));
            int row = iEasyProduct.delProductById(easyProduct);
            if (row <= 0) {
                return result.Fail("删除失败");
            }
        }
        return result.Success();
    }

    @Override
    public int isDelete(Integer id, String type) throws SQLException {
        return iEasyProduct.isDelete(id, type);
    }

    @Override
    public int addProduct(Easy_product easyProduct) throws SQLException {
        easyProduct.setP_isdelete("1");
        return iEasyProduct.addProduct(easyProduct);
    }

    @Override
    public int updateProduct(Easy_product easyProduct) throws SQLException {
        easyProduct.setP_isdelete("1");
        return iEasyProduct.updateProduct(easyProduct);
    }

    @Override
    public List<Easy_product> getProductByAllCategory(Integer id) throws SQLException {
        List<Easy_product> products = new ArrayList<Easy_product>();
        List<Easy_product_category> categorys = iEasyProductCategory.getCategoryByParentId(id.toString());
        for (Easy_product_category category : categorys) {
            List<Easy_product> allProducts = iEasyProduct.getProductByCateGoryLevel1(category.getC_id(), false);
            for (Easy_product Products : allProducts) {
                products.add(Products);
            }
        }
        return products;
    }

}
