package cn.hd.common.enumeration;

import java.util.ArrayList;
import java.util.List;

public enum UploadMaterialEnum implements EnumDescription {
	unwanted(0,"不需报材料"),
	wanting(1,"需要上报材料"),
	uploaded(2,"没激活");
	private final Integer val;   //枚举值
	private final String description;//枚举描述

	private static List<UploadMaterialEnum> list;
	static  {
		list = new ArrayList<UploadMaterialEnum>(3);
		list.add(unwanted);
		list.add(wanting);
		list.add(uploaded);
	}
	public static List<UploadMaterialEnum> getAll() {
		return  list;
	}
	public static UploadMaterialEnum findById(Integer id) {
		for (UploadMaterialEnum uploadMaterialEnum : getAll()) {
			if (id  ==  uploadMaterialEnum.getVal()) {
				return  uploadMaterialEnum;
			}
		}
		return   null ;
	}
	private UploadMaterialEnum(Integer val, String description) {
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
