package cn.tms.service.impl;

import cn.tms.dao.IRoleDAO;
import cn.tms.dao.IUserInfoDAO;
import cn.tms.entity.Role;
import cn.tms.entity.UserInfo;
import cn.tms.service.IRoleService;
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
@Service("roleService")
public class RoleServiceImpl implements IRoleService {
    //植入dao对象
    @Resource(name = "IRoleDAO")
    IRoleDAO roleDAO;

    //2.获取总记录数
    public int getTotalCount() {
        return roleDAO.getTotalCount();
    }
    //3.获取一页用户数据
    public PageUtil getOnePageData(int pageIndex, int pageSize) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("pageIndex",pageIndex*pageSize);
        map.put("pageSize",pageSize);
        PageUtil page=new PageUtil(); //实例化一个PageUtil对象
        page.setPageSize(pageSize); //给PageUtil属性赋值
        page.setPageIndex(pageIndex);//给PageUtil的pageIndex赋值

        page.setTotalRecords(roleDAO.getTotalCount());
        int totalPages=page.getTotalRecords()%page.getPageSize()==0?page.getTotalRecords()/page.getPageSize():page.getTotalRecords()/page.getPageSize()+1;
        page.setTotalPages(totalPages);
        //为什么dao层方法的入参我写成map？？？
        List<Role> list = roleDAO.getOnePageData(map);
        page.setList(list);
        return page;
    }

    public int getTotalCountByRoleName(String userName) {
        return roleDAO.getTotalCountByRoleName(userName);
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
            page.setTotalRecords(roleDAO.getTotalCountByRoleName(info.getUsername()));
        }else{
            page.setTotalPages(roleDAO.getTotalCount());
        }
        int totalPages=page.getTotalRecords()%page.getPageSize()==0?page.getTotalRecords()/page.getPageSize():page.getTotalRecords()/page.getPageSize()+1;
        page.setTotalPages(totalPages);
        //为什么dao层方法的入参我写成map？？？
        List<Role> list = roleDAO.getOnePageData(map);
        page.setList(list);
        return page;
    }
}
