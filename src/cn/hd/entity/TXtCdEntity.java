package cn.hd.entity;

import cn.hd.common.enumeration.ActiveEnum;
import cn.hd.common.enumeration.EnumDescriptionUserType;
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

/**
 * @ClassName: TXtCdEntity
 * @Description: (菜单列表)
 * @author liuqiang
 */
@Entity
@Table(name = "T_XT_CD",schema="YAHY")
@TypeDefs({
        @TypeDef(
                name = "activeZt", typeClass = EnumDescriptionUserType.class,
                parameters = {@org.hibernate.annotations.Parameter(name = "class", value = "cn.hd.common.enumeration.ActiveEnum")}
        )
})
@XmlRootElement(name = "TXtCdEntity")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class TXtCdEntity implements Serializable {
    private String id;//uuid
    private String mc;//名称
    private String url;//连接地址
    private String fid;//父菜单ID
    private String ys;//菜单样式
    private int px;//菜单排序
    private ActiveEnum jhZt;//激活状态:1激活、0未激活
    private Date cjSj;//创建时间

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
    @Column(name = "MC",columnDefinition="varchar(150)")
    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    @Basic
    @Column(name = "URL",columnDefinition="varchar(200)")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "FID",columnDefinition="varchar(32)")
    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    @Basic
    @Column(name = "YS",columnDefinition="varchar(150)")
    public String getYs() {
        return ys;
    }

    public void setYs(String ys) {
        this.ys = ys;
    }

    @Basic
    @Column(name = "PX",columnDefinition="NUMBER(3)",nullable = false)
    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    @Basic
    @Column(name = "JH_ZT",columnDefinition="NUMBER(1)")
    @Type( type = "activeZt")
    public ActiveEnum getJhZt() {
        return jhZt;
    }

    public void setJhZt(ActiveEnum jhZt) {
        this.jhZt = jhZt;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TXtCdEntity that = (TXtCdEntity) o;

        if (px != that.px) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (mc != null ? !mc.equals(that.mc) : that.mc != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (fid != null ? !fid.equals(that.fid) : that.fid != null) return false;
        if (ys != null ? !ys.equals(that.ys) : that.ys != null) return false;
        if (jhZt != null ? !jhZt.equals(that.jhZt) : that.jhZt != null) return false;
        if (cjSj != null ? !cjSj.equals(that.cjSj) : that.cjSj != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (mc != null ? mc.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (fid != null ? fid.hashCode() : 0);
        result = 31 * result + (ys != null ? ys.hashCode() : 0);
        result = 31 * result + px;
        result = 31 * result + (jhZt != null ? jhZt.hashCode() : 0);
        result = 31 * result + (cjSj != null ? cjSj.hashCode() : 0);
        return result;
    }
}
