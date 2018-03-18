package cn.hd.common.enumeration;

import java.util.ArrayList;
import java.util.List;

public enum ModuleEnum implements EnumDescription {
	menuModule("菜单模块",-1),
	recvModule("收件模块",1),
	sendModule("发件模块",2),
	orgModule("机构管理模块",3),
	dfpmkModule("待分配任务模块",4),
	xzjdModule("新增接待",5),
	dzdfnModule("待制定方案",6),
	xzhyModule("新增会议",7),
	bajModule("未办结",8),
	cjhywbModule("未报",9),
	bsmdModule("报送名单",10),
	dfpModule("待分配方案",11),
	ptldModule("陪同领导",12),
	dcsModule("待初审",13),
	dfsModule("待复审",14),
	ywcModule("已完成",15),
	chryModule("参会人员",16),
	gnfpModule("功能分配",17),
	rwfhModule("任务返回",18);


	private ModuleEnum(String description, Integer val) {
		this.description = description;
		this.val = val;
	}

	private String description;
	private Integer val;
	private static List<ModuleEnum> list;
	static  {
		list = new ArrayList<ModuleEnum>(6);
		list.add(menuModule);
		list.add(recvModule);
		list.add(sendModule);
		list.add(orgModule);
		list.add(dfpmkModule);
		list.add(xzjdModule);
	}
	public static List<ModuleEnum> getAll() {
		return  list;
	}
	public static ModuleEnum findById(Integer id) {
		for (ModuleEnum moduleEnum : getAll()) {
			if (id  ==  moduleEnum.getVal()) {
				return  moduleEnum;
			}
		}
		return   null ;
	}
	public String getDescription() {
		return description;
	}

	public Integer getVal() {
		return val;
	}

}
