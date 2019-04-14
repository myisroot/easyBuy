package com.easybuynet.dao.EasyProduct;

import com.easybuynet.entity.Easy_product;
import com.easybuynet.utils.JdbcUtils;
import com.easybuynet.utils.SqlParam;
import com.easybuynet.utils.SqlUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IEasyProductImpl implements IEasyProduct {
    private QueryRunner qr = JdbcUtils.getQueryRunner();
    private SqlUtil sqlUtil = new SqlUtil();

    /**
     * 判断根据什么条件查询商品
     *
     * @param p_name
     * @param p_categorylevel
     * @param currPaNo
     * @param pageSize
     * @return
     * @throws SQLException
     */
    @Override
    public List<Easy_product> getAllRroDuct(String p_name, String p_categorylevel, Integer currPaNo, Integer pageSize, Boolean flag) throws SQLException {
        StringBuffer sql = sqlUtil.getSql(new Easy_product(), SqlParam.SELECT);
        List<Object> plist = new ArrayList<>();
        sql.append(" where p_isdelete = 1  ");
        if (null != p_name) {
            sql.append(" and  p_name like ? ");
            plist.add("%" + p_name + "%");
        }
        if (null != p_categorylevel) {
            sql.append("and ( p_categorylevel1=? or p_categorylevel2=? or p_categorylevel3= ? )");
            plist.add(p_categorylevel);
            plist.add(p_categorylevel);
            plist.add(p_categorylevel);
        }
        if (flag) {
            sql.append(" limit  ? , ? ;");
            int curr = (currPaNo - 1) * pageSize;
            plist.add(curr);
            plist.add(pageSize);
        }
        return qr.query(sql.toString(), new BeanListHandler<>(Easy_product.class), plist.toArray());
    }


    /**
     * 根据id获取商品信息
     *
     * @param easyProduct
     * @return
     * @throws SQLException
     */
    @Override
    public Easy_product getEasyProductById(Easy_product easyProduct) throws SQLException {
        StringBuffer sql = sqlUtil.getSql(easyProduct, SqlParam.SELECT);
        List<Object> sqlParams = sqlUtil.getSqlParams(easyProduct, SqlParam.SELECT);
        return qr.query(sql.toString(), new BeanHandler<>(Easy_product.class), sqlParams.toArray());
    }

    /**
     * 更新商品库存
     *
     * @param id
     * @param stock
     * @return
     */
    @Override
    public int updateStock(Integer id, Integer stock) throws SQLException {
        StringBuffer sql = new StringBuffer("update Easy_product set p_stock =? where p_id=?");
        return qr.update(sql.toString(), stock, id);
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
        StringBuffer sql = sqlUtil.getSql(new Easy_product(), SqlParam.SELECT);
        sql.append(" where ( p_categorylevel1=? or p_categorylevel2=? or p_categorylevel3= ? )");
        return qr.query(sql.toString(), new BeanListHandler<>(Easy_product.class), CateGoryLevel, CateGoryLevel, CateGoryLevel);
    }

    /**
     * 根据商品名分页获取商品
     *
     * @param easyProduct
     * @param currPaNo
     * @param pageSize
     * @param flag
     * @return
     */
    @Override
    public List<Easy_product> getProductByName(Easy_product easyProduct, Integer currPaNo, Integer pageSize, Boolean flag) throws SQLException {
        StringBuffer sql = sqlUtil.getSql(easyProduct, SqlParam.SELECT);
        List<Object> sqlParams = sqlUtil.getSqlParams(easyProduct, SqlParam.SELECT);
        if (flag) {
            sql.append(" ORDER BY p_id DESC ");
            sql.append(" limit  ? , ? ;");
            sqlParams.add((currPaNo - 1) * pageSize);
            sqlParams.add(pageSize);
        }
        return qr.query(sql.toString(), new BeanListHandler<>(Easy_product.class), sqlParams.toArray());
    }

    /**
     * 根据id删除商品
     *
     * @param easyProduct
     * @return
     */
    @Override
    public int delProductById(Easy_product easyProduct) throws SQLException {
        StringBuffer sql = sqlUtil.getSql(easyProduct, SqlParam.DELETE);
        List<Object> sqlParams = sqlUtil.getSqlParams(easyProduct, SqlParam.DELETE);
        return qr.update(sql.toString(), sqlParams.toArray());
    }

    /**
     * 修改isDelete字段
     *
     * @param id
     * @param type
     * @return
     * @throws SQLException
     */
    @Override
    public int isDelete(Integer id, String type) throws SQLException {
        StringBuffer sql = new StringBuffer("update Easy_product set p_isdelete =? where p_id=?");
        return qr.update(sql.toString(), type, id);
    }

    /**
     * 添加商品
     *
     * @param easyProduct
     * @return
     */
    @Override
    public int addProduct(Easy_product easyProduct) throws SQLException {
        StringBuffer sql = sqlUtil.getSql(easyProduct, SqlParam.INSERT);
        List<Object> sqlParams = sqlUtil.getSqlParams(easyProduct, SqlParam.INSERT);
        return qr.update(sql.toString(), sqlParams.toArray());
    }

    /**
     * 修改商品
     *
     * @param easyProduct
     * @return
     * @throws SQLException
     */
    @Override
    public int updateProduct(Easy_product easyProduct) throws SQLException {
        StringBuffer sql = sqlUtil.getSql(easyProduct, SqlParam.UPDATE);
        List<Object> sqlParams = sqlUtil.getSqlParams(easyProduct, SqlParam.UPDATE);
        return qr.update(sql.toString(), sqlParams.toArray());
    }

    @Override
    public List<Easy_product> getProductByCateGoryLevel1(Integer CateGoryLevel1, Boolean flag) throws SQLException {
        Easy_product easyProduct = new Easy_product();
        if (flag) {
            easyProduct.setP_categorylevel1(CateGoryLevel1);
        } else {
            easyProduct.setP_categorylevel2(CateGoryLevel1);
        }
        StringBuffer sql = sqlUtil.getSql(easyProduct, SqlParam.SELECT);
        return qr.query(sql.toString(), new BeanListHandler<>(Easy_product.class), CateGoryLevel1);
    }

}
