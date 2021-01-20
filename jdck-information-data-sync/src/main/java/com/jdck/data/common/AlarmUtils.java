package com.jdck.data.common;

import com.jdck.util.SystemConstant;

public class AlarmUtils {

    /**
     * 根据报警状态获取最高报警状态
     *
     * @param alarm
     * @return
     */
    public static String getAlarmLevel(String alarm, Integer length) {
        String code = null;
        if (length == 2) {
            if (alarm.equals("0")) {
                code = "00";
            } else if (alarm.equals("1")) {
                code = "01";
            } else if (alarm.equals("2")) {
                code = "10";
            } else if (alarm.equals("3")) {
                code = "11";
            }
        } else {
            if (alarm.equals("0")) {
                code = "0";
            } else {
                code = "1";
            }
        }
        return code;
    }

    /**
     * 根据预警代码获取预警信息
     *
     * @param data
     * @return
     */
    public static String getAlarmInfomation(String data) {
        if (data == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        /*
         * 第一位：gnss X报警级别 第二位：gnss Y报警级别 第三位：gnss Z 报警级别 第四位：含水率报警级别 第五位: 泥位报警级别
         * 第六位：雨量报警级别 第七位：倾角X报警级别 第八位：倾角Y报警级别 第九位：倾角Z报警级别 第十位：位移报警级别
         */
        if (!data.equals(SystemConstant.ALARM_ZERO)) {
            stringBuilder.append(level2info(data.charAt(0), SystemConstant.GNSSXAttr));
            stringBuilder.append(level2info(data.charAt(1), SystemConstant.GNSSYAttr));
            stringBuilder.append(level2info(data.charAt(2), SystemConstant.GNSSZAttr));
            stringBuilder.append(level2info(data.charAt(3), SystemConstant.NWAttr));
            stringBuilder.append(level2info(data.charAt(4), SystemConstant.YLAttr));
            stringBuilder.append(level2info(data.charAt(5), SystemConstant.QJXAttr));
            stringBuilder.append(level2info(data.charAt(6), SystemConstant.QJYAttr));
            stringBuilder.append(level2info(data.charAt(7), SystemConstant.QJZAttr));
            stringBuilder.append(level2info(data.charAt(8), SystemConstant.LFAttr));
            stringBuilder.append(level2info(data.charAt(9), SystemConstant.HSL10Attr));
            stringBuilder.append(level2info(data.charAt(10), SystemConstant.HSL20Attr));
            stringBuilder.append(level2info(data.charAt(11), SystemConstant.HSL30Attr));
            stringBuilder.append(level2info(data.charAt(12), SystemConstant.HSL40Attr));
            stringBuilder.append(level2info(data.charAt(13), SystemConstant.HSL50Attr));
            stringBuilder.append(level2info(data.charAt(14), SystemConstant.HSL60Attr));
            stringBuilder.append(level2info(data.charAt(15), SystemConstant.HSL70Attr));
            stringBuilder.append(level2info(data.charAt(16), SystemConstant.HSL80Attr));
        }
        return stringBuilder.toString();
    }

    /**
     * 预警等级转换为汉字
     *
     * @param data
     * @return
     */
    private static String level2info(char data, String attr) {
        String result = "";

        switch (data) {
            case '0':
                result = "";
                break;
            case '1':
                result = attr + "红色预警";
                break;
            case '2':
                result = attr + "橙色预警";
                break;
            case '3':
                result = attr + "黄色预警";
                break;
        }
        return result;
    }

    /**
     * 倾角和gnss报警级别
     *
     * @param alarmLevel
     * @return
     */
    public static Integer getAlarmLevel(String alarmLevel, String id) {

        int stindex = 0;
        int endindex = 0;
        String returnStr = "";

        // gnss
        if (SystemConstant.GNSS_ID.equals(id)) {
            returnStr = "2";
            stindex = 0;
            endindex = 3;
            // 倾角
        } else if (SystemConstant.QJ_ID.equals(id)) {
            returnStr = "2000";
            stindex = 5;
            endindex = 8;
        }

        returnStr = returnStr + alarmLevel.substring(stindex, endindex);

//		for (char i : alarmLevel.toCharArray()) {
//
//			String str = String.valueOf(i);
//			if ("1".equals(str)) {
//				returnStr += "3";
//
//			} else if ("3".equals(str)) {
//				returnStr += "1";
//			} else {
//				returnStr += str;
//			}
//		}

        if (SystemConstant.GNSS_ID.equals(id)) {
            returnStr = returnStr + "00000";
            // 倾角
        } else if (SystemConstant.QJ_ID.equals(id)) {
            returnStr = returnStr + "00";
        }

        return Integer.parseInt(returnStr);
    }

//	
//	public static void main(String args[]) {
//		System.out.print(getAlarmLevel("00124567890123", SystemConstant.QJ_ID));
//	}
}
