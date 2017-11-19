package cn.tms.service.impl;

import cn.tms.dao.IUserInfoDAO;
import cn.tms.entity.UserInfo;
import cn.tms.service.IUserInfoService;
import cn.tms.util.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2017-9-27 09:42:36
 */
@Service("useService")
public class UserInfoServiceImpl implements IUserInfoService {
    //植入dao对象
    @Resource(name = "IUserInfoDAO")
    IUserInfoDAO userInfoDAO;
    public UserInfo isLogin(UserInfo info) {
        return userInfoDAO.isLogin(info);
    }
    //2.获取总记录数
    public int getTotalCount() {
        return userInfoDAO.getTotalCount();
    }
    //3.获取一页用户数据
    public PageUtil getOnePageData(int pageIndex, int pageSize) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("pageIndex",pageIndex*pageSize);
        map.put("pageSize",pageSize);
        PageUtil<UserInfo> page=new PageUtil<UserInfo>(); //实例化一个PageUtil对象
        page.setPageSize(pageSize); //给PageUtil属性赋值
        page.setPageIndex(pageIndex);//给PageUtil的pageIndex赋值

        page.setTotalRecords(userInfoDAO.getTotalCount());
        int totalPages=page.getTotalRecords()%page.getPageSize()==0?page.getTotalRecords()/page.getPageSize():page.getTotalRecords()/page.getPageSize()+1;
        page.setTotalPages(totalPages);
        //为什么dao层方法的入参我写成map？？？
        List<UserInfo> list = userInfoDAO.getOnePageData(map);
        page.setList(list);
        return page;
    }

    public int getTotalCountByUserName(String userName) {
        return userInfoDAO.getTotalCountByUserName(userName);
    }
    //3.获取一页用户数据 模糊查询
    public PageUtil getOnePageData(int pageIndex, int pageSize, UserInfo info) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("pageIndex",pageIndex*pageSize);
        map.put("pageSize",pageSize);
        if (info!=null) {
            map.put("userName", info.getUsername());
        }
        PageUtil page=new PageUtil(); //实例化一个PageUtil对象

        page.setPageSize(pageSize); //给PageUtil属性赋值
        page.setPageIndex(pageIndex);//给PageUtil的pageIndex赋值
        //
        if (info!=null){
            page.setTotalRecords(userInfoDAO.getTotalCountByUserName(info.getUsername()));
        }else{
            page.setTotalPages(userInfoDAO.getTotalCount());
        }
        int totalPages=page.getTotalRecords()%page.getPageSize()==0?page.getTotalRecords()/page.getPageSize():page.getTotalRecords()/page.getPageSize()+1;
        page.setTotalPages(totalPages);
        //为什么dao层方法的入参我写成map？？？
        List<UserInfo> list = userInfoDAO.getOnePageData(map);
        page.setList(list);
        return page;
    }
}
