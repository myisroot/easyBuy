package com.easybuynet.web.pre;

import com.easybuynet.dao.EasyProduct.IEasyProduct;
import com.easybuynet.dao.EasyProduct.IEasyProductImpl;
import com.easybuynet.entity.Easy_news;
import com.easybuynet.entity.Easy_product;
import com.easybuynet.entity.Easy_product_category;
import com.easybuynet.service.EasyNewsService.EasyNewsServiceImpl;
import com.easybuynet.service.EasyNewsService.IEasyNewsService;
import com.easybuynet.service.EasyProductCategoryService.IEasyProductCategoryService;
import com.easybuynet.service.EasyProductCategoryService.IEasyProductCategoryServiceImpl;
import com.easybuynet.service.EasyProductCategoryService.IEasyProductService;
import com.easybuynet.service.EasyProductCategoryService.IEasyProductServiceImpl;
import com.easybuynet.utils.BaseServlet;
import com.easybuynet.utils.Easy_product_categoryVO;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/Home")
public class Home extends BaseServlet {
    private static Logger logger = Logger.getLogger(Home.class.getName());
    private IEasyProductCategoryService iepcs = null;
    private IEasyNewsService iEasyNewsService = null;

    @Override
    public void init() throws ServletException {
        iepcs = new IEasyProductCategoryServiceImpl();
        iEasyNewsService = new EasyNewsServiceImpl();
    }

    /**
     * 进入首页时初始化分类
     *
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public String index(HttpServletRequest request, HttpServletResponse response) {
        try {
            Easy_product_category easyProductCategory = new Easy_product_category();
            easyProductCategory.setC_parentid("0");
            //查询所有的分类
            List<Easy_product_categoryVO> allVO = iepcs.getAllVO();
            List<Easy_product_category> allProduct = iepcs.getCategoryByParentId("0");
            List<Easy_news> allNews = iEasyNewsService.getAllNews(new Easy_news());
            logger.debug("初始化成功");
            request.setAttribute("allProduct", allProduct);
            request.setAttribute("allNews", allNews);
            request.setAttribute("allVO", allVO);
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }

        return "/shop/index.jsp";
    }
}
