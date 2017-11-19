package cn.tms.controller;

import cn.tms.entity.DicInfo;
import cn.tms.entity.Privilege;
import cn.tms.entity.UserInfo;
import cn.tms.service.IDicInfoService;
import cn.tms.service.IPrivilegeService;
import cn.tms.service.IUserInfoService;
import cn.tms.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Happy on 2017-08-21.
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {

    //植入用户的service
    @Resource(name ="useService" )
    IUserInfoService userInfoService;

    //植入字典的Service
    @Resource(name = "dicService")
    IDicInfoService dicInfoService;

    //植入权限的Service
    @Resource(name = "privilegeService")
    IPrivilegeService privilegeService;
    //登录方法
    @RequestMapping("/doLogin")
    @ResponseBody
    public Object doLogin(HttpSession session,UserInfo info){
        UserInfo user = userInfoService.isLogin(info);
        if (user!=null&&user.getUsername()!=null){
            //登录成功,将用户名放入Session
            session.setAttribute("userinfo",user);
            return "y";
        }else {
            //登录失败
            return "login";
        }
    }
    //转向系统主界面
    @RequestMapping("/doMain")
    public String doMain(HttpSession session,Model model){
        List<Privilege> allMenus = getAllMenus(session, model);
        model.addAttribute("privilegeList",allMenus);
        return "main";
    }

    //查询所有菜单，返回json
    @ResponseBody
    @RequestMapping("/getAllMenusJson")
    public Object getAllMenusJson(HttpSession session){
        //新的容器 保存有父子关系的权限
        List<Privilege> rootMenus=new ArrayList<Privilege>();
        UserInfo user= (UserInfo)session.getAttribute("userinfo");
        //保存的是平级的权限集合  使用内存级别的手段，构造成有关系的权限集合
        List<Privilege> privilegeList = privilegeService.getAllPrivileges();
        for (Privilege item:privilegeList){
            Privilege childMenu=item;
            int pid = childMenu.getParent();
            if (pid==0){
                rootMenus.add(item);
            }else{
                for (Privilege innerMenu:privilegeList){
                    Integer id = innerMenu.getId();
                    if (id==pid){
                        Privilege parentMenu=innerMenu;
                        parentMenu.getChildren().add(childMenu);
                        break;
                    }
                }
            }
        }
        return rootMenus;
    }

    //查询用户拥有的权限，返回json
    @ResponseBody
    @RequestMapping("/getPrivilegeJson")
    public Object getPrivilegeJson(String rid){
        //保存的是平级的权限集合  使用内存级别的手段，构造成有关系的权限集合
        List<Privilege> privilegeList = privilegeService.getAllPrivilegesByRoldId(rid);
        return privilegeList;
    }


    //查询用户拥有的权限
    public List<Privilege> getAllMenus(HttpSession session,Model model){
        //新的容器 保存有父子关系的权限
        List<Privilege> rootMenus=new ArrayList<Privilege>();
        UserInfo user= (UserInfo)session.getAttribute("userinfo");
        //保存的是平级的权限集合  使用内存级别的手段，构造成有关系的权限集合
        List<Privilege> privilegeList = privilegeService.getAllPrivilegesByEmpId(user.getUserid().toString());
        for (Privilege item:privilegeList){
            Privilege childMenu=item;
            int pid = childMenu.getParent();
            if (pid==0){
                rootMenus.add(item);
            }else{
                for (Privilege innerMenu:privilegeList){
                    Integer id = innerMenu.getId();
                    if (id==pid){
                        Privilege parentMenu=innerMenu;
                        parentMenu.getChildren().add(childMenu);
                        break;
                    }
                }
            }
        }
        //request.setAttribute(“”)等价  。有层级关系的集合
        return rootMenus;
    }

    /*2.用户列表 让用户看到该视图*/
    @RequestMapping("/showUserList")
    public String showUserList(){
        return "/page/user/userList";
    }

    @ResponseBody
    //获取单页用户数据
    @RequestMapping("/selectUser")
    public Object selectUser(@RequestParam(defaultValue = "1",value = "page") int pageIndex, @RequestParam(defaultValue = "2",value = "rows") int pageSize, UserInfo info){
        //key:total
        //key:rows:
        Map<String,Object> map=new HashMap<String,Object>();
        PageUtil pp=null;
        if (info==null||info.getUsername()==null){
            pp=userInfoService.getOnePageData(pageIndex-1,pageSize);

        }else{
            pp= userInfoService.getOnePageData(pageIndex-1,pageSize,info);
        }
        Integer total = pp.getTotalRecords();
        map.put("total",total);
        map.put("rows",pp.getList());
        return map;
    }

    //保存权限
    @RequestMapping("/savePrivilege")
    @ResponseBody
    public void savePrivilege(String rid,String s){
        privilegeService.delPrivilegeByRoldId(rid);
        String[] ids = s.split(",");


        for (String item:ids) {
            int pid=Integer.parseInt(item);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("rid",Integer.parseInt(rid));
            map.put("pid",pid);
            privilegeService.addDataToRolePrivilege(map);
        }

    }

    //转向机房和教员设定界面
    @RequestMapping("/labWeekSet")
   public String labWeekSet(){
        return "/page/userManage";
   }

   //转向内容发布的页面
    @RequestMapping("/publishingSet")
    public String publishingSet (){
       return "/page/content/publishing";
    }

}
