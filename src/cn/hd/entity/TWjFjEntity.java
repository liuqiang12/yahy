package cn.hd.entity;

import cn.hd.common.enumeration.EnumDescriptionUserType;
import cn.hd.common.enumeration.ModuleEnum;
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
 * @ClassName: TWjFjEntity
 * @Description: (附件表)
 * @author liuqiang
 */
@Entity
@Table(name = "T_WJ_FJ",schema="YAHY")
@TypeDefs({
        @TypeDef(
                name = "mkBz", typeClass = EnumDescriptionUserType.class,
                parameters = {@org.hibernate.annotations.Parameter(name = "class", value = "cn.hd.common.enumeration.ModuleEnum")}
        )
})
@XmlRootElement(name = "TWjFjEntity")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class TWjFjEntity  implements Serializable {
    private String id;//uuid
    private String mc;//附件名称
    private String nickMc;//昵称
    private String fid;//携带附件的对象的ID
    private ModuleEnum mkBz;//标志附件属于的模块，0,1,2,3…
    private int mj;//密级;使用数字表示,便于比较高低
    private String bz;//备注
    private Date cjSj;//创建时间:创建时间。直接
    private String cjrId;//创建人ID（t_xt _yh.id）
    private TWjWjEntity wjWjEntity;//其实是一对一关系

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
    @Column(name = "MC",columnDefinition="varchar(200)",nullable = false)
    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
        if(mc != null && !"".equals(mc) ){
            if(mc.length() > 27){
                nickMc = mc.substring(0,25)+"...";
            }else{
                nickMc = mc;
            }
        }
    }

    @Basic
    @Column(name = "FID",columnDefinition="varchar(32)",nullable = true)
    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    @Basic
    @Column(name = "MK_BZ",columnDefinition="NUMBER(10)",nullable = false)
    @Type( type = "mkBz")
    public ModuleEnum getMkBz() {
        return mkBz;
    }

    public void setMkBz(ModuleEnum mkBz) {
        this.mkBz = mkBz;
    }

    @Basic
    @Column(name = "MJ",columnDefinition="NUMBER(10)",nullable = false)
    public int getMj() {
        return mj;
    }

    public void setMj(int mj) {
        this.mj = mj;
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
    @Column(name = "CJ_SJ",columnDefinition="datetime",nullable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:MM:ss")
    public Date getCjSj() {
        return cjSj;
    }

    public void setCjSj(Date cjSj) {
        this.cjSj = cjSj;
    }

    @Basic
    @Column(name = "CJR_ID",columnDefinition="varchar(32)",nullable = true)
    public String getCjrId() {
        return cjrId;
    }

    public void setCjrId(String cjrId) {
        this.cjrId = cjrId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TWjFjEntity that = (TWjFjEntity) o;
        if (mj != that.mj) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (mkBz != null ? !mkBz.equals(that.mkBz) : that.mkBz != null) return false;
        if (mc != null ? !mc.equals(that.mc) : that.mc != null) return false;
        if (fid != null ? !fid.equals(that.fid) : that.fid != null) return false;
        if (bz != null ? !bz.equals(that.bz) : that.bz != null) return false;
        if (cjSj != null ? !cjSj.equals(that.cjSj) : that.cjSj != null) return false;
        if (cjrId != null ? !cjrId.equals(that.cjrId) : that.cjrId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (mc != null ? mc.hashCode() : 0);
        result = 31 * result + (fid != null ? fid.hashCode() : 0);
        result = 31 * result + (mkBz != null ? mkBz.hashCode() : 0);
        result = 31 * result + mj;
        result = 31 * result + (bz != null ? bz.hashCode() : 0);
        result = 31 * result + (cjSj != null ? cjSj.hashCode() : 0);
        result = 31 * result + (cjrId != null ? cjrId.hashCode() : 0);
        return result;
    }
    /**
     * @OneToOne注释指明为一对一关系，
     * TWjFjEntity是关系被维护端，optional = false设置person属性值不能为null
     * @JoinColumn(name = "WJ_ID", referencedColumnName ="ID",unique = true)
     * 指明TWjFjEntity对应表的WJ_ID列作为外键与
     * TWjWjEntity对应表的ID列进行关联,unique= true 指明WJ_ID 列的值不可重复。
     * @return
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wj_id")
    public TWjWjEntity getWjWjEntity() {
        return wjWjEntity;
    }

    public void setWjWjEntity(TWjWjEntity wjWjEntity) {
        this.wjWjEntity = wjWjEntity;
    }
    @Transient
    public String getNickMc() {
        return nickMc;
    }

    public void setNickMc(String nickMc) {
        this.nickMc = nickMc;
    }

}
