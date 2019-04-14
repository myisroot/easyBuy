package com.easybuynet.service.EasyNewsService;

import com.easybuynet.dao.EasyNews.IEasyNewImpl;
import com.easybuynet.dao.EasyNews.IEasyNews;
import com.easybuynet.entity.*;
import com.easybuynet.utils.Easy_product_categoryVO;
import com.easybuynet.utils.Tools;

import java.sql.SQLException;
import java.util.List;

public class EasyNewsServiceImpl implements IEasyNewsService {
    private IEasyNews iEasyNews = new IEasyNewImpl();

    @Override
    public List<Easy_news> getAllNews(Easy_news easy_news) throws SQLException {
        return iEasyNews.getAllNews(easy_news);
    }

    @Override
    public List<Easy_news> getNews(Easy_news easy_news, Integer curr, Integer pageSize) throws SQLException {
        return iEasyNews.getNews(easy_news, curr, pageSize, true);
    }

    @Override
    public int getNewsCount(Easy_news easy_news) throws SQLException {
        return iEasyNews.getNews(easy_news, 0, 0, false).size();
    }


    @Override
    public Result delNew(String nid, User user) throws SQLException {
        Result result = new Result();
        Easy_news easyNews = new Easy_news();
        if (Constants.NOREMOVE.equals(user.getU_isdelete())) {
            return result.Fail("请联系管理员给予删除权限");
        }
        if (null == nid || "".equals(nid.trim())) {
            return result.Fail("选择时发生异常");
        }
        easyNews.setN_id(Integer.parseInt(nid));
        int i = iEasyNews.delNews(easyNews);
        if (i <= 0) {
            return result.Fail("删除时发生异常");
        }
        return result.Success();
    }

    @Override
    public PageBean getNew(Easy_news easyNews, String currPaNo, Integer pageSize) throws SQLException {
        PageBean pageBean = new PageBean();
        String title = easyNews.getN_title();
        Integer curr = currPaNo == null || "".equals(currPaNo.trim()) ? 1 : Integer.parseInt(currPaNo);
        if (null != title && !"".equals(title.trim())) {
            String N_title = title;
            easyNews.setN_title(Tools.toFuzzyParam(N_title));
        } else {
            easyNews.setN_title(null);
        }
        //获取商品分页数据
        List<Easy_news> news = iEasyNews.getNews(easyNews, curr, pageSize, true);
        //设置page
        pageBean.setCurrPaNo(curr);
        pageBean.setPageSize(pageSize);
        //获取查询的数量
        pageBean.setAllCounts(iEasyNews.getNews(easyNews, curr, pageSize, false).size());
        pageBean.setData(news);
        return pageBean;
    }

}
