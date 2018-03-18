package cn.hd.common.enumeration;

import java.util.ArrayList;
import java.util.List;

/*
 住宿标志
0：不住宿；
1：住宿
 */
public enum HotelEnum implements EnumDescription {
    notwant(0,"不住宿"),
    want(1,"住宿");

    private final Integer val;   //枚举值
    private final String description;//枚举描述
    private static List<HotelEnum> list;
    static  {
        list = new ArrayList<HotelEnum>(2);
        list.add(notwant);
        list.add(want);
    }
    public static List<HotelEnum> getAll() {
        return  list;
    }
    public static HotelEnum findById(Integer id) {
        for (HotelEnum hotelEnum : getAll()) {
            if (id  ==  hotelEnum.getVal()) {
                return  hotelEnum;
            }
        }
        return   null ;
    }


    private HotelEnum(Integer val, String description) {
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
