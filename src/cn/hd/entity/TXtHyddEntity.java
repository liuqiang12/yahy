package cn.hd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: TXtHyddEntity
 * @Description: (会议地点)
 * @author liuqiang
 */
@Entity
@Table(name = "T_XT_HYDD",schema="YAHY")
@XmlRootElement(name = "TXtHyddEntity")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","jdxxEntities"})
public class TXtHyddEntity implements Serializable {
    private String id;
    private String mc;//名称
    private Integer sort;//排序
    private String fid;//父ID
    private THyHyEntity hyEntity;//会议表
    private Integer flag;//标志

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
    @Column(name = "MC",columnDefinition="varchar(50)")
    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    @Basic
    @Column(name = "SORT",columnDefinition="NUMBER(2)")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Basic
    @Column(name = "FID",columnDefinition="varchar(50)")
    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TXtHyddEntity that = (TXtHyddEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (mc != null ? !mc.equals(that.mc) : that.mc != null) return false;
        if (sort != null ? !sort.equals(that.sort) : that.sort != null) return false;
        if (fid != null ? !fid.equals(that.fid) : that.fid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (mc != null ? mc.hashCode() : 0);
        result = 31 * result + (sort != null ? sort.hashCode() : 0);
        result = 31 * result + (fid != null ? fid.hashCode() : 0);
        return result;
    }
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "HY_ID",referencedColumnName = "ID", nullable = true)
    @Fetch(FetchMode.SELECT)
    @XmlElement(nillable = true)
    @JsonIgnore //过滤字段
    public THyHyEntity getHyEntity() {
        return hyEntity;
    }

    public void setHyEntity(THyHyEntity hyEntity) {
        this.hyEntity = hyEntity;
    }

  /*  @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "JD_ID",referencedColumnName = "ID", nullable = true)
    @Fetch(FetchMode.SELECT)
    @XmlElement(nillable = true)
    @JsonIgnore //过滤字段
    public TJdJdxxEntity getJdxxEntity() {
        return jdxxEntity;
    }

    public void setJdxxEntity(TJdJdxxEntity jdxxEntity) {
        this.jdxxEntity = jdxxEntity;
    }*/
    @Basic
    @Column(name = "FLAG",columnDefinition="NUMBER(1)")
    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
