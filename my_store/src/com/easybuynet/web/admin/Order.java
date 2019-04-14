package com.easybuynet.web.admin;

import com.easybuynet.entity.*;
import com.easybuynet.service.EasyProductCategoryService.IEasyProductCategoryService;
import com.easybuynet.service.EasyProductCategoryService.IEasyProductCategoryServiceImpl;
import com.easybuynet.service.OrderService.IOrderService;
import com.easybuynet.service.OrderService.IOrderServiceImpl;
import com.easybuynet.service.User.IUserService;
import com.easybuynet.service.User.IUserServiceImpl;
import com.easybuynet.utils.BaseServlet;
import com.easybuynet.utils.Tools;
import com.easybuynet.web.Common;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin_order")
public class Order extends BaseServlet {
    private Logger logger = Logger.getLogger(Order.class.getName());
    private IOrderService iOrderService = null;
    private IEasyProductCategoryService iepcs = null;
    private IUserService iUserService = null;

    @Override
    public void init() throws ServletException {
        iOrderService = new IOrderServiceImpl();
        iepcs = new IEasyProductCategoryServiceImpl();
        iUserService = new IUserServiceImpl();
    }


    /**
     * 进入后台管理时查询全部订单
     *
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public String index(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Order_address orderAddress = new Order_address();
        Integer pageSize = 6;
        //获取当前页
        String currPaNo = request.getParameter("currPaNo");
        String oName = request.getParameter("oName");
        if (null != oName) {
            oName = Tools.toUtf_8(oName);
            orderAddress.setA_username(Tools.toFuzzyParam(oName));

        }
        Integer curr = currPaNo == null || "".equals(currPaNo.trim()) ? 1 : Integer.parseInt(currPaNo);
        //获取分页数据
        List<Order_address> orderByUid = iOrderService.getOrder(orderAddress, curr, pageSize, true);
        //获取分页数量
        int orderSize = iOrderService.getOrderCount(orderAddress);
        if (null == orderByUid || orderByUid.size() == 0) {
            orderAddress.setA_username(null);
            orderAddress.setO_serialnumber(Tools.toFuzzyParam(oName));
            orderByUid = iOrderService.getOrder(orderAddress, curr, pageSize, true);
            orderSize = iOrderService.getOrderCount(orderAddress);
        }
        if (null == orderByUid || orderByUid.size() == 0) {
            orderAddress.setO_serialnumber(null);
            orderAddress.setA_area(Tools.toFuzzyParam(oName));
            orderByUid = iOrderService.getOrder(orderAddress, curr, pageSize, true);
            orderSize = iOrderService.getOrderCount(orderAddress);
        }
        //存储pagebean
        PageBean pageBean = new PageBean();
        pageBean.setCurrPaNo(curr);
        pageBean.setPageSize(pageSize);
        pageBean.setAllCounts(orderSize);
        request.setAttribute("oName", oName);
        request.setAttribute("curr", currPaNo);
        request.setAttribute("orderByUid", orderByUid);
        request.setAttribute("page", pageBean);
        return "/admin/order.jsp";
    }

    public String orderInfo(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Integer pageSize = 6;
        String currPaNO = request.getParameter("currPaNO");
        Integer curr = currPaNO == null || "".equals(currPaNO) ? 1 : Integer.parseInt(currPaNO);
        Easy_order_detail easyOrderDetail = new Easy_order_detail();
        String oId = request.getParameter("oid");
        if (null != oId && !"".equals(oId)) {
            easyOrderDetail.setO_id(Integer.parseInt(oId));
            List<Order_info> orderInfos = iOrderService.getOrderInfoByOid(Integer.parseInt(oId));
            request.setAttribute("allByOid", orderInfos);
        }
        return "/admin/order_info.jsp";
    }

    /**
     * 删除订单
     *
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public Result delOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Result result = new Result();
        Easy_order easyOrder = new Easy_order();
        String oid = request.getParameter("oid");
        User user = Common.getUserFormSession(request, "admin_user");
        if (Constants.NOREMOVE.equals(user.getU_isdelete())) {
            return result.Fail("请联系管理员给予删除权限");
        }
        if (null == oid) {
            return result.Fail("未选择订单");
        }
        easyOrder.setO_id(Integer.parseInt(oid));
        int row = iOrderService.delOrder(easyOrder);
        if (row <= 0) {
            return result.Fail("删除时发生异常");
        }
        return result.Success();
    }
}
