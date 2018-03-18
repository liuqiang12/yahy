package cn.hd.entity;

import cn.hd.common.enumeration.GenderCategoryEnum;
import cn.hd.common.enumeration.GenderEnum;
import cn.hd.common.enumeration.HotelEnum;

import java.io.Serializable;

/**
 * 目的是批量情况
 */
public class THyChryEntityVo implements Serializable {
    private String[] id;//uuid
    private String[] dwId;//参会单位ID
    private String[] dwName;//参会单位名称
    private Integer[] nbStatus;//内部人员1是内部人员  0是外部人员  默认是0

    private String[] xm;//姓名
    private GenderEnum[] xb;//性别0男1女
    private String[] zw;//职务
    private GenderCategoryEnum[] lb;//人员类别0：参会人员；            1：工作人员；            2：驾驶人员；            3：会议请假
    private String[] bz;//备注（如会议请假时的请假事由）
    private HotelEnum[] zsBz;//住宿标志0：不住宿；            1：住宿
    private String[] lxfs;//联系方式

    public String[] getId() {
        return id;
    }

    public void setId(String[] id) {
        this.id = id;
    }

    public String[] getDwId() {
        return dwId;
    }

    public void setDwId(String[] dwId) {
        this.dwId = dwId;
    }

    public String[] getDwName() {
        return dwName;
    }

    public void setDwName(String[] dwName) {
        this.dwName = dwName;
    }

    public Integer[] getNbStatus() {
        return nbStatus;
    }

    public void setNbStatus(Integer[] nbStatus) {
        this.nbStatus = nbStatus;
    }

    public String[] getXm() {
        return xm;
    }

    public void setXm(String[] xm) {
        this.xm = xm;
    }

    public GenderEnum[] getXb() {
        return xb;
    }

    public void setXb(GenderEnum[] xb) {
        this.xb = xb;
    }

    public String[] getZw() {
        return zw;
    }

    public void setZw(String[] zw) {
        this.zw = zw;
    }

    public GenderCategoryEnum[] getLb() {
        return lb;
    }

    public void setLb(GenderCategoryEnum[] lb) {
        this.lb = lb;
    }

    public String[] getBz() {
        return bz;
    }

    public void setBz(String[] bz) {
        this.bz = bz;
    }

    public HotelEnum[] getZsBz() {
        return zsBz;
    }

    public void setZsBz(HotelEnum[] zsBz) {
        this.zsBz = zsBz;
    }

    public String[] getLxfs() {
        return lxfs;
    }

    public void setLxfs(String[] lxfs) {
        this.lxfs = lxfs;
    }
}
