package cn.hd.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: TWjWjEntity
 * @Description: (物理文件表)
 * 关系维护端
 * @author liuqiang
 */
@Entity
@Table(name = "T_WJ_WJ",schema="YAHY")
@XmlRootElement(name = "TWjWjEntity")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class TWjWjEntity  implements Serializable {
    private String id;//uuid
    private String uuidMc;//文件上传到服务器后生成的随机文件名，不保存后缀名
    private Long dx;//文件大小，字节
    private String md5;//文件MD5码
    private String bsclBt;//报送材料标题
    private String sha1;//文件sha1码
    private Date zxyysj;//最新引用时间
    private Date cjSj;//创建时间
    private String logicTablename;//逻辑表名:定位附件属于的表名
    private String ogicColumn;//逻辑列名称L
    private String relationalValue;//逻辑列值
    @Basic
    @Column(name = "BSCL_BT",columnDefinition="varchar(200)")
    public String getBsclBt() {
        return bsclBt;
    }

    public void setBsclBt(String bsclBt) {
        this.bsclBt = bsclBt;
    }



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
    @Column(name = "UUID_MC")
    public String getUuidMc() {
        return uuidMc;
    }

    public void setUuidMc(String uuidMc) {
        this.uuidMc = uuidMc;
    }

    @Basic
    @Column(name = "DX")
    public Long getDx() {
        return dx;
    }

    public void setDx(Long dx) {
        this.dx = dx;
    }

    @Basic
    @Column(name = "MD5")
    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    @Basic
    @Column(name = "SHA1")
    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    @Basic
    @Column(name = "ZXYYSJ",columnDefinition="datetime")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:MM:ss")
    public Date getZxyysj() {
        return zxyysj;
    }

    public void setZxyysj(Date zxyysj) {
        this.zxyysj = zxyysj;
    }

    @Basic
    @Column(name = "CJ_SJ",columnDefinition="datetime")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:MM:ss")
    public Date getCjSj() {
        return cjSj;
    }

    public void setCjSj(Date cjSj) {
        this.cjSj = cjSj;
    }

    @Basic
    @Column(name = "LOGIC_TABLENAME")
    public String getLogicTablename() {
        return logicTablename;
    }

    public void setLogicTablename(String logicTablename) {
        this.logicTablename = logicTablename;
    }

    @Basic
    @Column(name = "OGIC_COLUMN")
    public String getOgicColumn() {
        return ogicColumn;
    }

    public void setOgicColumn(String ogicColumn) {
        this.ogicColumn = ogicColumn;
    }

    @Basic
    @Column(name = "RELATIONAL_VALUE")
    public String getRelationalValue() {
        return relationalValue;
    }

    public void setRelationalValue(String relationalValue) {
        this.relationalValue = relationalValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TWjWjEntity that = (TWjWjEntity) o;

        if (dx != that.dx) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (uuidMc != null ? !uuidMc.equals(that.uuidMc) : that.uuidMc != null) return false;
        if (md5 != null ? !md5.equals(that.md5) : that.md5 != null) return false;
        if (sha1 != null ? !sha1.equals(that.sha1) : that.sha1 != null) return false;
        if (zxyysj != null ? !zxyysj.equals(that.zxyysj) : that.zxyysj != null) return false;
        if (cjSj != null ? !cjSj.equals(that.cjSj) : that.cjSj != null) return false;
        if (logicTablename != null ? !logicTablename.equals(that.logicTablename) : that.logicTablename != null)
            return false;
        if (ogicColumn != null ? !ogicColumn.equals(that.ogicColumn) : that.ogicColumn != null) return false;
        if (relationalValue != null ? !relationalValue.equals(that.relationalValue) : that.relationalValue != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (uuidMc != null ? uuidMc.hashCode() : 0);
        result = 31 * result + (dx != null ? dx.hashCode() : 0);
        result = 31 * result + (md5 != null ? md5.hashCode() : 0);
        result = 31 * result + (sha1 != null ? sha1.hashCode() : 0);
        result = 31 * result + (zxyysj != null ? zxyysj.hashCode() : 0);
        result = 31 * result + (cjSj != null ? cjSj.hashCode() : 0);
        result = 31 * result + (logicTablename != null ? logicTablename.hashCode() : 0);
        result = 31 * result + (ogicColumn != null ? ogicColumn.hashCode() : 0);
        result = 31 * result + (relationalValue != null ? relationalValue.hashCode() : 0);
        return result;
    }

}
