package cn.hd.common.cfgUtils;

import java.util.ResourceBundle;

/**
 * Created by DELL on 2017/9/1.
 */
public class CommonProperty {

    private static String propertyFileName = "cn.hd.config.common";
    private static CommonProperty configProperty;

    public String getPropertyValue(String key) {
        ResourceBundle resourcesTable = null;
        try {
            resourcesTable = ResourceBundle.getBundle(propertyFileName);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("配置文件缺失，请检查class目录下的" + propertyFileName + "配置文件" + e.getMessage());
        }
        try {
            String value = new String(resourcesTable.getString(key).getBytes("ISO-8859-1"), "UTF-8");

           // String value = resourcesTable.getString(key);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("配置文件配置项缺失，请检查class目录下的" + propertyFileName + "配置文件中的[" + key + "]  : " + e.getMessage());

        }
        return null;
    }
    public Integer getPropertyIntValue(String key) {
        ResourceBundle resourcesTable = null;
        try {
            resourcesTable = ResourceBundle.getBundle(propertyFileName);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("配置文件缺失，请检查class目录下的" + propertyFileName + "配置文件" + e.getMessage());
        }
        try {
            String value = resourcesTable.getString(key);
            if(value != null && !"".equals(value)){
                return Integer.valueOf(value);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("配置文件配置项缺失，请检查class目录下的" + propertyFileName + "配置文件中的[" + key + "]  : " + e.getMessage());

        }
        return null;
    }

    public static CommonProperty getInstance(){
        if(configProperty == null){
            configProperty = new CommonProperty();
        }
        return configProperty;
    }

}
