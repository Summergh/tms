package cn.tms.service;

import cn.tms.entity.DicInfo;

import java.util.List;

/**
 * Created by Happy on 2017-09-27.
 */
public interface IDicInfoService {

    //1.获取所有教师集合
    public List<DicInfo> findAllTeacher();
    //2.获取所有机房集合
    public List<DicInfo> findAllLab();
}
