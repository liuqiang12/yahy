package cn.hd.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @ClassName: TOperateEntity
 * @Description: (动作权限表)
 * @author liuqiang
 */
@Entity
@Table(name = "T_XT_OPERATE",schema="YAHY")
@XmlRootElement(name = "TOperateEntity")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class TOperateEntity  implements Serializable {
    private String id;//UUID
    private String mc;//设置名称
    private Integer isHold;//是否拥有1表示是0表示否
    private Integer flag;//1项目初始化数据  0从界面新增的数据
    private String bz;//备注
    private String userIds;//绑定的人员ids
    private String userNames;//绑定的人员names

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
    @Column(name = "IS_HOLD",columnDefinition="number(10)",nullable = false)
    public Integer getIsHold() {
        return isHold;
    }

    public void setIsHold(Integer isHold) {
        this.isHold = isHold;
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
    @Basic
    @Column(name = "USER_IDS",columnDefinition="varchar(6000)")
    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }
    @Basic
    @Column(name = "USER_NAMES",columnDefinition="varchar(6000)")
    public String getUserNames() {
        return userNames;
    }

    public void setUserNames(String userNames) {
        this.userNames = userNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TOperateEntity that = (TOperateEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (isHold != null ? !isHold.equals(that.isHold) : that.isHold != null) return false;
        if (mc != null ? !mc.equals(that.mc) : that.mc != null) return false;
        if (bz != null ? !bz.equals(that.bz) : that.bz != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (mc != null ? mc.hashCode() : 0);
        result = 31 * result + (isHold != null ? isHold.hashCode() : 0);
        result = 31 * result + (bz != null ? bz.hashCode() : 0);
        return result;
    }
}
