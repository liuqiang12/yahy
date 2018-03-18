package cn.hd.common.enumeration;

import java.util.ArrayList;
import java.util.List;

public enum MenuModuleEnum implements EnumDescription {
	XZJD("新增接待",1,"XZJD"),
	DFP("待分配",2,"DFP"),
	DZDFA("待制定方案",3,"DZDFA"),
	DCS("待初审",4,"DCS"),
	DFS("待初审",5,"DFS"),
	YWD("已完成",6,"YWD"),
	LCSM("流程说明",7,"LCSM"),
	JDSSXZ("接待实施细则",8,"JDSSXZ"),
	JDFAMB("接待方案模板",9,"JDFAMB"),
	CGDJS("参观点介绍",10,"CGDJS"),
	JDDSJ("接待大事记",11,"JDDSJ"),
	XTRZ("系统日志",12,"XTRZ"),
	GNPZ("功能配置",13,"GNPZ"),
	QXPZ("权限配置",14,"QXPZ"),
	XZHY("新增会议管理",15,"XZHYGL"),
	WB_HY("会议未报",16,"wb_list"),
	YB_HY("会议已报",17,"yb_list"),
	WBJ_HY("未办结",18,"wbj_list"),
	YBJ_HY("已办结",19,"ybj_list"),
	COUNT_HY("已办结",20,"meet_count");


	private MenuModuleEnum(String description, Integer val,String en) {
		this.description = description;
		this.val = val;
	}

	private String description;
	private String en;
	private Integer val;
	private static List<MenuModuleEnum> list;
	static  {
		list = new ArrayList<MenuModuleEnum>(14);
		list.add(XZJD);
		list.add(DFP);
		list.add(DZDFA);
		list.add(DCS);
		list.add(DFS);
		list.add(YWD);
		list.add(LCSM);
		list.add(JDSSXZ);
		list.add(JDFAMB);
		list.add(CGDJS);
		list.add(JDDSJ);
		list.add(XTRZ);
		list.add(GNPZ);
		list.add(QXPZ);
	}
	public static List<MenuModuleEnum> getAll() {
		return  list;
	}
	public static MenuModuleEnum findById(Integer id) {
		for (MenuModuleEnum moduleEnum : getAll()) {
			if (id  ==  moduleEnum.getVal()) {
				return  moduleEnum;
			}
		}
		return   null ;
	}
	public String getDescription() {
		return description;
	}
	public String getEn() {
		return en;
	}

	public Integer getVal() {
		return val;
	}
	public static String getDescriptionFromModule(Integer module) {
		for (MenuModuleEnum e : MenuModuleEnum.values()) {
			if(e.getVal() == module){
				return e.getDescription();
			}
		}
		return null;
	}

	public static void main(String[] args) {
		String s = getDescriptionFromModule(1);
		System.out.println(s);
	}
}
