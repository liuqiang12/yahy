package cn.hd.entity;

import cn.hd.common.enumeration.ActiveEnum;
import cn.hd.common.enumeration.EnumDescriptionUserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: TXtJsEntity
 * @Description: (系统角色)
 * @author liuqiang
 */
@Entity
@Table(name = "T_XT_JS",schema="YAHY")
@TypeDefs({
        @TypeDef(
                name = "activeZt", typeClass = EnumDescriptionUserType.class,
                parameters = {@org.hibernate.annotations.Parameter(name = "class", value = "cn.hd.common.enumeration.ActiveEnum")}
        )
})
@XmlRootElement(name = "TXtJsEntity")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","ryEntities"})
public class TXtJsEntity implements Serializable, Cloneable{
    private String id;//uuid
    private String mc;//角色名称
    private String jsBm;// 角色的标志:唯一
    private String fid;//父角色ID
    private ActiveEnum activeZt;//是否激活1激活0没激活
    private Integer flag;//角色标志1 系统初始化保存数据 0界面录入数据
    private transient Set<TXtRyEntity> ryEntities = new HashSet<TXtRyEntity>();//角色和人员属于多对多关系:关系

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
    @Column(name = "MC",columnDefinition = "VARCHAR(50)",nullable = false)
    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    @Basic
    @Column(name = "JS_BM",columnDefinition = "VARCHAR(150)",nullable = false)
    public String getJsBm() {
        return jsBm;
    }

    public void setJsBm(String jsBm) {
        this.jsBm = jsBm;
    }

    @Basic
    @Column(name = "FID",columnDefinition = "VARCHAR(32)")
    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    @Basic
    @Column(name = "ZT",columnDefinition="number(1)")
    @Type(type = "activeZt")
    public ActiveEnum getActiveZt() {
        return activeZt;
    }

    public void setActiveZt(ActiveEnum activeZt) {
        this.activeZt = activeZt;
    }
    @Basic
    @Column(name = "FLAG",columnDefinition = "NUMBER(10)")
    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
    public Object clone()
    {
        Object o=null;
        try
        {
            o=(TXtJsEntity)super.clone();//Object 中的clone()识别出你要复制的是哪一个对象。
        }
        catch(CloneNotSupportedException e)
        {
            System.out.println(e.toString());
        }
        return o;
    }

    /**
     * 因为多对多之间会通过一张中间表来维护两表直接的关系，
     * 所以通过 JoinTable这个注解来声明
     * name就是指定了中间表的名字，
     * JoinColumns是一个 @JoinColumn类型的数组
     * 表示的是我这方在对方中的外键名称
     * inverseJoinColumns也是一个@JoinColumn类型的数组
     * 表示的是对方在我这放中的外键名称，所以在我方外键的名称就是 id
     * @return
     */
    /**
     * joinColumns  定义中间表与Teacher 表的外键关系，中间表Teacher_Student的Teacher_ID 列是Teacher 表的主键列对应的外键列。
     * inverseJoinColumns 属性定义了中间表与另外一端(Student)的外键关系。
     * @return
     */
    /*@Basic*/
  /*  @ManyToMany(cascade = CascadeType.REFRESH,mappedBy = "jsEntities")// PERSIST级联新增（又称级联保存）  CascadeType.REFRESH:级联刷新
    *//*@JoinTable(name = "T_XT_JS_RY",
            joinColumns = {@JoinColumn(name = "JS_ID",referencedColumnName="ID")},//中间表的JS_ID 对应角色表的ID
            inverseJoinColumns = {@JoinColumn(name = "RY_ID",referencedColumnName="ID")//中间表的RY_ID，对应人员表的ID
        }
    )*/
   // @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)// PERSIST级联新增（又称级联保存）  CascadeType.REFRESH:级联刷新
    /*@JoinTable(name = "T_XT_JS_RY",
            joinColumns = {@JoinColumn(name = "JS_ID")},//中间表的JS_ID 对应用户表的ID
            inverseJoinColumns = {@JoinColumn(name = "RY_ID")//中间表的JS_ID，对应角色表的ID
            }
    )
    @JsonIgnore*/

    @JsonIgnore
    @ManyToMany(mappedBy = "jsEntities")
    public Set<TXtRyEntity> getRyEntities() {
        return ryEntities;
    }

    public void setRyEntities(Set<TXtRyEntity> ryEntities) {
        this.ryEntities = ryEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TXtJsEntity jsEntity = (TXtJsEntity) o;

        if (id != null ? !id.equals(jsEntity.id) : jsEntity.id != null) return false;
        if (mc != null ? !mc.equals(jsEntity.mc) : jsEntity.mc != null) return false;
        if (jsBm != null ? !jsBm.equals(jsEntity.jsBm) : jsEntity.jsBm != null) return false;
        if (fid != null ? !fid.equals(jsEntity.fid) : jsEntity.fid != null) return false;
        if (activeZt != jsEntity.activeZt) return false;
        return flag != null ? flag.equals(jsEntity.flag) : jsEntity.flag == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (mc != null ? mc.hashCode() : 0);
        result = 31 * result + (jsBm != null ? jsBm.hashCode() : 0);
        result = 31 * result + (fid != null ? fid.hashCode() : 0);
        result = 31 * result + (activeZt != null ? activeZt.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TXtJsEntity{" +
                "id='" + id + '\'' +
                ", mc='" + mc + '\'' +
                ", jsBm='" + jsBm + '\'' +
                ", fid='" + fid + '\'' +
                ", flag=" + flag +
                '}';
    }
}
