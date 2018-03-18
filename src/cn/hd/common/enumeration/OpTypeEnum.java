package cn.hd.common.enumeration;

import java.util.ArrayList;
import java.util.List;

public enum OpTypeEnum implements EnumDescription{
	linked(-1,"链接"),
	add(1,"添加"),
	update(2,"修改"),
	delete(3,"删除"),
	query(4,"查询"),
	download(5,"下载"),
	login(6,"登录");
	private Integer val;
	private String description;

	private static List<OpTypeEnum> list;
	static  {
		list = new ArrayList<OpTypeEnum>(7);
		list.add(linked);
		list.add(add);
		list.add(update);
		list.add(delete);
		list.add(query);
		list.add(download);
		list.add(login);
	}
	public static List<OpTypeEnum> getAll() {
		return  list;
	}
	public static OpTypeEnum findById(Integer id) {
		for (OpTypeEnum opTypeEnum : getAll()) {
			if (id  ==  opTypeEnum.getVal()) {
				return  opTypeEnum;
			}
		}
		return   null ;
	}
	private OpTypeEnum(Integer val, String description) {
		this.val = val;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public Integer getVal() {
		return val;
	}

}
