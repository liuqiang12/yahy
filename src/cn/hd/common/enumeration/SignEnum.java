package cn.hd.common.enumeration;

import java.util.ArrayList;
import java.util.List;

/**
 * 签收标志；
 0：未签收；
 1：已签收
 */
public enum SignEnum implements EnumDescription {
	notSign(0,"未签收"),
	signed(1,"已签收");

	private final Integer val;   //枚举值
	private final String description;//枚举描述
	private static List<SignEnum> list;
	static  {
		list = new ArrayList<SignEnum>(2);
		list.add(notSign);
		list.add(signed);
	}
	public static List<SignEnum> getAll() {
		return  list;
	}
	public static SignEnum findById(Integer id) {
		for (SignEnum signEnum : getAll()) {
			if (id  ==  signEnum.getVal()) {
				return  signEnum;
			}
		}
		return   null ;
	}

	private SignEnum(Integer val, String description) {
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
