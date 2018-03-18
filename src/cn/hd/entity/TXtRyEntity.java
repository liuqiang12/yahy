package cn.hd.entity;

import cn.hd.common.enumeration.GenderEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: TXtRyEntity
 * @Description: (系统人员)
 * @author liuqiang
 */
@Entity
@Table(name = "T_XT_RY",schema="YAHY")
@XmlRootElement(name = "TXtRyEntity")
@XmlAccessorType(XmlAccessType.FIELD)
/*@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","jsEntities"})*/
@TypeDefs({@TypeDef(name = "genderEnum", typeClass = GenderEnum.class,
        parameters = {@org.hibernate.annotations.Parameter(name = "class", value = "cn.hd.common.enumeration.GenderEnum")})
})

public class TXtRyEntity implements Serializable {
    private String id;//UUID
    private String xm;//姓名
    private GenderEnum xb;//性别0男1女
    private String zw;//职务
    private String bz;//备注
    private String lxfs;//联系方式
    private Integer fpryStatus;//分配人员:1分配人员  0不是分配人员
    /*private String dwId;//FK单位ID*/
    /*private String dwMc;//单位名称*/
    private Date cjSj;//创建时间
    private Integer flag;//
    private transient Set<TXtJsEntity> jsEntities = new HashSet<TXtJsEntity>();//角色人员
    /**
     * 接待信息
     */
    /*private Set<TJdJdxxEntity> jdJdxxEntities = new HashSet<TJdJdxxEntity>();//接待信息*/
    private TXtDwEntity xtDwEntity;//系统单位

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
    @Column(name = "XM",columnDefinition = "VARCHAR(100)",nullable = false)
    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    @Column(name="XB")
    @Type(type = "genderEnum")
    public GenderEnum getXb() {
        return xb;
    }

    public void setXb(GenderEnum xb) {
        this.xb = xb;
    }

    @Basic
    @Column(name = "ZW",columnDefinition = "VARCHAR(200)",nullable = false)
    public String getZw() {
        return zw;
    }

    public void setZw(String zw) {
        this.zw = zw;
    }

    @Basic
    @Column(name = "BZ",columnDefinition = "VARCHAR(200)")
    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    @Basic
    @Column(name = "LXFS",columnDefinition = "VARCHAR(200)")
    public String getLxfs() {
        return lxfs;
    }

    public void setLxfs(String lxfs) {
        this.lxfs = lxfs;
    }

    /*@Basic
    @Column(name = "DW_ID",columnDefinition = "VARCHAR(32)")
    public String getDwId() {
        return dwId;
    }*/

    /*public void setDwId(String dwId) {
        this.dwId = dwId;
    }*/

   /* @Basic
    @Column(name = "DW_MC",columnDefinition = "VARCHAR(150)")
    public String getDwMc() {
        return dwMc;
    }

    public void setDwMc(String dwMc) {
        this.dwMc = dwMc;
    }
*/
    @Basic
    @Column(name = "CJ_SJ",columnDefinition="datetime")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    public Date getCjSj() {
        return cjSj;
    }

    public void setCjSj(Date cjSj) {
        this.cjSj = cjSj;
    }

    /**
     * 分配人员标志
     * @return
     */
    @Basic
    @Column(name = "FPRY_STATUS",columnDefinition = "NUMBER(10)")
    public Integer getFpryStatus() {
        return fpryStatus;
    }

    public void setFpryStatus(Integer fpryStatus) {
        this.fpryStatus = fpryStatus;
    }



    /*@OneToMany(mappedBy = "tXtRyByRyId")
        public Collection<TXtJsRyEntity> gettXtJsRIESById() {
            return jsRyEntities;
        }

        public void settXtJsRIESById(Collection<TXtJsR yEntity> tXtJsRIESById) {
            this.tXtJsRIESById = tXtJsRIESById;
        }*/
    @JsonIgnore
    @JoinTable(name = "T_XT_JS_RY",
            schema = "YAHY",
            joinColumns = {@JoinColumn(name = "RY_ID",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "JS_ID",referencedColumnName = "id")})
    @ManyToMany(cascade = CascadeType.ALL)

    public Set<TXtJsEntity> getJsEntities() {
        return jsEntities;
    }
    public void setJsEntities(Set<TXtJsEntity> jsEntities) {
        this.jsEntities = jsEntities;
    }


    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    @JoinColumn(name = "DW_ID", referencedColumnName = "ID")
    @Fetch(FetchMode.SELECT)
    public TXtDwEntity getXtDwEntity() {
        return xtDwEntity;
    }

    public void setXtDwEntity(TXtDwEntity xtDwEntity) {
        this.xtDwEntity = xtDwEntity;
    }

    /*
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "ryEntity")
    *//*@JsonIgnore*//*
    public Set<TJdJdxxEntity> getJdJdxxEntities() {
        return jdJdxxEntities;
    }

    public void setJdJdxxEntities(Set<TJdJdxxEntity> jdJdxxEntities) {
        this.jdJdxxEntities = jdJdxxEntities;
    }*/
    @Basic
    @Column(name = "FLAG",columnDefinition = "NUMBER(10)")
    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TXtRyEntity ryEntity = (TXtRyEntity) o;

        if (id != null ? !id.equals(ryEntity.id) : ryEntity.id != null) return false;
        if (xm != null ? !xm.equals(ryEntity.xm) : ryEntity.xm != null) return false;
        if (xb != ryEntity.xb) return false;
        if (zw != null ? !zw.equals(ryEntity.zw) : ryEntity.zw != null) return false;
        if (bz != null ? !bz.equals(ryEntity.bz) : ryEntity.bz != null) return false;
        if (lxfs != null ? !lxfs.equals(ryEntity.lxfs) : ryEntity.lxfs != null) return false;
        if (fpryStatus != null ? !fpryStatus.equals(ryEntity.fpryStatus) : ryEntity.fpryStatus != null) return false;
        if (cjSj != null ? !cjSj.equals(ryEntity.cjSj) : ryEntity.cjSj != null) return false;
        return flag != null ? flag.equals(ryEntity.flag) : ryEntity.flag == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (xm != null ? xm.hashCode() : 0);
        result = 31 * result + (xb != null ? xb.hashCode() : 0);
        result = 31 * result + (zw != null ? zw.hashCode() : 0);
        result = 31 * result + (bz != null ? bz.hashCode() : 0);
        result = 31 * result + (lxfs != null ? lxfs.hashCode() : 0);
        result = 31 * result + (fpryStatus != null ? fpryStatus.hashCode() : 0);
        result = 31 * result + (cjSj != null ? cjSj.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TXtRyEntity{" +
                "id='" + id + '\'' +
                ", xm='" + xm + '\'' +
                ", xb=" + xb +
                ", zw='" + zw + '\'' +
                ", bz='" + bz + '\'' +
                ", lxfs='" + lxfs + '\'' +
                ", fpryStatus=" + fpryStatus +
                ", cjSj=" + cjSj +
                ", flag=" + flag +
                ", xtDwEntity.jgmc=" + xtDwEntity.getJgMc() +
                ", xtDwEntity.id=" + xtDwEntity.getId() +
                '}';
    }
}
