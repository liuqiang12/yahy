package cn.hd.common.enumeration;

import java.util.ArrayList;
import java.util.List;

/**
 办结标志
 0：未办结；
 1：已办结（需要报送参会人员名单）
 */
public enum BanjieEnum implements EnumDescription {
	notBanjie(0,"未办结"),
	banjied(1,"已办结");

	private final Integer val;   //枚举值
	private final String description;//枚举描述
	private static List<BanjieEnum> list;
	static  {
		list = new ArrayList<BanjieEnum>(2);
		list.add(notBanjie);
		list.add(banjied);
	}
	public static List<BanjieEnum> getAll() {
		return  list;
	}
	public static BanjieEnum findById(Integer id) {
		for (BanjieEnum banjieEnum : getAll()) {
			if (id  ==  banjieEnum.getVal()) {
				return  banjieEnum;
			}
		}
		return   null ;
	}


	private BanjieEnum(Integer val, String description) {
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
