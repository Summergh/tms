package cn.tms.dao;

import cn.tms.entity.Privilege;
import cn.tms.entity.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Happy on 2017-08-21.
 */
public interface IUserInfoDAO {
    //1，登录的方法
    public UserInfo isLogin(UserInfo info);
    //2. 用户列表 分页  获取单页数据带条件
    public List<UserInfo> getOnePageData(Map<String,Object> map);
    //3.获取总记录数
    public int getTotalCount();
    //4.获取带条件的总记录数
    public int getTotalCountByUserName(String username);
    //05.获取指定角色下所有权限
    List<Privilege> getPersonPrivilege(Integer userid);
}
