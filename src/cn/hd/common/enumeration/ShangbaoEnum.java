package cn.hd.common.enumeration;

import java.util.ArrayList;
import java.util.List;

/**
 办结完成后，此文件是否上报的状态：0未报、1已报
 */
public enum ShangbaoEnum implements EnumDescription {
	notShangbao(0,"0未报"),
	shangbao(1,"1已报");

	private final Integer val;   //枚举值
	private final String description;//枚举描述

	private static List<ShangbaoEnum> list;
	static  {
		list = new ArrayList<ShangbaoEnum>(2);
		list.add(notShangbao);
		list.add(shangbao);
	}
	public static List<ShangbaoEnum> getAll() {
		return  list;
	}
	public static ShangbaoEnum findById(Integer id) {
		for (ShangbaoEnum opTypeEnum : getAll()) {
			if (id  ==  opTypeEnum.getVal()) {
				return  opTypeEnum;
			}
		}
		return   null ;
	}

	private ShangbaoEnum(Integer val, String description) {
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
