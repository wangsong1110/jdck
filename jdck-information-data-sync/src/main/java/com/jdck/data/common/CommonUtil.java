package com.jdck.data.common;

import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

/**
 * @Description
 * @Author zyc
 * @Date 2020/8/3 15:03
 */
@Slf4j
public class CommonUtil {
    public static byte checkSum(byte[] data) {
        if (data.length <= 0) {
            throw new ArrayIndexOutOfBoundsException("byte data array is empty");
        }
        byte cs = 0;
        for (int i = 0; i < data.length; i++) {
            cs ^= data[i];
        }
        return cs;
    }

    public static String getId() {
        UUID uuid = UUID.randomUUID();
        // 得到对象产生的ID
        return uuid.toString().replaceAll("-", "").toUpperCase();
    }

    private static byte ascII2Bcd(byte asc) {
        if ((asc >= '0') && (asc <= '9'))
            return (byte) (asc - '0');
        else if ((asc >= 'A') && (asc <= 'F'))
            return (byte) (asc - 'A' + 10);
        else if ((asc >= 'a') && (asc <= 'f'))
            return (byte) (asc - 'a' + 10);
        else
            return (byte) (asc - 48);
    }

    /**
     * bcd转为string 设备序列号需要从6字节数组转成字符串
     */
    public static String bcd2String(byte[] bytes) {
        StringBuilder temp = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            // 高四位
            temp.append((bytes[i] & 0xf0) >>> 4);
            // 低四位
            temp.append(bytes[i] & 0x0f);
        }
        return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp.toString().substring(1) : temp.toString();
    }

    /**
     * 将BCD码转成String
     *
     * @param b
     * @return
     */
    public static String bcdToString(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            int h = ((b[i] & 0xff) >> 4) + 48;
            sb.append((char) h);
            int l = (b[i] & 0x0f) + 48;
            sb.append((char) l);
        }
        return sb.toString();
    }

    /**
     * 字符串==>BCD字节数组
     *
     * @param str
     * @return BCD字节数组
     */
    public static byte[] string2Bcd(String str) {
        // 奇数,前补零
        if ((str.length() & 0x1) == 1) {
            str = "0" + str;
        }

        byte ret[] = new byte[str.length() / 2];
        byte bs[] = str.getBytes();
        for (int i = 0; i < ret.length; i++) {
            byte high = ascII2Bcd(bs[2 * i]);
            byte low = ascII2Bcd(bs[2 * i + 1]);
            ret[i] = (byte) ((high << 4) | low);
        }
        return ret;
    }

    public static String convertByteBufToString(ByteBuf buf) {
        String str;
        if (buf.hasArray()) { // 处理堆缓冲区
            str = new String(buf.array(), buf.arrayOffset() + buf.readerIndex(), buf.readableBytes());
        } else { // 处理直接缓冲区以及复合缓冲区
            byte[] bytes = new byte[buf.readableBytes()];
            buf.getBytes(buf.readerIndex(), bytes);
            str = new String(bytes, 0, buf.readableBytes());
        }
        return str;
    }

    /**
     * 把一个byte转化位整形,通常为指令用
     *
     * @param value
     * @return
     * @throws Exception
     */
    public static int oneByteToInteger(byte value) {
        return (int) value & 0xFF;
    }

    /**
     * 把一个2位的数组转化位整形
     *
     * @param value
     * @return
     * @throws Exception
     */
    public static int twoBytesToInteger(byte[] value) {

        int temp0 = value[0] & 0xFF;
        int temp1 = value[1] & 0xFF;
        return ((temp0 << 8) + temp1);
    }

    /**
     * 把一个3位的数组转化位整形
     *
     * @param value
     * @return
     * @throws Exception
     */
    public static int threeBytesToInteger(byte[] value) {
        int temp0 = value[0] & 0xFF;
        int temp1 = value[1] & 0xFF;
        int temp2 = value[2] & 0xFF;
        return ((temp0 << 16) + (temp1 << 8) + temp2);
    }

    /**
     * 把一个4位的数组转化位整形,通常为指令用
     *
     * @param value
     * @return
     * @throws Exception
     */
    public static int fourBytesToInteger(byte[] value) {
        // if (value.length < 4) {
        // throw new Exception("Byte array too short!");
        // }
        int temp0 = value[0] & 0xFF;
        int temp1 = value[1] & 0xFF;
        int temp2 = value[2] & 0xFF;
        int temp3 = value[3] & 0xFF;
        return ((temp0 << 24) + (temp1 << 16) + (temp2 << 8) + temp3);
    }

    public static int byteToInteger(byte[] value) {
        int result;
        if (value.length == 1) {
            result = oneByteToInteger(value[0]);
        } else if (value.length == 2) {
            result = twoBytesToInteger(value);
        } else if (value.length == 3) {
            result = threeBytesToInteger(value);
        } else if (value.length == 4) {
            result = fourBytesToInteger(value);
        } else {
            result = fourBytesToInteger(value);
        }
        return result;
    }

    /**
     * 接收消息时转义<br>
     *
     * <pre>
     * 0x7d01 <====> 0x7d
     * 0x7d02 <====> 0x7e
     * </pre>
     *
     * @param bs    要转义的字节数组
     * @param start 起始索引
     * @param end   结束索引
     * @return 转义后的字节数组
     * @throws IOException
     * @throws Exception
     */
    public static byte[] doEscape4Receive(byte[] bs) {
        if (bs.length <= 0)
            throw new ArrayIndexOutOfBoundsException("array is empty");
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            for (int i = 0; i < bs.length; i++) {
                // 为了防止最后一位越界，i+1必须小于length
                if (i + 1 < bs.length) {
                    if (bs[i] == 0x7d && bs[i + 1] == 0x01) {
                        baos.write(0x7d);
                        i++;
                    } else if (bs[i] == 0x7d && bs[i + 1] == 0x02) {
                        baos.write(0x7e);
                        i++;
                    } else {
                        baos.write(bs[i]);
                    }
                } else {
                    // 最后一位不再检验
                    baos.write(bs[i]);
                }
            }
            return baos.toByteArray();
        } catch (Exception e) {
            log.error("escape exception", e);
        }
        return bs;
    }

    /**
     * 发送消息时转义<br>
     *
     * <pre>
     *  0x7e <====> 0x7d02
     * </pre>
     *
     * @param bs    要转义的字节数组
     * @param start 起始索引
     * @param end   结束索引
     * @return 转义后的字节数组
     * @throws Exception
     */
    public static byte[] doEscape4Send(byte[] bs, int start, int end) {
        if (start < 0 || end > bs.length)
            throw new ArrayIndexOutOfBoundsException("doEscape4Send error : index out of bounds(start=" + start
                    + ",end=" + end + ",bytes length=" + bs.length + ")");
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            for (int i = 0; i < start; i++) {
                baos.write(bs[i]);
            }
            for (int i = start; i < end; i++) {
                if (bs[i] == 0x7e) {
                    baos.write(0x7d);
                    baos.write(0x02);
                } else {
                    baos.write(bs[i]);
                }
            }
            for (int i = end; i < bs.length; i++) {
                baos.write(bs[i]);
            }
            return baos.toByteArray();
        } catch (Exception e) {
            log.error("escape exception", e);
        }
        return bs;
    }

    public static String toFullBinaryString(int num)// 将整数num转化为32位的二进制数
    {
        char[] chs = new char[Integer.SIZE];
        for (int i = 0; i < Integer.SIZE; i++) {
            chs[Integer.SIZE - 1 - i] = (char) (((num >> i) & 1) + '0');
        }
        return new String(chs);
    }
}
