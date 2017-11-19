package cn.tms.dao;

import cn.tms.entity.Role;
import cn.tms.entity.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Happy on 2017-08-21.
 *角色DAO
 */
public interface IRoleDAO {
    //2. 角色列表 分页  获取单页数据带条件
    public List<Role> getOnePageData(Map<String, Object> map);
    //3.获取总记录数
    public int getTotalCount();
    //4.获取带条件的总记录数
    public int getTotalCountByRoleName(String rolename);
}
