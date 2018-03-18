package cn.hd.entity;

import cn.hd.common.enumeration.BanjieEnum;
import cn.hd.common.enumeration.EnumDescriptionUserType;
import cn.hd.common.enumeration.ShangbaoEnum;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 *
 * @ClassName: THyHyInstEntity
 * @Description: (会议实例)
 * @author liuqiang
 */
@Entity
@Table(name = "T_HY_HY_INST",schema="YAHY")
@TypeDefs({
        @TypeDef(
                name = "shangbaoEnum", typeClass = EnumDescriptionUserType.class,
                parameters = {@org.hibernate.annotations.Parameter(name = "class", value = "cn.hd.common.enumeration.ShangbaoEnum")}
        ),
        @TypeDef(
                name = "banjieEnum", typeClass = EnumDescriptionUserType.class,
                parameters = {@org.hibernate.annotations.Parameter(name = "class", value = "cn.hd.common.enumeration.BanjieEnum")}
        )
})
public class THyHyInstEntity implements Serializable {
    private String id;//uuid
    private Integer flagStatus=1;//标志：如果1代表发起方；2代表接收方。即参加会议对象
    private ShangbaoEnum sbZt;//办结完成后，此文件是否上报的状态：0未报、1已报
    private BanjieEnum bjBz;//办结标志0：未办结；            1：已办结
    private THyHyEntity hyHyEntity;//会议id

    private Integer sbZtInt=0;//未报和已报
    private Integer bjBzInt=0;//未办结1：已办结
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
    /* 多对一 会议 */
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "INST_ID", referencedColumnName = "ID")
    @Fetch(FetchMode.SELECT)
    public THyHyEntity getHyHyEntity() {
        return hyHyEntity;
    }
    public void setHyHyEntity(THyHyEntity hyHyEntity) {
        this.hyHyEntity = hyHyEntity;
    }

    public Integer getFlagStatus() {
        return flagStatus;
    }

    public void setFlagStatus(Integer flagStatus) {
        this.flagStatus = flagStatus;
    }
    @Basic
    @Column(name = "SB_ZT",columnDefinition = "NUMBER(10)")
    @Type( type = "shangbaoEnum")
    public ShangbaoEnum getSbZt() {
        return sbZt;
    }

    public void setSbZt(ShangbaoEnum sbZt) {
        this.sbZt = sbZt;
    }

    @Basic
    @Column(name = "BJ_BZ",columnDefinition = "NUMBER(10)")
    @Type( type = "banjieEnum")
    public BanjieEnum getBjBz() {
        return bjBz;
    }

    public void setBjBz(BanjieEnum bjBz) {
        this.bjBz = bjBz;
    }
    @Transient
    public Integer getSbZtInt() {
        return sbZtInt;
    }

    public void setSbZtInt(Integer sbZtInt) {
        this.sbZtInt = sbZtInt;
    }
    @Transient
    public Integer getBjBzInt() {
        return bjBzInt;
    }

    public void setBjBzInt(Integer bjBzInt) {
        this.bjBzInt = bjBzInt;
    }
}
