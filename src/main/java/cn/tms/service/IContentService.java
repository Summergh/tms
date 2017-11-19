package cn.tms.service;

import cn.tms.entity.Content;
import cn.tms.entity.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by guo on 2017/11/6.
 */
public interface IContentService {
    //查询所有的内容
    public List<Content> getAllContent();
    //查询所有的内容
    public List<Content> getAllContents(String columnname);
    //根据syscode查询所有新闻
    public List<News> getAllNewBySyscode(Map<String,Object> map);
    //多条件查询
    public List<News> getMuchNews(Map<String,Object> map);
    public int count(Map<String,Object> map);
    //添加
    public int addNews(News news);
    //修改
    public News selectNewsById(String id);
    public int updNewsById(News news);
}
