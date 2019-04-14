package com.easybuynet.web.pre;

import com.easybuynet.entity.Constants;
import com.easybuynet.entity.Easy_user_address;
import com.easybuynet.entity.Result;
import com.easybuynet.entity.User;
import com.easybuynet.service.EasyBuyAddress.IEasyBuyAddressService;
import com.easybuynet.service.EasyBuyAddress.IEasyBuyAddressServiceImpl;
import com.easybuynet.utils.BaseServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/address")
public class Address extends BaseServlet {

    private IEasyBuyAddressService iEasyBuyAddressService = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        iEasyBuyAddressService = new IEasyBuyAddressServiceImpl();
    }

    //初始化地址模板
    public String toAddress(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Easy_user_address easyUserAddress = new Easy_user_address();
        Integer u_id = Integer.parseInt(request.getParameter("uid"));
        easyUserAddress.setU_id(u_id);
        easyUserAddress.setA_isdelete(Constants.NODELADDRESS);
        List<Easy_user_address> allAddress = iEasyBuyAddressService.getAllAddress(easyUserAddress);
        request.setAttribute("allAddress", allAddress);
        request.setAttribute("color2", "style='color: red;'");
        return "/shop/address.jsp";
    }

    //添加地址
    public Result addAddress(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Result result = new Result();
        Easy_user_address easyUserAddress = new Easy_user_address();
        String uid = request.getParameter("uid");
        User user = getUserFormSession(request);
        if (null == user) {
            return result.Fail("发生异常");
        }
        if (null != uid && !"".equals(uid)) {
            user.setU_id(Integer.parseInt(uid.trim()));
        }
        //获取参数
        String area = request.getParameter("area");
        String address = request.getParameter("address");
        String aName = request.getParameter("aName");
        String aPhone = request.getParameter("aPhone");
        String aRemark = request.getParameter("aRemark");
        String flag = request.getParameter("flag");
        if (aRemark.trim().equals("")) {
            aRemark = "无";
        }
        easyUserAddress.setA_area(area);
        easyUserAddress.setA_address(address);
        easyUserAddress.setA_phone(aPhone);
        easyUserAddress.setA_username(aName);
        easyUserAddress.setA_remark(aRemark);
        //处理添加地址请求
        if ("true".equals(flag)) {
            int row = iEasyBuyAddressService.addAddress(easyUserAddress, user, true);
            if (row > 0) {
                return result.Success();
            }
            //处理修改地址请求
        } else if ("false".equals(flag)) {
            String dateTime = request.getParameter("dateTime");
            String aid = request.getParameter("aid");
            if (null == aid || "".equals(aid)) {
                return result.Fail("发生异常");
            }
            easyUserAddress.setA_id(Integer.parseInt(aid));
            easyUserAddress.setA_creattime(dateTime);
            int row = iEasyBuyAddressService.addAddress(easyUserAddress, user, false);
            if (row > 0) {
                return result.Success();
            }
        }
        return result.Fail("发生异常");
    }


    //删除地址
    public Result delAddress(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Result result = new Result();
        String uid = request.getParameter("uid");
        if (null == uid || "".equals(uid)) {
            return result.Fail("用户选择异常");
        }
        String aid = request.getParameter("aid");
        if (null != aid && !"".equals(aid)) {
            int i = iEasyBuyAddressService.updateAddress(aid, Constants.ISDELADDRESS, Integer.parseInt(uid));
            if (i > 0) {
                return result.Success();
            }
        }
        return result.Fail("出现异常");
    }

    //修改默认地址
    public Result editDefault(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Result result = new Result();
        String uid = request.getParameter("uid");
        if (null == uid || "".equals(uid)) {
            return result.Fail("出现异常");
        }
        String aid = request.getParameter("aid");
        if (null == aid || "".equals(aid)) {
            return result.Fail("出现异常");
        }
        int i = iEasyBuyAddressService.editDefaultByAid(Constants.ISDEFAULT, Integer.parseInt(uid), aid);
        if (i > 0) {
            return result.Success();
        }
        return result.Fail("出现异常");
    }

    public String toEditAddress(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Easy_user_address easyUserAddress = new Easy_user_address();
        easyUserAddress.setA_isdelete(Constants.NODELADDRESS);
        String aid = request.getParameter("aid");
        String uid = request.getParameter("uid");
        if (null != uid && !"".equals(uid)) {
            Integer u_id = Integer.parseInt(uid);
            easyUserAddress.setU_id(u_id);
        }
        List<Easy_user_address> allAddress = iEasyBuyAddressService.getAllAddress(easyUserAddress);
        request.setAttribute("allAddress", allAddress);
        if (null != aid && !"".equals(aid)) {
            Integer a_id = Integer.parseInt(aid);
            easyUserAddress.setA_id(a_id);
        }
        List<Easy_user_address> address = iEasyBuyAddressService.getAllAddress(easyUserAddress);
        if (null != address) {
            Easy_user_address easyUserAddress1 = address.get(0);
            String a_area = easyUserAddress1.getA_area();
            String[] split = a_area.split("-");
            request.setAttribute("province", split[0]);
            request.setAttribute("city", split[1]);
            request.setAttribute("town", split[2]);
            request.setAttribute("Address", easyUserAddress1);
        }
        return "/shop/editaddress.jsp";
    }

    private User getUserFormSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        return user;
    }
}
