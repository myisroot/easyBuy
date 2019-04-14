package com.easybuynet.web.pre;

import com.easybuynet.entity.*;
import com.easybuynet.service.EasyProductCategoryService.*;
import com.easybuynet.service.OrderService.ICartService;
import com.easybuynet.service.OrderService.ICartServiceImpl;
import com.easybuynet.service.OrderService.IOrderService;
import com.easybuynet.service.OrderService.IOrderServiceImpl;
import com.easybuynet.utils.*;
import com.easybuynet.web.Common;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/ShoppingCat")
public class ShoppingCat extends BaseServlet {
    private static Logger logger = Logger.getLogger(ShoppingCat.class.getName());
    private IEasyProductService iEasyProductService = null;
    private IEasyProductCategoryService iEasyProductCategoryService = null;
    private ICartService iCartService = null;
    private IAddressService iAddressService = null;
    private IOrderService iOrderService = null;

    @Override
    public void init() throws ServletException {
        iEasyProductService = new IEasyProductServiceImpl();
        iEasyProductCategoryService = new IEasyProductCategoryServiceImpl();
        iCartService = new ICartServiceImpl();
        iAddressService = new IAddressServiceImpl();
        iOrderService = new IOrderServiceImpl();
    }

    /**
     * 购物车添加商品
     *
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public Result add(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Easy_product easy_product = new Easy_product();
        Result result = new Result();
        User user = Common.getUserFormSession(request, "user");
        if (null == user) {
            return result.Fail("请先登入");
        }
        if (Constants.NOLOGIN.equals(user.getU_islogin())) {
            return result.Fail("你的账户已被冻结不能购物");
        }
        //获取商品id
        String id = request.getParameter("p_id");
        easy_product.setP_id(Integer.parseInt(id));
        //获取数量
        String number = request.getParameter("number");
        Integer quantity = number != null ? Integer.parseInt(number) : 1;
        //根据商品id获取商品
        Easy_product easyProductById = iEasyProductService.getEasyProductById(easy_product);
        //购买数量大于库存时
        if (Integer.parseInt(easyProductById.getP_stock()) < quantity) {
            return result.Fail("商品数量不能大于库存");
        }
        //获取session中的购物车
        Cart cart = getCatFormSession(request);
        //增加商品
        result = cart.addItem(easyProductById, quantity);
        //判断是否增加成功
        if (result.getStatus() == Constants.SUCCESS) {
            //获取购物车的总金额
            BigDecimal sum = cart.getSum();
            //获取当前要增加的商品的总金额
            Integer price = Integer.parseInt(easyProductById.getP_price()) * quantity;

            //判断是否大于0
            if (null != sum) {
                //设置总金额
                cart.setSum(new BigDecimal(price).add(sum));
            } else {
                cart.setSum(new BigDecimal(price));
            }
        }
        return result.Success(cart);
    }


    /**
     * 从session中获取购物车信息
     *
     * @param request
     * @return
     */
    private Cart getCatFormSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("shopCat");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("shopCat", cart);
        }
        return cart;
    }

    private void clearCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("shopCat");
    }

    /**
     * 刷新搜索页面
     *
     * @param request
     * @param response
     * @return
     */
    public String reCart(HttpServletRequest request, HttpServletResponse response) {
        return "shop/common/search.jsp";
    }

    /**
     * 初始化购物车页面
     *
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public String toSettlement(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        List<Easy_product_category> allProduct = iEasyProductCategoryService.getCategoryByParentId("0");
        List<Easy_product_categoryVO> allVO = iEasyProductCategoryService.getAllVO();
        request.setAttribute("allVO", allVO);
        request.setAttribute("allProduct", allProduct);
        return "shop/BuyCar.jsp";
    }

    //跳转到确认订单页面
    public String toSettlement2(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Easy_user_address easy_user_address = new Easy_user_address();
        User user = Common.getUserFormSession(request, "user");
        easy_user_address.setU_id(user.getU_id());
        easy_user_address.setA_isdelete(Constants.NODELADDRESS);
        List<Easy_user_address> allAddress = iAddressService.getAllAddress(easy_user_address);
        request.setAttribute("allAddress", allAddress);
        return "shop/cart_two.jsp";
    }

    //跳转到结算订单页面
    public Object toSettlement3(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Cart cart = getCatFormSession(request);
        Result result = checkCart(request);
        if (result.getStatus() == Constants.FAIL) {
            return result;
        }
        //获取地址id
        String addressId = request.getParameter("address");
        User user = Common.getUserFormSession(request, "user");
        Easy_user_address easyUserAddress = new Easy_user_address();
        //设置地址id
        easyUserAddress.setA_id(Integer.parseInt(addressId));
        //根据id查询出地址信息
        List<Easy_user_address> allAddress = iAddressService.getAllAddress(easyUserAddress);
        if (allAddress.size() > 0) {
            Easy_user_address easy_user_address = allAddress.get(0);
            //生成订单
            String address = easy_user_address.getA_area() + easy_user_address.getA_address();
            Easy_order easy_order = iOrderService.addOrder(user, address, cart, Integer.parseInt(addressId));
            request.setAttribute("easy_order", easy_order);
        }
        logger.debug("生成订单成功" + addressId);
        request.setAttribute("sum", cart.getSum());
        logger.info(user.getU_loginname() + "提交一笔订单:" + cart.getSum());
        clearCart(request);
        return "shop/cart_three.jsp";
    }

    //判断结算的数量是否超过库存
    public Result checkCart(HttpServletRequest request) throws SQLException {
        Easy_product easy_product = new Easy_product();
        Result result = new Result();
        User user = Common.getUserFormSession(request, "user");
        if (Constants.NOLOGIN.equals(user.getU_islogin())) {
            return result.Fail("你的账户已被冻结不能购物");
        }
        Cart cart = getCatFormSession(request);
        for (ShoppingItem item : cart.getShopItems()) {
            Integer p_id = item.getEasyProduct().getP_id();
            easy_product.setP_id(p_id);
            Easy_product product = iEasyProductService.getEasyProductById(easy_product);
            if (Integer.parseInt(product.getP_stock()) < item.getQuantity()) {
                return result.Fail(product.getP_name() + "商品数量不足");
            }
        }
        return result;
    }

    /**
     * 修改购物车显示金额
     *
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public Result changeQuantity(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        Result result = new Result();
        Easy_product easy_product = new Easy_product();
        //获取参数
        String p_id = request.getParameter("p_id");
        String quantity = request.getParameter("quantity");
        //判断是否有参数
        if (null == p_id && null == quantity) {
            return result.Fail("参数不能为空");
        }
        //获取已存在的购物车
        Cart cart = getCatFormSession(request);
        easy_product.setP_id(Integer.parseInt(p_id));
        //根据id查询对象
        Easy_product easyProductById = iEasyProductService.getEasyProductById(easy_product);
        if (Integer.parseInt(quantity) > Integer.parseInt(easyProductById.getP_stock())) {
            return result.Fail("商品数量不足");
        }
        //修改购物车
        cart = iCartService.change(p_id, Integer.parseInt(quantity), cart);
        session.setAttribute("shopCat", cart);
        return result.Success(cart);
    }

    /**
     * 删除购物车商品
     *
     * @param request
     * @param response
     * @return
     */
    public Result delItem(HttpServletRequest request, HttpServletResponse response) {
        Result result = new Result();
        HttpSession session = request.getSession();
        String p_id = request.getParameter("p_id");
        if (null == p_id) {
            return result.Fail("参数不能为空");
        }
        Cart cart = getCatFormSession(request);
        cart = iCartService.delShopItem(p_id, cart);
        session.setAttribute("shopCat", cart);
        return result.Success(cart);
    }
}
