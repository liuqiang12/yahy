package cn.hd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

/**
 * @ClassName: TXtDwEntity
 * @Description: (系统单位(组织机构))  : 承办单位
 * @author liuqiang
 */
@Entity
@Table(name = "T_XT_DW",schema="YAHY")
@XmlRootElement(name = "TXtDwEntity")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","hyEntities","xtRyEntities","jdJdxxEntities","children","hyHyEntities","chdwEntities"})
public class TXtDwEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;//uuid
    private String jgMc;//机构名称
    private String jgLxr;//机构联系人
    private String jgLxrHm;//机构联系人号码
    private Integer flag;//1表示默认项目初始化时的设置数据  0从界面初始化的数据

    private TXtDwEntity parent;//父节点
    private Set<TXtDwEntity> children =  new HashSet<TXtDwEntity>();  ;//子节点

    private  Set<THyHyEntity> hyChdwEntities = new HashSet<THyHyEntity>();//参会单位
    private Set<THyHyEntity> hySbdwEntities = new HashSet<THyHyEntity>();//上报材料单位
    private Set<TXtRyEntity> ryEntities = new HashSet<TXtRyEntity>();//系统人员
/*
    private Set<THyHyEntity> sbdwHyEntities = new HashSet<THyHyEntity>();//上报材料情况*/

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
    @Column(name = "JG_MC",columnDefinition="varchar(150)")
    public String getJgMc() {
        return jgMc;
    }

    public void setJgMc(String jgMc) {
        this.jgMc = jgMc;
    }

    @Basic
    @Column(name = "JG_LXR",columnDefinition="varchar(150)")
    public String getJgLxr() {
        return jgLxr;
    }

    public void setJgLxr(String jgLxr) {
        this.jgLxr = jgLxr;
    }

    @Basic
    @Column(name = "JG_LXR_HM",columnDefinition="varchar(20)")
    public String getJgLxrHm() {
        return jgLxrHm;
    }

    public void setJgLxrHm(String jgLxrHm) {
        this.jgLxrHm = jgLxrHm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TXtDwEntity that = (TXtDwEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (jgMc != null ? !jgMc.equals(that.jgMc) : that.jgMc != null) return false;
        if (jgLxr != null ? !jgLxr.equals(that.jgLxr) : that.jgLxr != null) return false;
        if (jgLxrHm != null ? !jgLxrHm.equals(that.jgLxrHm) : that.jgLxrHm != null) return false;
       /* if (fid != null ? !fid.equals(that.fid) : that.fid != null) return false;*/

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (jgMc != null ? jgMc.hashCode() : 0);
        result = 31 * result + (jgLxr != null ? jgLxr.hashCode() : 0);
        result = 31 * result + (jgLxrHm != null ? jgLxrHm.hashCode() : 0);
        /*result = 31 * result + (fid != null ? fid.hashCode() : 0);*/
        return result;
    }
 
    @JsonIgnore
    @JoinColumn(name="PARENT_ID")
    @ManyToOne(targetEntity=TXtDwEntity.class,fetch=FetchType.LAZY)
    public TXtDwEntity getParent() {
        return parent;
    }

    public void setParent(TXtDwEntity parent) {
        this.parent = parent;
    }

    @JsonIgnore
    @OneToMany(mappedBy="parent",targetEntity=TXtDwEntity.class,fetch=FetchType.LAZY)
    public Set<TXtDwEntity> getChildren() {
        return children;
    }
    public void setChildren(Set<TXtDwEntity> children) {
        this.children = children;
    }
    @Basic
    @Column(name = "FLAG",columnDefinition = "NUMBER(10)")
    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
    @JsonIgnore
    @ManyToMany(mappedBy = "chdwEntities")
    public Set<THyHyEntity> getHyChdwEntities() {
        return hyChdwEntities;
    }

    public void setHyChdwEntities(Set<THyHyEntity> hyChdwEntities) {
        this.hyChdwEntities = hyChdwEntities;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "sbcldwEntities")
    public Set<THyHyEntity> getHySbdwEntities() {
        return hySbdwEntities;
    }

    public void setHySbdwEntities(Set<THyHyEntity> hySbdwEntities) {
        this.hySbdwEntities = hySbdwEntities;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "xtDwEntity")
    @JsonIgnore
    public Set<TXtRyEntity> getRyEntities() {
        return ryEntities;
    }

    public void setRyEntities(Set<TXtRyEntity> ryEntities) {
        this.ryEntities = ryEntities;
    }

    /* @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "fqdwEntity")
        @JsonIgnore
        public Set<THyHyEntity> getSbdwHyEntities() {
            return sbdwHyEntities;
        }

        public void setSbdwHyEntities(Set<THyHyEntity> sbdwHyEntities) {
            this.sbdwHyEntities = sbdwHyEntities;
        }*/
    @Override
    public String toString() {
        return "TXtDwEntity{" +
                "id='" + id + '\'' +
                ", jgMc='" + jgMc + '\'' +
                ", jgLxr='" + jgLxr + '\'' +
                ", jgLxrHm='" + jgLxrHm + '\'' +
                ", flag=" + flag +

                '}';
    }
}
