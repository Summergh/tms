package cn.tms.entity;

/**
 * Created by Happy on 2017-09-27.
 * 数据字典实体
 */
public class DicInfo {
    private Integer dicid;
    private String dicname;
    private Integer dictype;

    public Integer getDicid() {
        return dicid;
    }

    public void setDicid(Integer dicid) {
        this.dicid = dicid;
    }

    public String getDicname() {
        return dicname;
    }

    public void setDicname(String dicname) {
        this.dicname = dicname;
    }

    public Integer getDictype() {
        return dictype;
    }

    public void setDictype(Integer dictype) {
        this.dictype = dictype;
    }
}
