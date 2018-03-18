import cn.hd.common.enumeration.EnumDescriptionUserType;
import cn.hd.common.enumeration.ModuleEnum;
import cn.hd.common.enumeration.OpTypeEnum;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @ClassName: TXtRzEntity
 * @Description: (系统日志表)
 * @author liuqiang
 */
@Entity
@Table(name = "T_XT_RZ",schema="YAHY")
@XmlRootElement(name = "TXtRzEntity")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
@TypeDefs({
        @TypeDef(
                name = "mkEnum", typeClass = EnumDescriptionUserType.class,
                parameters = {@org.hibernate.annotations.Parameter(name = "class", value = "cn.hd.common.enumeration.ModuleEnum")}
        ),
        @TypeDef(
                name = "lxEnum", typeClass = EnumDescriptionUserType.class,
                parameters = {@org.hibernate.annotations.Parameter(name = "class", value = "cn.hd.common.enumeration.OpTypeEnum")}
        )
})
public class TXtRzEntity {
    private String id;//uuid
    private String bz;//日志备注
    private Date cjSj;//创建时间
    private String cjrFid;//创建人单位ID
    private String cjrId;//创建人ID（t_xt _yh.id）
    private OpTypeEnum lxBz;//0:添加；1：修改；2：删除；3：查询；4：下载
    private ModuleEnum mkBz;//模块标志
    private String nr;//日志内容，详细说明此日志相关信息
    private String sjId;//数据ID，与mk_bz组合使用

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
    @Column(name = "BZ",columnDefinition="varchar(1000)")
    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    @Basic
    @Column(name = "CJ_SJ",columnDefinition="datetime",nullable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:MM:ss")
    public Date getCjSj() {
        return cjSj;
    }

    public void setCjSj(Date cjSj) {
        this.cjSj = cjSj;
    }

    @Basic
    @Column(name = "CJR_FID",columnDefinition="varchar(32)")
    public String getCjrFid() {
        return cjrFid;
    }

    public void setCjrFid(String cjrFid) {
        this.cjrFid = cjrFid;
    }

    @Basic
    @Column(name = "CJR_ID",columnDefinition="varchar(32)")
    public String getCjrId() {
        return cjrId;
    }

    public void setCjrId(String cjrId) {
        this.cjrId = cjrId;
    }

    @Basic
    @Column(name="LX_BZ",columnDefinition="number(10)")
    @Type(type = "mkEnum")
    public OpTypeEnum getLxBz() {
        return lxBz;
    }

    public void setLxBz(OpTypeEnum lxBz) {
        this.lxBz = lxBz;
    }

    @Basic
    @Column(name = "MK_BZ",columnDefinition="number(10)")
    @Type(type = "mkEnum")
    public ModuleEnum getMkBz() {
        return mkBz;
    }

    public void setMkBz(ModuleEnum mkBz) {
        this.mkBz = mkBz;
    }

    @Basic
    @Column(name = "NR",columnDefinition="varchar(1000)",nullable = false)
    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    @Basic
    @Column(name = "SJ_ID",columnDefinition="varchar(32)")
    public String getSjId() {
        return sjId;
    }

    public void setSjId(String sjId) {
        this.sjId = sjId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TXtRzEntity that = (TXtRzEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (bz != null ? !bz.equals(that.bz) : that.bz != null) return false;
        if (cjSj != null ? !cjSj.equals(that.cjSj) : that.cjSj != null) return false;
        if (cjrFid != null ? !cjrFid.equals(that.cjrFid) : that.cjrFid != null) return false;
        if (cjrId != null ? !cjrId.equals(that.cjrId) : that.cjrId != null) return false;
        if (lxBz != null ? !lxBz.equals(that.lxBz) : that.lxBz != null) return false;
        if (mkBz != null ? !mkBz.equals(that.mkBz) : that.mkBz != null) return false;
        if (nr != null ? !nr.equals(that.nr) : that.nr != null) return false;
        if (sjId != null ? !sjId.equals(that.sjId) : that.sjId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bz != null ? bz.hashCode() : 0);
        result = 31 * result + (cjSj != null ? cjSj.hashCode() : 0);
        result = 31 * result + (cjrFid != null ? cjrFid.hashCode() : 0);
        result = 31 * result + (cjrId != null ? cjrId.hashCode() : 0);
        result = 31 * result + (lxBz != null ? lxBz.hashCode() : 0);
        result = 31 * result + (mkBz != null ? mkBz.hashCode() : 0);
        result = 31 * result + (nr != null ? nr.hashCode() : 0);
        result = 31 * result + (sjId != null ? sjId.hashCode() : 0);
        return result;
    }
}
