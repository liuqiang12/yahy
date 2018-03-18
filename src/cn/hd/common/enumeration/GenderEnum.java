package cn.hd.common.enumeration;


import java.util.ArrayList;
import java.util.List;

public enum GenderEnum implements EnumDescription {
	male(1,"男"),
	female(2,"女");

	private final Integer val;   //枚举值
	private final String description;//枚举描述
	private static List<GenderEnum> list;
	static  {
		list = new ArrayList<GenderEnum>(2);
		list.add(male);
		list.add(female);
	}
	public static List<GenderEnum> getAll() {
		return  list;
	}
	public static GenderEnum findById(Integer id) {
		for (GenderEnum genderEnum : getAll()) {
			if (id  ==  genderEnum.getVal()) {
				return  genderEnum;
			}
		}
		return   null ;
	}


	private GenderEnum(Integer val, String description) {
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
