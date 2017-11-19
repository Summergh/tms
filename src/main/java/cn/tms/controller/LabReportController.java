package cn.tms.controller;

import cn.tms.entity.DicInfo;
import cn.tms.entity.LabReport;
import cn.tms.service.ILabReportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Happy on 2017-09-27.
 */
@Controller
@RequestMapping("/labReport")
public class LabReportController {

    @Resource(name = "labReportService")
    ILabReportService labReportService;

    //查询所有机房
    @RequestMapping("/saveLab")
    @ResponseBody
    public Object findLab(HttpServletRequest request){
        LabReport info=new LabReport();
        info.setContent(request.getParameter("data"));
        info.setTitle(request.getParameter("title"));
        int count=labReportService.saveLabReport(info);
        return count;
    }
    //显示报表调整页面
    @RequestMapping("/lookLabInfo")
    public String lookLabInfo(){
        return "/page/labLook";
    }

    @RequestMapping("/getReport")
    @ResponseBody
    public Object getReport(){
        LabReport labReport = labReportService.getLabReport();
        return labReport;
    }
}

