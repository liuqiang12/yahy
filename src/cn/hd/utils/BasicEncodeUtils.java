package cn.hd.utils;

import java.security.MessageDigest;

/**
 * 加密算法
 */
public class BasicEncodeUtils {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    /**
     * MD5
     * @param b
     * @return
     */
    public static String getMD5(byte[] b){
        StringBuilder sb = new StringBuilder();
        try {
            byte[] btInput = b;
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            md5.update(btInput);
            // 获得密文
            byte[] md = md5.digest();
            for (byte b1 : md) {
                sb.append(String.format("%02x", b1)); // 10进制转16进制，x表示以十六进制形式小写输出，02 表示不足两位前面补0输出
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Takes the raw bytes from the digest and formats them correct.
     *
     * @param bytes the raw bytes from the digest.
     * @return the formatted bytes.
     */
    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    public static String getSHA1(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
