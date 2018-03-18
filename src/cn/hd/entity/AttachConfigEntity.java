package cn.hd.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @ClassName: THyChryEntity
 * @Description: (附件配置)
 * @author liuqiang
 *
 */
@Entity
@Table(name = "T_ATTACH_CONFIG",schema="YAHY")
@XmlRootElement(name = "AttachConfigEntity")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class AttachConfigEntity  implements Serializable {
    private String id;//uuid
    /* 附件的配置文件表 */
    private String filePath = "d://22";//文件配置路径

    /**
     * 主键ID
     * @return
     */
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

    /**
     * 文件路径
     * @return
     */
    @Basic
    @Column(name = "FILE_PATH",columnDefinition="varchar(150)",nullable = false)
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
