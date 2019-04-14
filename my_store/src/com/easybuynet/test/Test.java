package com.easybuynet.test;

import com.easybuynet.dao.EasyProduct.IEasyProduct;
import com.easybuynet.dao.EasyProduct.IEasyProductImpl;
import com.easybuynet.dao.EasyProductCategory.IEasyProductCategory;
import com.easybuynet.dao.EasyProductCategory.IEasyProductCategoryImpl;
import com.easybuynet.dao.OrderAddress.IOrderAddressDao;
import com.easybuynet.dao.OrderAddress.IOrderAddressDaoImpl;
import com.easybuynet.entity.*;
import com.easybuynet.service.EasyProductCategoryService.IEasyProductCategoryService;
import com.easybuynet.service.EasyProductCategoryService.IEasyProductCategoryServiceImpl;
import com.easybuynet.service.EasyProductCategoryService.IEasyProductService;
import com.easybuynet.service.EasyProductCategoryService.IEasyProductServiceImpl;
import com.easybuynet.service.OrderService.IOrderService;
import com.easybuynet.service.OrderService.IOrderServiceImpl;
import com.easybuynet.utils.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test<T> {
    private static SqlUtil sqlUtil = new SqlUtil();
    static IOrderService iOrderService = new IOrderServiceImpl();
    private static IEasyProductService iEasyProductService = new IEasyProductServiceImpl();
    private static IEasyProductCategoryService i = new IEasyProductCategoryServiceImpl();
    private static IEasyProduct ip = new IEasyProductImpl();
    private static IEasyProductCategory iEasyProductCategory = new IEasyProductCategoryImpl();
    private static QueryRunner qr = JdbcUtils.getQueryRunner();
    static IOrderAddressDao iOrderAddressDao = new IOrderAddressDaoImpl();

    public static void main(String[] args) throws SQLException {
        List<Order_address> orderByUid = iOrderAddressDao.getOrderByUid(new Order_address(), 1, 6, true);
        System.out.println(orderByUid);
    }


    public Integer counts(Object object, ResultSetHandler<T> rsh) throws SQLException {
        StringBuffer sql = sqlUtil.getSql(object, SqlParam.SELECT);
        List<T> list = (List<T>) qr.query(sql.toString(), rsh);
        return list.size();
    }

    public static void getProductByCateGoryLevel() throws SQLException {
        List<Easy_product> productByCateGoryLevel = iEasyProductService.getProductByCateGoryLevel("2");
        for (Easy_product e : productByCateGoryLevel) {
            System.out.println(e);
        }
    }


    public static void getCategoryByParentId() throws SQLException {
        List<Easy_product_category> categoryByParentId = i.getCategoryByParentId("1");
        for (Easy_product_category e : categoryByParentId) {
            System.out.println(e);
        }
    }


    public static void getAllEasyProductCategory() throws SQLException {
        List<Easy_product_category> allEasyProductCategory = i.getAllEasyProductCategory(new Easy_product_category(), 1, 2, false);
        for (Easy_product_category e : allEasyProductCategory) {
            System.out.println(e);
        }
    }

    public static void getEasyProduct() throws SQLException {
        Easy_product easy_product = new Easy_product();
        easy_product.setP_id(6);
        Easy_product easyProductById = iEasyProductService.getEasyProductById(easy_product);
        System.out.println(easyProductById);
    }

    public static void insert() throws SQLException {
        List<Easy_product_categoryVO> allVO = i.getAllVO();
        for (Easy_product_categoryVO e : allVO
        ) {
            List<Easy_product_categoryVO> list = e.getList();
            System.out.println(e.getEasy_product_category());
            for (Easy_product_categoryVO e2 : list
            ) {
                System.out.println(e2.getEasy_product_category());
                for (Easy_product_categoryVO e3 : list
                ) {
                    System.out.println(e3.getEasy_product_category());

                }
            }
        }
    }

    /**
     * 根据属性名获取属性值
     *
     * @param fieldName
     * @param object
     * @return
     */
    private static Object getFieldVal(String fieldName, Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            //设置对象的访问权限，保证对private的属性的访问
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Map<String, Object> getSqlParams(Object object) {
        Map<String, Object> map = new HashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object fieldVal = getFieldVal(field.getName(), object);
            if (fieldVal != null && !field.getName().equalsIgnoreCase("serialVersionUID")) {
                map.put(field.getName(), fieldVal);
            }
        }
        return map;
    }
}
