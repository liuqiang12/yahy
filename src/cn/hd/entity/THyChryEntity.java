package cn.hd.entity;

import cn.hd.common.enumeration.EnumDescriptionUserType;
import cn.hd.common.enumeration.GenderCategoryEnum;
import cn.hd.common.enumeration.GenderEnum;
import cn.hd.common.enumeration.HotelEnum;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: THyChryEntity
 * @Description: (参会人员)
 * @author liuqiang
 *
 */
@Entity
@Table(name = "T_HY_CHRY",schema="YAHY")
@TypeDefs({
        @TypeDef(
                name = "genderEnum", typeClass = EnumDescriptionUserType.class,
                parameters = {@org.hibernate.annotations.Parameter(name = "class", value = "cn.hd.common.enumeration.GenderEnum")}
        ),
        @TypeDef(
                name = "genderCategoryEnum", typeClass = EnumDescriptionUserType.class,
                parameters = {@org.hibernate.annotations.Parameter(name = "class", value = "cn.hd.common.enumeration.GenderCategoryEnum")}
        ),
        @TypeDef(
                name = "hotelEnum", typeClass = EnumDescriptionUserType.class,
                parameters = {@org.hibernate.annotations.Parameter(name = "class", value = "cn.hd.common.enumeration.HotelEnum")}
        )
})
@XmlRootElement(name = "THyChryEntity")
@XmlAccessorType(XmlAccessType.FIELD)
/*@JsonIgnoreProperties(value={"hibernateLazyInitializer"})*/
public class THyChryEntity  implements Serializable {
    private String id;//uuid
    /*private String hyId;//会议ID(t_hy_hy.id)*/
    private String dwId;//参会单位ID
    private String dwName;//参会单位名称
    private Integer nbStatus;//内部人员1是内部人员  0是外部人员  默认是0
    private Integer lbZt;//人员类别0：参会人员；            1：工作人员；            2：驾驶人员；            3：会议请假

    private String xm;//姓名
    private Integer xb;//性别0男1女
    private String zw;//职务
    private GenderCategoryEnum lb;//人员类别0：参会人员；            1：工作人员；            2：驾驶人员；            3：会议请假
    private String bz;//备注（如会议请假时的请假事由）
    private HotelEnum zsBz;//住宿标志0：不住宿；            1：住宿
    private String zsstr;//住宿
    private String xbstr;//性别
    private String lxfs;//联系方式
    private Date bsSj;//报送时间
    private String bsrId;//报送人ID
    private String ryId;//人员id
    private Integer flagStatus=1;//标志：如果1代表发起方；2代表接收方。即参加会议对象:这个字段暂时没有使用。怀疑是设计问题。先预留这个字段
    //private THyHyEntity hyEntity;//会议表*/

    private THyHyEntity hyHyEntity;//会议:多对一

    @Id
    @Column(name="ID",columnDefinition="varchar(32)")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    @Basic
    @Column(name = "DW_ID",columnDefinition="varchar(32)",nullable = true)
    public String getDwId() {
        return dwId;
    }

    public void setDwId(String dwId) {
        this.dwId = dwId;
    }

    @Basic
    @Column(name = "XM",columnDefinition="varchar(100)",nullable = true)
    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    @Column(name="XB")
    public Integer getXb() {
        return xb;
    }

    public void setXb(Integer xb) {
        this.xb = xb;
    }

    @Basic
    @Column(name = "ZW",columnDefinition="varchar(100)",nullable = true)
    public String getZw() {
        return zw;
    }

    public void setZw(String zw) {
        this.zw = zw;
    }

    @Basic
    @Column(name = "LB",nullable = true)
    @Type( type = "genderCategoryEnum")
    public GenderCategoryEnum getLb() {
        return lb;
    }

    public void setLb(GenderCategoryEnum lb) {
        this.lb = lb;
    }

    @Basic
    @Column(name = "BZ",columnDefinition="varchar(200)")
    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    @Basic
    @Column(name = "ZS_BZ")
    @Type( type = "hotelEnum")
    public HotelEnum getZsBz() {
        return zsBz;
    }

    public void setZsBz(HotelEnum zsBz) {
        this.zsBz = zsBz;
    }

    @Basic
    @Column(name = "LXFS",columnDefinition="varchar(100)")
    public String getLxfs() {
        return lxfs;
    }

    public void setLxfs(String lxfs) {
        this.lxfs = lxfs;
    }
    @Basic
    @Column(name = "NB_STATUS",columnDefinition="NUMBER(10)")
    public Integer getNbStatus() {
        return nbStatus;
    }

    public void setNbStatus(Integer nbStatus) {
        this.nbStatus = nbStatus;
    }

    @Basic
    @Column(name = "BS_SJ",columnDefinition="datetime")
    public Date getBsSj() {
        return bsSj;
    }

    public void setBsSj(Date bsSj) {
        this.bsSj = bsSj;
    }

    @Basic
    @Column(name = "BSR_ID",columnDefinition="varchar(32)")
    public String getBsrId() {
        return bsrId;
    }

    public void setBsrId(String bsrId) {
        this.bsrId = bsrId;
    }
    /*@Basic
    @Column(name = "HY_ID",columnDefinition="varchar(32)")
    public String getHyId() {
        return hyId;
    }

    public void setHyId(String hyId) {
        this.hyId = hyId;
    }*/
    @Basic
    @Column(name = "RY_ID",columnDefinition="varchar(32)")
    public String getRyId() {
        return ryId;
    }

    public void setRyId(String ryId) {
        this.ryId = ryId;
    }
    @Transient
    public Integer getLbZt() {
        return lbZt;
    }

    public void setLbZt(Integer lbZt) {
        this.lbZt = lbZt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        THyChryEntity that = (THyChryEntity) o;

        if (lb != null ? !lb.equals(that.lb) : that.lb != null) return false;
        if (xb != null ? !xb.equals(that.xb) : that.xb != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        /*if (hyId != null ? !hyId.equals(that.hyId) : that.hyId != null) return false;*/
        if (dwId != null ? !dwId.equals(that.dwId) : that.dwId != null) return false;
        if (xm != null ? !xm.equals(that.xm) : that.xm != null) return false;
        if (zw != null ? !zw.equals(that.zw) : that.zw != null) return false;
        if (bz != null ? !bz.equals(that.bz) : that.bz != null) return false;
        if (zsBz != null ? !zsBz.equals(that.zsBz) : that.zsBz != null) return false;
        if (lxfs != null ? !lxfs.equals(that.lxfs) : that.lxfs != null) return false;
        if (bsSj != null ? !bsSj.equals(that.bsSj) : that.bsSj != null) return false;
        if (bsrId != null ? !bsrId.equals(that.bsrId) : that.bsrId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        /*result = 31 * result + (hyId != null ? hyId.hashCode() : 0);*/
        result = 31 * result + (dwId != null ? dwId.hashCode() : 0);
        result = 31 * result + (xm != null ? xm.hashCode() : 0);
        result = 31 * result + (xb != null ? xb.hashCode() : 0);
        result = 31 * result + (zw != null ? zw.hashCode() : 0);
        result = 31 * result + (lb != null ? lb.hashCode() : 0);
        result = 31 * result + (bz != null ? bz.hashCode() : 0);
        result = 31 * result + (zsBz != null ? zsBz.hashCode() : 0);
        result = 31 * result + (lxfs != null ? lxfs.hashCode() : 0);
        result = 31 * result + (bsSj != null ? bsSj.hashCode() : 0);
        result = 31 * result + (bsrId != null ? bsrId.hashCode() : 0);
        return result;
    }
    @Basic
    @Column(name = "DW_NAME",columnDefinition="varchar(500)")
    public String getDwName() {
        return dwName;
    }

    public void setDwName(String dwName) {
        this.dwName = dwName;
    }

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "HY_ID", referencedColumnName = "ID")
    @Fetch(FetchMode.SELECT)
    public THyHyEntity getHyHyEntity() {
        return hyHyEntity;
    }

    public void setHyHyEntity(THyHyEntity hyHyEntity) {
        this.hyHyEntity = hyHyEntity;
    }

    public Integer getFlagStatus() {
        return flagStatus;
    }

    public void setFlagStatus(Integer flagStatus) {
        this.flagStatus = flagStatus;
    }

    @Transient
    public String getZsstr() {
        return zsstr;
    }

    public void setZsstr(String zsstr) {
        this.zsstr = zsstr;
    }
    @Transient
    public String getXbstr() {
        return xbstr;
    }

    public void setXbstr(String xbstr) {
        this.xbstr = xbstr;
    }
    /*@ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "HY_ID",referencedColumnName = "ID", nullable = true)
    @Fetch(FetchMode.SELECT)
    @XmlElement(nillable = true)
    @JsonIgnore //过滤字段
    public THyHyEntity getHyEntity() {
        return hyEntity;
    }

    public void setHyEntity(THyHyEntity hyEntity) {
        this.hyEntity = hyEntity;
    }*/
}
