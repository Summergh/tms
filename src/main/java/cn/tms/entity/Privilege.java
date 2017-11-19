package cn.tms.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Happy on 2017-10-16.
 * 权限实体
 */
public class Privilege {
    private Integer id;  //权限编号
    private String url; // 对应的功能链接地址
    private String name; // 权限名称（要唯一）
    private String icon; // 图标
    private int  parent; //父权限对象
    //子权限集合
    private List<Privilege> children=new ArrayList<Privilege>();

    public List<Privilege> getChildren() {
        return children;
    }

    public void setChildren(List<Privilege> children) {
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }
}
