package cn.hd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: THyHyEntity
 * @Description: (会议表)
 * @author liuqiang
 */
@Entity
@Table(name = "T_HY_HY",schema="YAHY")
public class THyHyEntity  implements Serializable {
    private String id;//uuid
    private TXtRyEntity fqrEntity;//发起人信息:其中包含了所属单位
    private String cbdwId;//承办单位:id
    private String cbdwName;//承办单位:id
    private String mc;//会议名称
    private int mj;//密级
    private Date hySj;//会议时间
    private Date fqSj;//发起时间
    private String bz;//备注
    private TXtHyddEntity hyddEntity;//会议地点:多对一关系
    private Set<TXtDwEntity> chdwEntities = new HashSet<TXtDwEntity>();// 活动信息;//参会单位:多对多关系
    private Set<TXtDwEntity> sbcldwEntities = new HashSet<TXtDwEntity>();// 上报材料单位:多对多关系
    /* 一对多关系会议实例 */
    private Set<THyHyInstEntity> hyInstEntities = new HashSet<THyHyInstEntity>();

    //报送名单属于全局性质的： bsmd: 一对多情况
    private Set<THyChryEntity> chryEntities = new HashSet<THyChryEntity>();



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
    @Column(name = "CHDW_NAME",columnDefinition="varchar(200)",nullable = false)
    public String getCbdwName() {
        return cbdwName;
    }

    public void setCbdwName(String cbdwName) {
        this.cbdwName = cbdwName;
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
    @Column(name = "FQ_SJ",columnDefinition="datetime",nullable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    public Date getFqSj() {
        return fqSj;
    }

    public void setFqSj(Date fqSj) {
        this.fqSj = fqSj;
    }

    @Basic
    @Column(name = "BZ",columnDefinition="varchar(200)",nullable = false)
    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }
    /* 多对多 */
    @JsonIgnore
    @JoinTable(name = "T_XT_HY_CHDW",
            joinColumns = {@JoinColumn(name = "HY_ID",referencedColumnName = "id")},
            schema = "YAHY",
            inverseJoinColumns = {@JoinColumn(name = "DW_ID",referencedColumnName = "id")})
    @ManyToMany(cascade = CascadeType.REFRESH)
    public Set<TXtDwEntity> getChdwEntities() {
        return chdwEntities;
    }

    public void setChdwEntities(Set<TXtDwEntity> chdwEntities) {
        this.chdwEntities = chdwEntities;
    }
    @JsonIgnore
    @JoinTable(name = "T_XT_HY_SBDW",
            schema = "YAHY",
            joinColumns = {@JoinColumn(name = "HY_ID",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "DW_ID",referencedColumnName = "id")})
    @ManyToMany(cascade = CascadeType.REFRESH)
    public Set<TXtDwEntity> getSbcldwEntities() {
        return sbcldwEntities;
    }

    public void setSbcldwEntities(Set<TXtDwEntity> sbcldwEntities) {
        this.sbcldwEntities = sbcldwEntities;
    }

    @Basic
    @Column(name = "MJ",columnDefinition="NUMBER(10)",nullable = false)
    public int getMj() {
        return mj;
    }

    public void setMj(int mj) {
        this.mj = mj;
    }

    /**
     * 多对一
     * @return
     */
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "FQR_ID", referencedColumnName = "ID")
    @Fetch(FetchMode.SELECT)
    public TXtRyEntity getFqrEntity() {
        return fqrEntity;
    }

    public void setFqrEntity(TXtRyEntity fqrEntity) {
        this.fqrEntity = fqrEntity;
    }

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "HYDD_ID", referencedColumnName = "ID")
    @Fetch(FetchMode.SELECT)
    public TXtHyddEntity getHyddEntity() {
        return hyddEntity;
    }

    public void setHyddEntity(TXtHyddEntity hyddEntity) {
        this.hyddEntity = hyddEntity;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy = "hyHyEntity")
    @JsonIgnore
    public Set<THyHyInstEntity> getHyInstEntities() {
        return hyInstEntities;
    }
    public void setHyInstEntities(Set<THyHyInstEntity> hyInstEntities) {
        this.hyInstEntities = hyInstEntities;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy = "hyHyEntity")
    @JsonIgnore
    public Set<THyChryEntity> getChryEntities() {
        return chryEntities;
    }

    public void setChryEntities(Set<THyChryEntity> chryEntities) {
        this.chryEntities = chryEntities;
    }
}
