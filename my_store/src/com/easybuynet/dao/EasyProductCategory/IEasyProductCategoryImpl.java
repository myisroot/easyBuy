package com.easybuynet.dao.EasyProductCategory;

import com.easybuynet.entity.Easy_product_category;
import com.easybuynet.utils.JdbcUtils;
import com.easybuynet.utils.SqlParam;
import com.easybuynet.utils.SqlUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class IEasyProductCategoryImpl implements IEasyProductCategory {
    private QueryRunner qr = JdbcUtils.getQueryRunner();
    private SqlUtil sqlUtil = new SqlUtil();


    /**
     * 分页获取分类信息
     *
     * @param object
     * @param curr
     * @param pageSize
     * @param flag
     * @return
     * @throws SQLException
     */
    @Override
    public List<Easy_product_category> getAllEasyProductCategory(Easy_product_category object, Integer curr, Integer pageSize, Boolean flag) throws SQLException {
        StringBuffer sql = sqlUtil.getSql(object, SqlParam.SELECT);
        List<Object> sqlParams = sqlUtil.getSqlParams(object, SqlParam.SELECT);
        if (flag) {
            sql.append(" ORDER BY c_id DESC  ");
            sql.append(" limit ?,?;");
            sqlParams.add((curr - 1) * pageSize);
            sqlParams.add(pageSize);
        }
        return qr.query(sql.toString(), new BeanListHandler<>(Easy_product_category.class), sqlParams.toArray());
    }

    /**
     * 根据parentid查询分类信息
     *
     * @param parentid
     * @return
     * @throws SQLException
     */
    @Override
    public List<Easy_product_category> getCategoryByParentId(String parentid) throws SQLException {
        Easy_product_category easyProductCategory = new Easy_product_category();
        easyProductCategory.setC_parentid(parentid);
        StringBuffer sql = sqlUtil.getSql(easyProductCategory, SqlParam.SELECT);
        return qr.query(sql.toString(), new BeanListHandler<>(Easy_product_category.class), parentid);
    }

    /**
     * 根据parentid查询分类信息
     *
     * @param parentId 父级分类id
     * @return
     */
    @Override
    public Easy_product_category getCateGoryById(Integer parentId) throws SQLException {
        Easy_product_category easyProductCategory = new Easy_product_category();
        if (null != parentId) {
            easyProductCategory.setC_id(parentId);
        }
        StringBuffer sql = sqlUtil.getSql(easyProductCategory, SqlParam.SELECT);
        return qr.query(sql.toString(), new BeanHandler<>(Easy_product_category.class), parentId);
    }

    @Override
    public int delCategoryById(Integer id) throws SQLException {
        Easy_product_category easyProductCategory = new Easy_product_category();
        StringBuffer sql = sqlUtil.getSql(easyProductCategory, SqlParam.DELETE);
        easyProductCategory.setC_id(id);
        List<Object> sqlParams = sqlUtil.getSqlParams(easyProductCategory, SqlParam.DELETE);
        return qr.update(sql.toString(), sqlParams.toArray());
    }

    @Override
    public int addCategory(Easy_product_category easyProductCategory) throws SQLException {
        StringBuffer sql = sqlUtil.getSql(easyProductCategory, SqlParam.INSERT);
        List<Object> sqlParams = sqlUtil.getSqlParams(easyProductCategory, SqlParam.INSERT);
        return qr.update(sql.toString(), sqlParams.toArray());
    }

    @Override
    public List<Easy_product_category> getCategoryByType(String type) throws SQLException {
        Easy_product_category easyProductCategory = new Easy_product_category();
        if (null != type && !"".equals(type)) {
            easyProductCategory.setC_type(Integer.parseInt(type));
        }
        StringBuffer sql = sqlUtil.getSql(easyProductCategory, SqlParam.SELECT);
        List<Object> sqlParams = sqlUtil.getSqlParams(easyProductCategory, SqlParam.SELECT);
        return qr.query(sql.toString(), new BeanListHandler<>(Easy_product_category.class), sqlParams.toArray());
    }

    @Override
    public int updateCategory(Easy_product_category easyProductCategory) throws SQLException {
        StringBuffer sql = sqlUtil.getSql(easyProductCategory, SqlParam.UPDATE);
        List<Object> sqlParams = sqlUtil.getSqlParams(easyProductCategory, SqlParam.UPDATE);
        return qr.update(sql.toString(), sqlParams.toArray());
    }

}
