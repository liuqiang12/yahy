package cn.hd.common.enumeration;

import java.util.ArrayList;
import java.util.List;

public enum ActiveEnum implements EnumDescription {
    active(1,"激活"),
    disabled(0,"没激活");

    private final Integer val;   //枚举值
    private final String description;//枚举描述
    private static List<ActiveEnum> list;
    static  {
        list = new ArrayList<ActiveEnum>(2);
        list.add(active);
        list.add(disabled);
    }
    public static List<ActiveEnum> getAll() {
        return  list;
    }
    public static ActiveEnum findById(Integer id) {
        for (ActiveEnum activeEnum : getAll()) {
            if (id  ==  activeEnum.getVal()) {
                return  activeEnum;
            }
        }
        return   null ;
    }

    private ActiveEnum(Integer val, String description) {
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
