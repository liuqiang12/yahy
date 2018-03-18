package cn.hd.entity;

import cn.hd.common.enumeration.BanjieEnum;
import cn.hd.common.enumeration.EnumDescriptionUserType;
import cn.hd.common.enumeration.SignEnum;
import cn.hd.common.enumeration.UploadMaterialEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * SignEnum
 *
 * @ClassName: THyChdwEntity
 * @Description: (参会单位)
 * @author liuqiang
 */
@Entity
@Table(name = "T_HY_CHDW",schema="YAHY")
@XmlRootElement(name = "THyChdwEntity")
@XmlAccessorType(XmlAccessType.FIELD)
@TypeDefs({
    @TypeDef(
            name = "signEnum", typeClass = EnumDescriptionUserType.class,
            parameters = {@org.hibernate.annotations.Parameter(name = "class", value = "cn.hd.common.enumeration.SignEnum")}
    ),
    @TypeDef(
            name = "uploadMaterialEnum", typeClass = EnumDescriptionUserType.class,
            parameters = {@org.hibernate.annotations.Parameter(name = "class", value = "cn.hd.common.enumeration.UploadMaterialEnum")}
    ),
    @TypeDef(
            name = "banjieEnum", typeClass = EnumDescriptionUserType.class,
            parameters = {@org.hibernate.annotations.Parameter(name = "class", value = "cn.hd.common.enumeration.BanjieEnum")}
    )
})
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","hyEntities"})
public class THyChdwEntity  implements Serializable {
    private String id;//uuid
    private String dwId;//参会单位ID
    private SignEnum qsBz;//签收标志 签收标志；0：未签收；            1：已签收
    private Date qsSj;//签收时间
    private String qsrId;//签收人ID
    private UploadMaterialEnum sbclBz;//上报材料标志0：不需报材料1：需报未报材料2：已上报
    private BanjieEnum bjBz;//办结标志0：未办结；            1：已办结（需要报送参会人员名单）
    private Date bjJs;//办结时间
    private String bjrId;//办结人ID
    private Set<THyHyEntity> hyEntities = new HashSet<THyHyEntity>();//多对多关系:会议表

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
    @Column(name = "DW_ID",columnDefinition="varchar(32)",nullable = false)
    public String getDwId() {
        return dwId;
    }

    public void setDwId(String dwId) {
        this.dwId = dwId;
    }

    @Basic
    @Column(name = "QS_BZ",columnDefinition="NUMBER(10)",nullable = false)
    @Type(type = "signEnum")
    public SignEnum getQsBz() {
        return qsBz;
    }

    public void setQsBz(SignEnum qsBz) {
        this.qsBz = qsBz;
    }

    @Basic
    @Column(name = "QS_SJ",columnDefinition="datetime")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:MM:ss")
    public Date getQsSj() {
        return qsSj;
    }

    public void setQsSj(Date qsSj) {
        this.qsSj = qsSj;
    }

    @Basic
    @Column(name = "QSR_ID",columnDefinition="varchar(32)")
    public String getQsrId() {
        return qsrId;
    }

    public void setQsrId(String qsrId) {
        this.qsrId = qsrId;
    }

    @Basic
    @Column(name = "SBCL_BZ",columnDefinition="NUMBER(10)",nullable = false)
    @Type( type = "uploadMaterialEnum")
    public UploadMaterialEnum getSbclBz() {
        return sbclBz;
    }

    public void setSbclBz(UploadMaterialEnum sbclBz) {
        this.sbclBz = sbclBz;
    }

    @Basic
    @Column(name = "BJ_BZ",columnDefinition="NUMBER(10)",nullable = false)
    @Type( type = "banjieEnum")
    public BanjieEnum getBjBz() {
        return bjBz;
    }

    public void setBjBz(BanjieEnum bjBz) {
        this.bjBz = bjBz;
    }

    @Basic
    @Column(name = "BJ_JS",columnDefinition="datetime")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:MM:ss")
    public Date getBjJs() {
        return bjJs;
    }

    public void setBjJs(Date bjJs) {
        this.bjJs = bjJs;
    }

    @Basic
    @Column(name = "BJR_ID",columnDefinition="varchar(32)",nullable = true)
    public String getBjrId() {
        return bjrId;
    }

    public void setBjrId(String bjrId) {
        this.bjrId = bjrId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        THyChdwEntity that = (THyChdwEntity) o;

        if (qsBz != null ? !qsBz.equals(that.qsBz) : that.qsBz != null) return false;
        if (sbclBz != null ? !sbclBz.equals(that.sbclBz) : that.sbclBz != null) return false;
        if (bjBz != null ? !bjBz.equals(that.bjBz) : that.bjBz != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (dwId != null ? !dwId.equals(that.dwId) : that.dwId != null) return false;
        if (qsSj != null ? !qsSj.equals(that.qsSj) : that.qsSj != null) return false;
        if (qsrId != null ? !qsrId.equals(that.qsrId) : that.qsrId != null) return false;
        if (bjJs != null ? !bjJs.equals(that.bjJs) : that.bjJs != null) return false;
        if (bjrId != null ? !bjrId.equals(that.bjrId) : that.bjrId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dwId != null ? dwId.hashCode() : 0);
        result = 31 * result + (qsBz != null ? qsBz.hashCode() : 0);
        result = 31 * result + (qsSj != null ? qsSj.hashCode() : 0);
        result = 31 * result + (qsrId != null ? qsrId.hashCode() : 0);
        result = 31 * result + (sbclBz != null ? sbclBz.hashCode() : 0);
        result = 31 * result + (bjBz != null ? bjBz.hashCode() : 0);
        result = 31 * result + (bjJs != null ? bjJs.hashCode() : 0);
        result = 31 * result + (bjrId != null ? bjrId.hashCode() : 0);
        return result;
    }

    /**  关系维护情况 **/
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "T_HY_CHDW_LINK",
            schema = "YAHY",
            joinColumns = {@JoinColumn(name = "CHDW_ID",referencedColumnName="ID")},
            inverseJoinColumns = {@JoinColumn(name = "HY_ID",referencedColumnName="ID")
            }
    )
    @JsonIgnore
    @Transient
    public Set<THyHyEntity> getHyEntities() {
        return hyEntities;
    }

    public void setHyEntities(Set<THyHyEntity> hyEntities) {
        this.hyEntities = hyEntities;
    }
}
