package cn.hd.common.cfgUtils;

import java.util.ResourceBundle;

/**
 * Created by DELL on 2017/9/1.
 */
public class ConfigProperty {

    private static String propertyFileName = "cn.hd.config.config";
    private static ConfigProperty configProperty;

    public String getPropertyValue(String key) {
        ResourceBundle resourcesTable = null;
        try {
            resourcesTable = ResourceBundle.getBundle(propertyFileName);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("配置文件缺失，请检查class目录下的" + propertyFileName + "配置文件" + e.getMessage());
        }
        try {
            String value = resourcesTable.getString(key);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("配置文件配置项缺失，请检查class目录下的" + propertyFileName + "配置文件中的[" + key + "]  : " + e.getMessage());

        }
        return null;
    }

    public static ConfigProperty getInstance(){
        if(configProperty == null){
            configProperty = new ConfigProperty();
        }
        return configProperty;
    }

}
