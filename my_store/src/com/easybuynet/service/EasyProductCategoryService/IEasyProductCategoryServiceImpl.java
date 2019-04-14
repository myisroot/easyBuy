package com.easybuynet.service.EasyProductCategoryService;

import com.easybuynet.dao.EasyProduct.IEasyProduct;
import com.easybuynet.dao.EasyProduct.IEasyProductImpl;
import com.easybuynet.dao.EasyProductCategory.IEasyProductCategory;
import com.easybuynet.dao.EasyProductCategory.IEasyProductCategoryImpl;
import com.easybuynet.entity.Constants;
import com.easybuynet.entity.Easy_product_category;
import com.easybuynet.entity.Result;
import com.easybuynet.entity.User;
import com.easybuynet.utils.Easy_product_categoryVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IEasyProductCategoryServiceImpl implements IEasyProductCategoryService {
    private IEasyProductCategory iepc = new IEasyProductCategoryImpl();
    private IEasyProduct iEasyProduct = new IEasyProductImpl();
    private IEasyProductService iEasyProductService = new IEasyProductServiceImpl();

    /**
     * 设置parentId的父级分类名
     */
    @Override

    public List<Easy_product_category> getAllEasyProductCategory(Easy_product_category object, Integer curr, Integer pageSize, Boolean flag) throws SQLException {
        List<Easy_product_category> allEasyProductCategory = iepc.getAllEasyProductCategory(object, curr, pageSize, flag);
        for (Easy_product_category category : allEasyProductCategory) {
            if (!"0".equals(category.getC_parentid())) {
                Easy_product_category productCategory = iepc.getCateGoryById(Integer.parseInt(category.getC_parentid()));
                category.setC_iconclass(productCategory.getC_name());
            } else {
                category.setC_iconclass("无");
            }
        }
        return allEasyProductCategory;
    }

    /**
     * 获取所有的分类
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Easy_product_categoryVO> getAllVO() throws SQLException {
        //一级分类List
        List<Easy_product_categoryVO> pcVoList1 = new ArrayList<>();
        //获取所有的一级分类
        List<Easy_product_category> all1Type = getCategoryByParentId("0");
        //遍历获取一级分类根据一级分类的id(parentid)获取二级分类
        for (Easy_product_category epc1 : all1Type) {
            //每一个一级分类对应一个下一级分类的pcvo1
            Easy_product_categoryVO pcvo1 = new Easy_product_categoryVO();
            //设置子分类下的商品信息
            pcvo1.setProducts(iEasyProductService.getProductByAllCategory(epc1.getC_id()));
            //将每一个一级分类的值设置到List一级分类中
            pcvo1.setEasy_product_category(epc1);
            //创建一个分类list放二级所有分类
            List<Easy_product_categoryVO> pcVoList2 = new ArrayList<>();
            //根据一级的id查询二级分类的praentid
            List<Easy_product_category> all2Type = getCategoryByParentId(epc1.getC_id().toString());
            //处理二级分类
            for (Easy_product_category epc2 : all2Type) {
                //遍历所有的二级分类以及每次遍历为此分类创建一个vo对象来存储下一个子分类
                Easy_product_categoryVO pcvo2 = new Easy_product_categoryVO();
                //把每一个二级分类赋值到二级分类的pcvot中
                pcvo2.setEasy_product_category(epc2);
                //创建一个分类list放上一级分类的三级子分类
                List<Easy_product_categoryVO> pcVoList3 = new ArrayList<>();
                //根据上一级每一个的id获取三级分类的parentid
                List<Easy_product_category> all3Type = getCategoryByParentId(epc2.getC_id().toString());
                //遍历每一个以二级分类对应的三级分类
                for (Easy_product_category epc3 : all3Type) {
                    //创建三级分类voList存储分类信息
                    Easy_product_categoryVO pcvo3 = new Easy_product_categoryVO();
                    //设置三级分类信息值到三级分类的volist中
                    pcvo3.setEasy_product_category(epc3);
                    //因为是最后一级分类所以不用不含下一级分类的Volist
                    pcVoList3.add(pcvo3);
                }
                //把每一个三级分类添加到二级分类的VoList中
                pcvo2.setList(pcVoList3);
                pcVoList2.add(pcvo2);
            }
            pcvo1.setList(pcVoList2);
            pcVoList1.add(pcvo1);
        }
        return pcVoList1;
    }

    /**
     * 根据parent获取商品分类
     *
     * @param parentid
     * @return
     * @throws SQLException
     */
    @Override
    public List<Easy_product_category> getCategoryByParentId(String parentid) throws SQLException {
        return iepc.getCategoryByParentId(parentid);
    }


    /**
     * 添加分类
     *
     * @param easyProductCategory
     * @return
     * @throws SQLException
     */
    @Override
    public int addCategory(Easy_product_category easyProductCategory) throws SQLException {
        return iepc.addCategory(easyProductCategory);
    }

    /**
     * 删除分类
     *
     * @param cid
     * @param user
     * @return
     * @throws SQLException
     */
    @Override
    public Result delCategory(String cid, User user) throws SQLException {
        Result result = new Result();
        if (Constants.NOREMOVE.equals(user.getU_isdelete())) {
            return result.Fail("请联系管理员给予删除权限");
        }
        if (null != cid) {
            int categoryByParentIdSize = iepc.getCategoryByParentId(cid).size();
            if (categoryByParentIdSize > 0) {
                return result.Fail("已存在子分类不能删除");
            }
            int size = iEasyProduct.getProductByCateGoryLevel(cid).size();
            if (size > 0) {
                return result.Fail("此分类已有商品不能删除");
            }
            int row = iepc.delCategoryById(Integer.parseInt(cid));
        } else {
            return result.Fail("选择分类异常");
        }
        return result.Success();
    }

    @Override
    public Easy_product_category getCateGoryById(Integer id) throws SQLException {
        return iepc.getCateGoryById(id);
    }

    @Override
    public int updateCategory(Easy_product_category easyProductCategory) throws SQLException {
        return iepc.updateCategory(easyProductCategory);
    }

}
