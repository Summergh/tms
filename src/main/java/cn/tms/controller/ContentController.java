package cn.tms.controller;

import cn.tms.entity.Content;

import cn.tms.entity.News;
import cn.tms.entity.UserInfo;
import cn.tms.service.IContentService;
import cn.tms.util.OssUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guo on 2017/11/6.
 */
@Controller
@RequestMapping("/con")
public class ContentController {
    @Resource(name = "contentService")
    IContentService contentService;

    @ResponseBody
    @RequestMapping("/getAllContent")
    public Object getAllContent() {
        //新的容器 保存有父子关系的权限
        List<Content> rootMenus = new ArrayList<Content>();
        //保存的是平级的权限集合  使用内存级别的手段，构造成有关系的权限集合
        List<Content> privilegeList = contentService.getAllContent();
        for (Content item : privilegeList) {
            Content childMenu = item;
            String pid = childMenu.getParentcode();
            if ("0".equals(pid)) {
                rootMenus.add(item);
            } else {
                for (Content innerMenu : privilegeList) {
                    String id = innerMenu.getSyscode();
                    if (id.equals(pid)) {
                        Content parentMenu = innerMenu;
                        parentMenu.getChildren().add(childMenu);
                        break;
                    }
                }
            }
        }
        return rootMenus;
    }

    @ResponseBody
    @RequestMapping("/getAllContents")
    public Object getAllContents(String columnname) {
        System.out.println("__________________"+columnname);
        //新的容器 保存有父子关系的权限
        List<Content> rootMenus = new ArrayList<Content>();
        //保存的是平级的权限集合  使用内存级别的手段，构造成有关系的权限集合
        List<Content> privilegeList = contentService.getAllContents(columnname);
        System.out.println("_________________"+privilegeList.size());
        for (Content item : privilegeList) {
            Content childMenu = item;
            String pid = childMenu.getParentcode();
            if ("0".equals(pid)) {
                rootMenus.add(item);
            } else {
                for (Content innerMenu : privilegeList) {
                    String id = innerMenu.getSyscode();
                    if (id.equals(pid)) {
                        Content parentMenu = innerMenu;
                        parentMenu.getChildren().add(childMenu);
                        break;
                    }
                }
            }
        }

        return rootMenus;
    }

    @ResponseBody
    @RequestMapping("/getNewsBySyscode")
    public Object getNewsBySyscode(HttpServletResponse response,String titles,String cmsyscode, HttpServletRequest request, Integer page, Integer rows) throws IOException {
        Integer pageindex=(page==null||page==0)?1:page;
        Integer pageSize=(rows==null||rows==0)?2:rows;
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("pageindex",pageindex);
        map.put("pageSize",pageSize);
        map.put("cmsyscode",cmsyscode);
        request.getSession().setAttribute("titles",titles);
        request.getSession().setAttribute("cmsyscode",cmsyscode);
        List<News> allNewBySyscode = contentService.getAllNewBySyscode(map);

        /* return allNewBySyscode;*/
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("total",this.contentService.count(map));
        System.out.println(this.contentService.count(map)+"+++++++++++++++"+allNewBySyscode+"++++++++++++++");
        jsonObject.put("rows",allNewBySyscode);
        response.getWriter().write(jsonObject.toString());//转化为JSOn格式
        return null;
    }

    /*@ResponseBody
    @RequestMapping("/getMuchNews")
    public Object getMuchNews(String newtitle,String type,HttpServletRequest request){
        String syscode = (String)request.getSession().getAttribute("cmsyscode");
        System.out.println(syscode+"=="+newtitle+"=="+type);
        List<News> muchNews = contentService.getMuchNews(syscode, newtitle, type);
        return muchNews;
    }
*/
    @ResponseBody
    @RequestMapping("/getMuchNews")
    public Object getMuchNews(HttpServletResponse response, HttpServletRequest request, Integer page, Integer rows,String newtitle,String type) throws IOException {
        System.out.println("______"+"_________"+newtitle+"________"+type);
        String cmsyscode = (String)request.getSession().getAttribute("cmsyscode");
        Integer pageindex=(page==null||page==0)?1:page;
        Integer pageSize=(rows==null||rows==0)?2:rows;
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("pageindex",pageindex);
        map.put("pageSize",pageSize);
        map.put("cmsyscode",cmsyscode);
        map.put("type",type);
        map.put("newtitle",newtitle);
        System.out.println(cmsyscode+"_______"+type+"_______"+newtitle);
        List<News> allNewBySyscode = contentService.getMuchNews(map);

        /* return allNewBySyscode;*/
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("total",this.contentService.count(map));
        jsonObject.put("rows",allNewBySyscode);
        response.getWriter().write(jsonObject.toString());//转化为JSOn格式
        return null;
    }


    //添加
    @ResponseBody
    @RequestMapping("/addNews")
    public Object addNews(News news,HttpServletRequest request){

        String imageurl = news.getImageurl();
        String ossUrl = OssUtil.getOSSUrl("C:/Users/guo/Desktop/自定义mvc/" + imageurl);
        news.setImageurl(ossUrl);
        UserInfo userinfo = (UserInfo)request.getSession().getAttribute("userinfo");
        news.setUserid(userinfo.getUserid());
        int i = contentService.addNews(news);
        return i;
    }


    //跳转修改页面
    @RequestMapping("/updColumnDetailSet")
    public Object updColumnDetailSet(String id) {
        System.out.println("____________" + id);
        return "page/content/updColumnDetail";
    }

    @ResponseBody
    @RequestMapping("/selectNewsById")
    public Object selectNewsById(String id){
        News newss = contentService.selectNewsById(id);
        return newss;
    }

    @ResponseBody
    @RequestMapping("/updNewsById")
    public Object updNewsById(News news,HttpServletRequest request){
        String imageurl = news.getImageurl();
        String imageurlbefore = news.getImageurlbefore();
        System.out.println(imageurl+"_______________________"+imageurlbefore);
        UserInfo userinfo = (UserInfo)request.getSession().getAttribute("userinfo");
        news.setUserid(userinfo.getUserid());
        if(imageurl!=null){
            String ossUrl = OssUtil.getOSSUrl("C:/Users/guo/Desktop/自定义mvc/" + imageurl);
            news.setImageurl(ossUrl);
        }else{
            news.setImageurl(imageurlbefore);
        }
        int i = contentService.updNewsById(news);
        return i;
    }
















    @RequestMapping("/fileImage")
    public String fileImage(String image,HttpServletRequest request) {
        String ossUrl = OssUtil.getOSSUrl("C:/Users/guo/Desktop/自定义mvc/" + image);
          System.out.println(ossUrl);
         request.getSession().setAttribute("url", ossUrl);
       /* System.out.println(code + "222222222");
        return code;*/
       return "test";
    }
    @RequestMapping("/addSet")
    public String addSet() {
        return "page/content/addColumnDetail";
    }
}
