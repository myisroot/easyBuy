package com.easybuynet.web.pre;

import com.easybuynet.entity.*;
import com.easybuynet.service.EasyProductCategoryService.IEasyProductCategoryService;
import com.easybuynet.service.EasyProductCategoryService.IEasyProductCategoryServiceImpl;
import com.easybuynet.service.EasyProductCategoryService.IEasyProductService;
import com.easybuynet.service.EasyProductCategoryService.IEasyProductServiceImpl;
import com.easybuynet.service.OrderService.IOrderService;
import com.easybuynet.service.OrderService.IOrderServiceImpl;
import com.easybuynet.utils.BaseServlet;
import com.easybuynet.utils.Easy_product_categoryVO;
import com.easybuynet.utils.OrderVo;
import com.easybuynet.utils.Tools;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/product")
public class Product extends BaseServlet {
    private static Logger logger = Logger.getLogger(Product.class.getName());
    private IEasyProductCategoryService iepcs = null;
    private IEasyProductService iEasyProductService = null;
    private IOrderService iOrderService = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        iepcs = new IEasyProductCategoryServiceImpl();
        iEasyProductService = new IEasyProductServiceImpl();
        iOrderService = new IOrderServiceImpl();
    }

    /**
     * 获取查询以及分类数据
     *
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public String getProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, UnsupportedEncodingException {
        Integer pageSize = 8;
        Easy_product_category easyProductCategory = new Easy_product_category();
        easyProductCategory.setC_parentid("0");
        //获取当前页
        String currPaN = request.getParameter("currPaNo");
        Integer c = currPaN == null ? 1 : Integer.parseInt(currPaN);
        //获取商品名
        String c_name = request.getParameter("c_name");
        if (null != c_name) {
            c_name = Tools.toUtf_8(c_name);
        }
        //分类id
        String c_id = request.getParameter("c_id");
        //查询分页数据
        List<Easy_product> allRroDuct = iEasyProductService.getAllRroDuct(c_name, c_id, c, pageSize, true);
        //获取所有分类
        List<Easy_product_categoryVO> allVO = iepcs.getAllVO();
        //获取所有一级分类
        List<Easy_product_category> allProduct = iepcs.getCategoryByParentId("0");
        //创建分页实体类
        PageBean pageBean = new PageBean();
        //设置总数量
        pageBean.setAllCounts(iEasyProductService.getCounts(c_name, c_id));
        //设置每页显示数量
        pageBean.setPageSize(pageSize);
        //设置当前页
        pageBean.setCurrPaNo(c);
        request.setAttribute("pageBean", pageBean);
        request.setAttribute("allRroDuct", allRroDuct);
        request.setAttribute("allProduct", allProduct);
        request.setAttribute("allVO", allVO);
        request.setAttribute("c_id", c_id);
        //设置查询内容便于回显
        request.setAttribute("search", c_name);
        return "/shop/BrandList.jsp";
    }

    public String productInfo(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Easy_product easy_product = new Easy_product();
        List<Easy_product_category> allProduct = iepcs.getCategoryByParentId("0");
        String p_id = request.getParameter("p_id");
        if (p_id != null) {
            easy_product.setP_id(Integer.parseInt(p_id));
        }
        Easy_product easyProductById = iEasyProductService.getEasyProductById(easy_product);
        List<Easy_product_categoryVO> allVO = iepcs.getAllVO();
        request.setAttribute("easyProduct", easyProductById);
        request.setAttribute("allVO", allVO);
        request.setAttribute("allProduct", allProduct);
        return "shop/Product.jsp";
    }

    public String getOrderVo(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        PageBean pageBean = new PageBean();
        Order_address orderAddress = new Order_address();
        String uid = request.getParameter("uid");
        Integer pageSize = 5;
        //获取当前页
        String currPaNo = request.getParameter("currPaNo");
        Integer curr = currPaNo == null || "".equals(currPaNo.trim()) ? 1 : Integer.parseInt(currPaNo);
        if (null != uid && !"".equals(uid.trim())) {
            orderAddress.setU_id(Integer.parseInt(uid));
        }
        List<OrderVo> orderVos = iOrderService.getOrderVo(orderAddress, curr, pageSize, true);
        Integer counts = iOrderService.getOrderCount(orderAddress);
        pageBean.setPageSize(pageSize);
        pageBean.setCurrPaNo(curr);
        pageBean.setAllCounts(counts);
        pageBean.setData(orderVos);
        request.setAttribute("color1", "style='color: red;'");
        request.setAttribute("page", pageBean);
        return "/shop/order.jsp";
    }
}
