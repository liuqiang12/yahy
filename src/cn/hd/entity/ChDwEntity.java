package cn.hd.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: TXtDwEntity
 * @Description: (系统单位(组织机构))  : 承办单位
 * @author liuqiang
 */
@Entity
@Table(name = "T_CH_DW",schema="YAHY")
@XmlRootElement(name = "ChDwEntity")
@XmlAccessorType(XmlAccessType.FIELD)
public class ChDwEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;//uuid
    private String hyId;// 实际参会单位
    private String chdwName;// 参会单位名称
    private String dwId;//单位ID
    private Date qsSj;//签时间
    private String qssj;//签收时间
    private String chdw;//参会单位
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
    @Column(name = "HY_ID",columnDefinition="varchar(32)")
    public String getHyId() {
        return hyId;
    }

    public void setHyId(String hyId) {
        this.hyId = hyId;
    }
    @Basic
    @Column(name = "CHDW_NAME",columnDefinition="varchar(150)")
    public String getChdwName() {
        return chdwName;
    }

    public void setChdwName(String chdwName) {
        this.chdwName = chdwName;
    }
    @Basic
    @Column(name = "DW_ID",columnDefinition="varchar(32)")
    public String getDwId() {
        return dwId;
    }

    public void setDwId(String dwId) {
        this.dwId = dwId;
    }
    @Basic
    @Column(name = "QS_SJ",columnDefinition="datetime")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    public Date getQsSj() {
        return qsSj;
    }

    public void setQsSj(Date qsSj) {
        this.qsSj = qsSj;
    }
    @Transient
    public String getQssj() {
        return qssj;
    }

    public void setQssj(String qssj) {
        this.qssj = qssj;
    }
    @Transient
    public String getChdw() {
        return chdw;
    }

    public void setChdw(String chdw) {
        this.chdw = chdw;
    }
}
