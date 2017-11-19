package cn.tms.service.impl;

import cn.tms.dao.ILabReportDAO;
import cn.tms.dao.IUserInfoDAO;
import cn.tms.entity.LabReport;
import cn.tms.service.ILabReportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Happy on 2017-09-27.
 */
@Service("labReportService")
public class LabReportServiceImpl implements ILabReportService {
    //植入dao对象
    @Resource(name = "ILabReportDAO")
    ILabReportDAO labReportDAO;
    public int saveLabReport(LabReport labinfo) {
        return labReportDAO.saveLabReport(labinfo);
    }

    public LabReport getLabReport() {
        return labReportDAO.getLabReport();
    }
}
