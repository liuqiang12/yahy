package cn.hd.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @ClassName: TSjZdEntity
 * @Description: (系统数据字典)
 * @author liuqiang
 */
@Entity
@Table(name = "T_SJ_ZD",schema="YAHY")
@XmlRootElement(name = "TSjZdEntity")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class TSjZdEntity  implements Serializable {
    private String id;//uuid
    private String bm;//编码
    private String mc;//名称
    private String pbm;//父编码
    private String bz;//备注

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
    @Column(name = "BM",columnDefinition="varchar(10)")
    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm;
    }

    @Basic
    @Column(name = "MC",columnDefinition="varchar(100)")
    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    @Basic
    @Column(name = "PBM",columnDefinition="varchar(32)")
    public String getPbm() {
        return pbm;
    }

    public void setPbm(String pbm) {
        this.pbm = pbm;
    }

    @Basic
    @Column(name = "BZ",columnDefinition="varchar(200)")
    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TSjZdEntity that = (TSjZdEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (bm != null ? !bm.equals(that.bm) : that.bm != null) return false;
        if (mc != null ? !mc.equals(that.mc) : that.mc != null) return false;
        if (pbm != null ? !pbm.equals(that.pbm) : that.pbm != null) return false;
        if (bz != null ? !bz.equals(that.bz) : that.bz != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bm != null ? bm.hashCode() : 0);
        result = 31 * result + (mc != null ? mc.hashCode() : 0);
        result = 31 * result + (pbm != null ? pbm.hashCode() : 0);
        result = 31 * result + (bz != null ? bz.hashCode() : 0);
        return result;
    }
}
