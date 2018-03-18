package cn.hd.common.enumeration;


import java.util.ArrayList;
import java.util.List;

/**
 * 人员类别
 0：参会人员；
 1：工作人员；
 2：驾驶人员；
 3：会议请假

 */
public enum GenderCategoryEnum implements EnumDescription {
	Participant(0,"参会人员"),
	Personnel(1,"工作人员"),
	Driver(2,"驾驶人员"),
	Leaved(3,"会议请假");

	private final Integer val;   //枚举值
	private final String description;//枚举描述
	private static List<GenderCategoryEnum> list;
	static  {
		list = new ArrayList<GenderCategoryEnum>(4);
		list.add(Participant);
		list.add(Personnel);
		list.add(Driver);
		list.add(Leaved);
	}
	public static List<GenderCategoryEnum> getAll() {
		return  list;
	}
	public static GenderCategoryEnum findById(Integer id) {
		for (GenderCategoryEnum genderCategoryEnum : getAll()) {
			if (id  ==  genderCategoryEnum.getVal()) {
				return  genderCategoryEnum;
			}
		}
		return   null ;
	}


	private GenderCategoryEnum(Integer val, String description) {
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
