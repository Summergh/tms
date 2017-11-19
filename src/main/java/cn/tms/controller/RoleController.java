package cn.tms.controller;

import cn.tms.entity.UserInfo;
import cn.tms.service.IRoleService;
import cn.tms.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Happy on 2017-10-25.
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    //植入用户的service
    @Resource(name ="roleService" )
    IRoleService roleService;
    /*2.用户列表 让用户看到该视图*/
    @RequestMapping("/showRoleList")
    public String showRoleList(){
        return "/page/user/roleList";
    }

    @ResponseBody
    //获取单页用户数据
    @RequestMapping("/selectRole")
    public Object selectRole(@RequestParam(defaultValue = "1",value = "page") int pageIndex, @RequestParam(defaultValue = "2",value = "rows") int pageSize, UserInfo info){
        Map<String,Object> map=new HashMap<String,Object>();
        PageUtil pp=null;
        if (info==null||info.getUsername()==null){
            pp=roleService.getOnePageData(pageIndex-1,pageSize);

        }else{
            pp= roleService.getOnePageData(pageIndex-1,pageSize,info);
        }
        Integer total = pp.getTotalRecords();
        map.put("total",total);
        map.put("rows",pp.getList());
        return map;
    }


    public IRoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }
}
