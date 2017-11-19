package cn.tms.entity;
import cn.tms.entity.Content;
import cn.tms.entity.UserInfo;

import java.util.Date;

public class News {

  private int id;
  private String newtitle;
  private String cmSyscode;
  private int clickcount;
  private int userid;
  private Date updateTime;
  private String type;
  private Content content;
  private UserInfo userInfo;
  private String imageurl;
  private String abstracts;
  private String tag;
  private String contents;
  //修改前图片路径、
  private String imageurlbefore;

  public String getImageurlbefore() {
    return imageurlbefore;
  }

  public void setImageurlbefore(String imageurlbefore) {
    this.imageurlbefore = imageurlbefore;
  }

  public String getImageurl() {
    return imageurl;
  }

  public void setImageurl(String imageurl) {
    this.imageurl = imageurl;
  }

  public String getAbstracts() {
    return abstracts;
  }

  public void setAbstracts(String abstracts) {
    this.abstracts = abstracts;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNewtitle() {
    return newtitle;
  }

  public void setNewtitle(String newtitle) {
    this.newtitle = newtitle;
  }

  public String getCmSyscode() {
    return cmSyscode;
  }

  public void setCmSyscode(String cmSyscode) {
    this.cmSyscode = cmSyscode;
  }

  public int getClickcount() {
    return clickcount;
  }

  public void setClickcount(int clickcount) {
    this.clickcount = clickcount;
  }

  public int getUserid() {
    return userid;
  }

  public void setUserid(int userid) {
    this.userid = userid;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Content getContent() {
    return content;
  }

  public void setContent(Content content) {
    this.content = content;
  }

  public UserInfo getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(UserInfo userInfo) {
    this.userInfo = userInfo;
  }
}
