package cn.hd.common.enumeration;

import java.util.ArrayList;
import java.util.List;

public enum ZtreeEnum implements EnumDescription {
    XTDW(1,"系统单位树形结构"),
    QT(2,"");

    private final Integer val;   //枚举值
    private final String description;//枚举描述
    private static List<ZtreeEnum> list;
    static  {
        list = new ArrayList<ZtreeEnum>(2);
        list.add(XTDW);
        list.add(QT);
    }
    public static List<ZtreeEnum> getAll() {
        return  list;
    }
    public static ZtreeEnum findById(Integer id) {
        for (ZtreeEnum ztreeEnum : getAll()) {
            if (id  ==  ztreeEnum.getVal()) {
                return  ztreeEnum;
            }
        }
        return   null ;
    }

    private ZtreeEnum(Integer val, String description) {
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
