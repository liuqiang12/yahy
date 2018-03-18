package cn.hd.entity;

import cn.hd.common.enumeration.EnumDescriptionUserType;
import cn.hd.common.enumeration.ModuleEnum;
import cn.hd.common.enumeration.OpTypeEnum;
import cn.hd.entity.core.BaseModel;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 日志表实体
 * @author: machao
 * @date  : 2017-11-2 上午10:53:18
 */
@Entity
@Table(name="T_XT_RZ",schema="YAHY")
@TypeDefs({@TypeDef(name = "mkEnum", typeClass = EnumDescriptionUserType.class,
		parameters = {@Parameter(name = "class", value = "cn.hd.common.enumeration.ModuleEnum")}),
		@TypeDef(name = "lxEnum", typeClass = EnumDescriptionUserType.class,
				parameters = {@Parameter(name = "class", value = "cn.hd.common.enumeration.OpTypeEnum")})})
public class T_xt_rz extends BaseModel implements Serializable{

	private static final long serialVersionUID = -2308310175228519895L;


	@Column(name="NR",columnDefinition="varchar(2000)",nullable=false)
	private String nr;//日志内容，详细说明此日志相关信息
	@Column(name="mk_bz",columnDefinition="number(10)")
	@Type(type = "mkEnum")
	private ModuleEnum mk_bz;//模块标志
	@Column(name="sj_id",columnDefinition="varchar(32)")
	private String sj_id;//数据ID，与mk_bz组合使用
	@Column(name="lx_bz",columnDefinition="number(10)")
	@Type(type="lxEnum")
	private OpTypeEnum lx_bz;//1:添加；2：修改；3：删除；4：查询；5：下载 6:登录
	@Column(name="bz",columnDefinition="varchar(2000)")
	private String bz;//日志备注
	@Column(name="cjr_fid",columnDefinition="varchar(32)")
	private String cjr_fid;//创建人单位ID
	@Column(name="cj_sj",columnDefinition="datetime",nullable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:MM:ss")
	private Date cj_sj ;//创建时间
	@Column(name="cjr_id",columnDefinition="varchar(32)")
	private String cjr_id;//创建人ID（t_xt _yh.id）


	public String getNr() {
		return nr;
	}
	public void setNr(String nr) {
		this.nr = nr;
	}
	public String getSj_id() {
		return sj_id;
	}
	public void setSj_id(String sjId) {
		sj_id = sjId;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getCjr_fid() {
		return cjr_fid;
	}
	public void setCjr_fid(String cjrFid) {
		cjr_fid = cjrFid;
	}
	public Date getCj_sj() {
		return cj_sj;
	}
	public void setCj_sj(Date cjSj) {
		cj_sj = cjSj;
	}
	public String getCjr_id() {
		return cjr_id;
	}
	public void setCjr_id(String cjrId) {
		cjr_id = cjrId;
	}
	public ModuleEnum getMk_bz() {
		return mk_bz;
	}
	public void setMk_bz(ModuleEnum mkBz) {
		mk_bz = mkBz;
	}
	public OpTypeEnum getLx_bz() {
		return lx_bz;
	}
	public void setLx_bz(OpTypeEnum lxBz) {
		lx_bz = lxBz;
	}
}
