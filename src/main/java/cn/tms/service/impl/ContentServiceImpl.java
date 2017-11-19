package cn.tms.service.impl;

import cn.tms.dao.IContentDAO;
import cn.tms.entity.Content;

import cn.tms.entity.News;
import cn.tms.service.IContentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by guo on 2017/11/6.
 */
@Service("contentService")
public class ContentServiceImpl implements IContentService {
    @Resource(name = "IContentDAO")
    IContentDAO contentDAO;

    public List<Content> getAllContent() {
        return contentDAO.getAllContent();
    }

    public List<Content> getAllContents(String columnname) {
        return contentDAO.getAllContents(columnname);
    }

    public List<News> getAllNewBySyscode(Map<String,Object> map) {
        return contentDAO.getAllNewBySyscode(map);
    }

    public List<News> getMuchNews(Map<String,Object> map) {
        return contentDAO.getMuchNews(map);
    }

    public int count(Map<String, Object> map) {
        return contentDAO.count(map);
    }

    public int addNews(News news) {
        return contentDAO.addNews(news);
    }

    public News selectNewsById(String id) {
        return contentDAO.selectNewsById(id);
    }

    public int updNewsById(News news) {
        return contentDAO.updNewsById(news);
    }


}
