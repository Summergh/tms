package cn.tms.controller;

import cn.tms.entity.DicInfo;
import cn.tms.service.IDicInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Happy on 2017-09-27.
 */
@Controller
@RequestMapping("/dic")
public class IDicInfoController {

    @Resource(name = "dicService")
    IDicInfoService dicInfoService;

    @RequestMapping("/findTeacher")
    @ResponseBody
    //查询所有老师
    public Object findTeacher(){
        List<DicInfo> allTeachers = dicInfoService.findAllTeacher();
        return allTeachers;
    }
    //查询所有机房
    @RequestMapping("/findLab")
    @ResponseBody
    public Object findLab(){
        List<DicInfo> alllabls = dicInfoService.findAllLab();
        return alllabls;
    }
}
