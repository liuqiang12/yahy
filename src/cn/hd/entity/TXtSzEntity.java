package cn.hd.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @ClassName: THyChdwEntity
 * @Description: (系统设置)
 * @author liuqiang
 */
@Entity
@Table(name = "T_XT_SZ",schema="YAHY")
@XmlRootElement(name = "TXtSzEntity")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})

public class TXtSzEntity implements Serializable {
    private String id;//UUID
    private String mc;//设置名称
    private Integer qz;//设置取值:1是0否
    private String bz;//备注
    private Integer flag;//标志

    @Id
    @Column(name="ID",columnDefinition="varchar(32)")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    public String getId() {
        return id;
    }//
    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "MC",columnDefinition="varchar(100)",nullable = false)
    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    @Basic
    @Column(name = "QZ",columnDefinition="number(10)",nullable = false)
    public Integer getQz() {
        return qz;
    }

    public void setQz(Integer qz) {
        this.qz = qz;
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
    @Column(name = "FLAG",columnDefinition="number(10)",nullable = false)
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

        TXtSzEntity that = (TXtSzEntity) o;
        if (qz != null ? !qz.equals(that.qz) : that.qz != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (mc != null ? !mc.equals(that.mc) : that.mc != null) return false;
        if (bz != null ? !bz.equals(that.bz) : that.bz != null) return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (qz != null ? qz.hashCode() : 0);
        result = 31 * result + (mc != null ? mc.hashCode() : 0);
        result = 31 * result + (bz != null ? bz.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        return result;
    }
}
