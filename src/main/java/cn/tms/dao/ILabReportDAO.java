package cn.tms.dao;

import cn.tms.entity.LabReport;

/**
 * Created by Happy on 2017-09-27.
 */
public interface ILabReportDAO {
    //01.保存报表
    public int saveLabReport(LabReport labinfo);
    //02.获取报表数据
    public LabReport getLabReport();
}
