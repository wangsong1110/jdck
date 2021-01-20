package com.jdck.data.common;

import java.math.BigInteger;

/**
 * @Description
 * @Author zyc
 * @Date 2020/7/29 17:01
 */
public class HexUtils {

    /**
     * 16进制累加和校验是否通过
     *
     * @param data
     * @param check
     * @return
     */
    public static boolean isCheck(String data, String check) {
        String checkStr = makeChecksum(data);
        if (checkStr.equals(check)) {
            return true;

        }
        return false;
    }

    /**
     * 16进制累加和校验返回
     *
     * @param data
     * @return
     */
    public static String makeChecksum(String data) {
        if (data == null || data.equals("")) {
            return "";
        }
        int total = 0;
        int len = data.length();
        int num = 0;
        while (num < len) {
            String s = data.substring(num, num + 1);
            System.out.println(s);
            total += Integer.parseInt(s, 16);
            num = num + 1;
        }
        /**
         * 用256求余最大是255，即16进制的FF
         */
        int mod = total % 256;
        String hex = Integer.toHexString(mod);
        len = hex.length();
        // 如果不够校验位的长度，补0,这里用的是两位校验
        if (len < 2) {
            hex = "0" + hex;
        }
        return hex;
    }

    /**
     * 将16进制字符串转成浮点数,带符号
     *
     * @param data
     * @return
     */
    public static float hex2Float(String data) {
        return Float.intBitsToFloat(new BigInteger(data, 16).intValue());
    }

    /**
     * 将16进制字符串转成带符号的10进制
     */
    public static Integer hex2DecSymbol(String data) {
        return Integer.valueOf(data, 16).intValue();
    }

    public static Long hex2DecNoSymbol(String data) {
        return Long.valueOf(data, 16).longValue();
    }
}
