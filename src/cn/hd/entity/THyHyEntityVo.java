package cn.hd.entity;

import cn.hd.common.enumeration.BanjieEnum;
import cn.hd.common.enumeration.EnumDescriptionUserType;
import cn.hd.common.enumeration.ShangbaoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: THyHyEntity
 * @Description: (会议表)
 * @author liuqiang
 */
@TypeDefs({
        @TypeDef(
                name = "shangbaoEnum", typeClass = EnumDescriptionUserType.class,
                parameters = {@org.hibernate.annotations.Parameter(name = "class", value = "cn.hd.common.enumeration.ShangbaoEnum")}
        ),
        @TypeDef(
                name = "banjieEnum", typeClass = EnumDescriptionUserType.class,
                parameters = {@org.hibernate.annotations.Parameter(name = "class", value = "cn.hd.common.enumeration.BanjieEnum")}
        )
})
public class THyHyEntityVo implements Serializable {
    private String id;//uuid
    /*private String fid;//会议发起单位id*/
    private String cbdwId;//承办单位ID
    private String mc;//会议名称
    private int mj;//密级
    private Date hySj;//会议时间
    private String bz;//备注
    private ShangbaoEnum sbZt;//办结完成后，此文件是否上报的状态：0未报、1已报
    private Integer sbZtInt;//上报标志
    private BanjieEnum bjBz;//办结标志0：未办结；            1：已办结
    private Integer jbBzint;//办结标志
    private Date fqSj;//发起时间

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
    @Column(name = "CBDW_ID",columnDefinition="varchar(32)",nullable = false)
    public String getCbdwId() {
        return cbdwId;
    }

    public void setCbdwId(String cbdwId) {
        this.cbdwId = cbdwId;
    }

    @Basic
    @Column(name = "MC",columnDefinition="varchar(200)",nullable = false)
    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    @Basic
    @Column(name = "HY_SJ",columnDefinition="datetime",nullable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    public Date getHySj() {
        return hySj;
    }

    public void setHySj(Date hySj) {
        this.hySj = hySj;
    }

    @Basic
    @Column(name = "BZ",columnDefinition="varchar(200)",nullable = false)
    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    @Basic
    @Column(name = "SB_ZT")
    @Type( type = "shangbaoEnum")
    public ShangbaoEnum getSbZt() {
        return sbZt;
    }

    public void setSbZt(ShangbaoEnum sbZt) {
        this.sbZt = sbZt;
    }

    @Basic
    @Column(name = "BJ_BZ")
    @Type( type = "banjieEnum")
    public BanjieEnum getBjBz() {
        return bjBz;
    }

    public void setBjBz(BanjieEnum bjBz) {
        this.bjBz = bjBz;
    }

    @Basic
    @Column(name = "FQ_SJ",columnDefinition="datetime",nullable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    public Date getFqSj() {
        return fqSj;
    }

    public void setFqSj(Date fqSj) {
        this.fqSj = fqSj;
    }

    /*@Basic
    @Column(name = "FQR_ID",columnDefinition="varchar(32)",nullable = false)
    public String getFqrId() {
        return fqrId;
    }

    public void setFqrId(String fqrId) {
        this.fqrId = fqrId;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        THyHyEntityVo that = (THyHyEntityVo) o;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (bjBz != null ? !bjBz.equals(that.bjBz) : that.bjBz != null) return false;
        if (cbdwId != null ? !cbdwId.equals(that.cbdwId) : that.cbdwId != null) return false;
        if (mc != null ? !mc.equals(that.mc) : that.mc != null) return false;
        if (hySj != null ? !hySj.equals(that.hySj) : that.hySj != null) return false;
        if (bz != null ? !bz.equals(that.bz) : that.bz != null) return false;
        if (sbZt != null ? !sbZt.equals(that.sbZt) : that.sbZt != null) return false;
        if (fqSj != null ? !fqSj.equals(that.fqSj) : that.fqSj != null) return false;
        /*if (fqrId != null ? !fqrId.equals(that.fqrId) : that.fqrId != null) return false;*/

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cbdwId != null ? cbdwId.hashCode() : 0);
        result = 31 * result + (mc != null ? mc.hashCode() : 0);
        result = 31 * result + (hySj != null ? hySj.hashCode() : 0);
        result = 31 * result + (bz != null ? bz.hashCode() : 0);
        result = 31 * result + (sbZt != null ? sbZt.hashCode() : 0);
        result = 31 * result + (bjBz != null ? bjBz.hashCode() : 0);
        result = 31 * result + (fqSj != null ? fqSj.hashCode() : 0);
        /*result = 31 * result + (fqrId != null ? fqrId.hashCode() : 0);*/
        return result;
    }

    public int getMj() {
        return mj;
    }

    public void setMj(int mj) {
        this.mj = mj;
    }

    public Integer getSbZtInt() {
        return sbZtInt;
    }

    public void setSbZtInt(Integer sbZtInt) {
        this.sbZtInt = sbZtInt;
    }

    public Integer getJbBzint() {
        return jbBzint;
    }

    public void setJbBzint(Integer jbBzint) {
        this.jbBzint = jbBzint;
    }
}
