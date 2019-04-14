package com.easybuynet.web.admin;

import com.easybuynet.entity.*;
import com.easybuynet.service.EasyProductCategoryService.IEasyProductCategoryService;
import com.easybuynet.service.EasyProductCategoryService.IEasyProductCategoryServiceImpl;
import com.easybuynet.service.EasyProductCategoryService.IEasyProductService;
import com.easybuynet.service.EasyProductCategoryService.IEasyProductServiceImpl;
import com.easybuynet.service.User.IUserService;
import com.easybuynet.service.User.IUserServiceImpl;
import com.easybuynet.utils.BaseServlet;
import com.easybuynet.utils.FileUpload;
import com.easybuynet.utils.Tools;
import com.easybuynet.web.Common;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@WebServlet("/admin_product")
public class Product extends BaseServlet {
    private Logger logger = Logger.getLogger(Product.class.getName());
    private IEasyProductCategoryService iepcs = null;
    private IEasyProductService iEasyProductService = null;
    private IUserService iUserService = null;

    @Override
    public void init() throws ServletException {
        iepcs = new IEasyProductCategoryServiceImpl();
        iEasyProductService = new IEasyProductServiceImpl();
        iUserService = new IUserServiceImpl();
    }

    /**
     * 分页获取分类
     *
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public String allCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Easy_product_category easyProductCategory = new Easy_product_category();
        //页面大小
        Integer pageSize = 6;
        PageBean pageBean = new PageBean();
        //获取当前页
        String currPaNo = request.getParameter("currPaNo");
        String cName = request.getParameter("cName");
        if (null != cName && !"".equals(cName.trim())) {
            cName = Tools.toUtf_8(cName);
            easyProductCategory.setC_name(Tools.toFuzzyParam(cName));
            request.setAttribute("cName", cName);
        }
        Integer curr = currPaNo == null || "".equals(currPaNo.trim()) ? 1 : Integer.parseInt(currPaNo);
        //获取分页数据
        List<Easy_product_category> allEasyProductCategory = iepcs.getAllEasyProductCategory(easyProductCategory, curr, pageSize, true);
        pageBean.setAllCounts(iepcs.getAllEasyProductCategory(easyProductCategory, curr, pageSize, false).size());
        pageBean.setPageSize(pageSize);
        pageBean.setCurrPaNo(curr);
        request.setAttribute("curr", currPaNo);
        request.setAttribute("pageBean", pageBean);
        request.setAttribute("allCategory", allEasyProductCategory);
        return "/admin/category.jsp";
    }

    /**
     * 删除分类
     *
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public Result delCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String cid = request.getParameter("cid");
        User user = Common.getUserFormSession(request,"admin_user");
        Result result = iepcs.delCategory(cid, user);
        return result;
    }

    /**
     * 添加商品分类
     *
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public Result addCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String pc1 = request.getParameter("pc1");
        String pc2 = request.getParameter("pc2");
        String ctype = request.getParameter("ctype");
        String cname = request.getParameter("cname");
        User user = Common.getUserFormSession(request,"admin_user");
        Result result = new Result();
        if (Constants.NOADD.equals(user.getU_isadd())) {
            return result.Fail("请联系管理员给予增加权限");
        }
        Easy_product_category easyProductCategory = new Easy_product_category();
        if ("1".equals(ctype.trim())) {
            easyProductCategory.setC_parentid("0");
        } else if ("2".equals(ctype.trim())) {
            easyProductCategory.setC_parentid(pc1);
        } else if ("3".equals(ctype.trim())) {
            easyProductCategory.setC_parentid(pc2);
        }
        if (null != ctype && !"".equals(ctype.trim())) {
            easyProductCategory.setC_type(Integer.parseInt(ctype));
        }
        easyProductCategory.setC_name(cname);
        int i = iepcs.addCategory(easyProductCategory);
        if (i <= 0) {
            return result.Fail("发生异常");
        }
        return result.Success();
    }

    /**
     * 获取分类
     *
     * @param request
     * @param response
     * @return
     */
    public String checkCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        List<Easy_product_category> categorys = iepcs.getCategoryByParentId("0");
        request.setAttribute("categorys", categorys);
        return "/admin/add.jsp";
    }

    /**
     * 验证是否有权限修改
     *
     * @param request
     * @param response
     * @return
     */
    public Result testUpdate(HttpServletRequest request, HttpServletResponse response) {
        Result result = new Result();
        User user = Common.getUserFormSession(request,"admin_user");
        if (Constants.NOUPDATE.equals(user.getU_isupdate())) {
            return result.Fail("请联系管理员给予修改权限");
        }
        return result.Success();
    }

    /**
     * 修改商品
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String updateProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Easy_product easyProduct = new Easy_product();
        Map<String, Object> map = FileUpload.fileUpload(request, "shop/images/", null, 500000, 300000, Arrays.asList("gif", "jpg", "png"));
        BeanUtils.populate(easyProduct, map);
        String fileName = (String) map.get("fileName");
        String p_filename = easyProduct.getP_filename();
        if (null != p_filename) {
            if (null == p_filename || "".equals(fileName)) {
                easyProduct.setP_filename(fileName);
            }
        }
        int size = iEasyProductService.updateProduct(easyProduct);
        if (size > 0) {
            request.setAttribute("size", size);
            request.setAttribute("curr", request.getParameter("currPaNo"));
        }
        return "/admin_product?action=allProduct";
    }

    /**
     * 修改商品时设置分类值
     *
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public String setProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Easy_product easyProduct = new Easy_product();
        String pid = request.getParameter("pid");
        if (null != pid && !"".equals(pid)) {
            easyProduct.setP_id(Integer.parseInt(pid));
        }
        Easy_product product = iEasyProductService.getEasyProductById(easyProduct);
        List<Easy_product_category> categorys1 = iepcs.getCategoryByParentId("0");
        List<Easy_product_category> categorys2 = iepcs.getCategoryByParentId(product.getP_categorylevel1().toString());
        List<Easy_product_category> categorys3 = iepcs.getCategoryByParentId(product.getP_categorylevel2().toString());
        request.setAttribute("categorys1", categorys1);
        request.setAttribute("categorys2", categorys2);
        request.setAttribute("categorys3", categorys3);
        request.setAttribute("product", product);
        return "/admin/edit.jsp";
    }

    /**
     * 验证是否有增加权限
     *
     * @param request
     * @param response
     * @return
     */
    public Result testAdd(HttpServletRequest request, HttpServletResponse response) {
        Result result = new Result();
        User user = Common.getUserFormSession(request,"admin_user");
        if (Constants.NOADD.equals(user.getU_isadd())) {
            return result.Fail("请联系管理员获取增加权限");
        }
        return result.Success();
    }

    /**
     * 获取子分类
     *
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public Result getCategoryByParentId(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Result result = new Result();
        String parentid = request.getParameter("parentid");
        List<Easy_product_category> categoryByParentId = iepcs.getCategoryByParentId(parentid);
        return result.Success(categoryByParentId);
    }

    /**
     * 添加商品
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String addProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Easy_product easyProduct = new Easy_product();
        Map<String, Object> map = FileUpload.fileUpload(request, "shop/images/", null, 5000, 300000, Arrays.asList("gif", "jpg", "png"));
        BeanUtils.populate(easyProduct, map);
        easyProduct.setP_isdelete("1");
        int i = iEasyProductService.addProduct(easyProduct);
        if (i > 0) {
            request.setAttribute("row", i);
            request.setAttribute("curr", request.getParameter("currPaNo"));
        }
        return "/admin_product?action=allProduct";
    }

    /**
     * 分页查询商品
     *
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public String allProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Easy_product easyProduct = new Easy_product();
        Integer pageSize = 6;
        String currPaNo = request.getParameter("currPaNo");
        String pName = request.getParameter("pName");
        if (null != pName) {
            pName = Tools.toUtf_8(pName);
        }
        easyProduct.setP_name(pName);
        PageBean page = iEasyProductService.getProducts(easyProduct, currPaNo, pageSize);
        request.setAttribute("pName", pName);
        request.setAttribute("products", page.getData());
        request.setAttribute("curr", page.getCurrPaNo());
        request.setAttribute("pageBean", page);
        return "/admin/main.jsp";
    }

    /**
     * 删除商品
     *
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public Result delProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String pid = request.getParameter("pid");
        User user = Common.getUserFormSession(request,"admin_user");
        return iEasyProductService.delProductById(pid, user);
    }


    /**
     * 是否下架
     *
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public Result isDelete(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Result result = new Result();
        String pid = request.getParameter("pid");
        String aClass = request.getParameter("class");
        if (null == pid || "".equals(pid.trim())) {
            result.Fail("选择商品时发生异常");
        }
        int delete = iEasyProductService.isDelete(Integer.parseInt(pid), aClass);
        if (delete <= 0) {
            result.Fail("下架失败");
        }
        return result.Success();
    }

    /**
     * 根据id返回分类对象
     *
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public Result getCategoryById(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Result result = new Result();
        String cid = request.getParameter("cid");
        if (null != cid && !"".equals(cid)) {
            Easy_product_category cateGoryById = iepcs.getCateGoryById(Integer.parseInt(cid));
            return result.Success(cateGoryById);
        }
        return result.Fail("出现异常");
    }

    /**
     * 修改分类
     *
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public Result updateCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Result result = new Result();
        User user = Common.getUserFormSession(request,"admin_user");
        if (Constants.NOUPDATE.equals(user.getU_isadd())) {
            return result.Fail("请联系管理员给予修改权限");
        }
        Easy_product_category easyProductCategory = new Easy_product_category();
        String cid = request.getParameter("cid");
        String pc1 = request.getParameter("pc1");
        String pc2 = request.getParameter("pc2");
        String type = request.getParameter("type");
        String name = request.getParameter("name");
        if (null == cid || "".equals(cid)) {
            return result.Fail("出现异常");
        }
        if ("1".equals(type)) {
            easyProductCategory.setC_parentid("0");
            easyProductCategory.setC_type(Integer.parseInt(type));
            easyProductCategory.setC_id(Integer.parseInt(cid));
            easyProductCategory.setC_name(name);
        } else if ("2".equals(type)) {
            easyProductCategory.setC_type(Integer.parseInt(type));
            easyProductCategory.setC_id(Integer.parseInt(cid));
            easyProductCategory.setC_parentid(pc1);
            easyProductCategory.setC_name(name);
        } else if ("3".equals(type)) {
            easyProductCategory.setC_type(Integer.parseInt(type));
            easyProductCategory.setC_id(Integer.parseInt(cid));
            easyProductCategory.setC_parentid(pc2);
            easyProductCategory.setC_name(name);
        }
        int i = iepcs.updateCategory(easyProductCategory);
        if (i > 0) {
            return result.Success();
        }
        return result.Fail("出现异常");
    }
}
