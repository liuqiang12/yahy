package cn.hd.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @ClassName: TXtCbryEntity
 * @Description: (承办人员表[从系统用户中选定而来])
 * @author liuqiang
 */
@Entity
@Table(name = "T_XT_CBRY",schema="YAHY")
@XmlRootElement(name = "TXtCbryEntity")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class TXtCbryEntity implements Serializable {
    private String id;//uuid
    private String fkXtryId;//外键连接系统人员ID
    private String mc;//名称

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
    @Column(name = "FK_XTRY_ID",columnDefinition="varchar(32)")
    public String getFkXtryId() {
        return fkXtryId;
    }

    public void setFkXtryId(String fkXtryId) {
        this.fkXtryId = fkXtryId;
    }

    @Basic
    @Column(name = "MC",columnDefinition="varchar(100)")
    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TXtCbryEntity that = (TXtCbryEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (fkXtryId != null ? !fkXtryId.equals(that.fkXtryId) : that.fkXtryId != null) return false;
        if (mc != null ? !mc.equals(that.mc) : that.mc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fkXtryId != null ? fkXtryId.hashCode() : 0);
        result = 31 * result + (mc != null ? mc.hashCode() : 0);
        return result;
    }
}
