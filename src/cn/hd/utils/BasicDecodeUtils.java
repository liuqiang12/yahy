package cn.hd.utils;

/**
 * Created by DELL on 2017/8/29.
 * 解密算法工具
 */
public class BasicDecodeUtils {
    private static final int buffer = 2048;

    private static BasicDecodeUtils ourInstance = new BasicDecodeUtils();

    public static BasicDecodeUtils getInstance() {
        return ourInstance;
    }

    private BasicDecodeUtils() {
    }
}
