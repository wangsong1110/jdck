package com.jdck.util;

/**
 * 系统静态变量
 */
public class SystemConstant {

	/**
	 * 接口处理成功返回值
	 */
	public static final String RESULT_SUCCESS = "success";

	/**
	 * 接口处理失败返回值
	 */
	public static final String RESULT_FAIL = "fail";

	public final static String VERTICAL = "|";

	/**
	 * 传感器类型id
	 */
	// 设备类型
	public static final String STYPE_ID = "3106dbc1-c5f3-4595-9bc9-ebe628f5b9b5";
	// 雨量
	public static final String YL_ID = "ed7ff84b-d159-11ea-80a3-94c691d81cf0";
	// 预警站
	public static final String YJ_ID = "ed8014d8-d159-11ea-80a3-94c691d81cf0";
	// 入户报警器
	public static final String RH_ID = "ed80276c-d159-11ea-80a3-94c691d81cf0";
	// gnss
	public static final String GNSS_ID = "ed803e86-d159-11ea-80a3-94c691d81cf0";
	// 裂缝
	public static final String LF_ID = "ed805d38-d159-11ea-80a3-94c691d81cf0";
	// 含水率
	public static final String HSL_ID = "ed8099c5-d159-11ea-80a3-94c691d81cf0";
	// 倾角
	public static final String QJ_ID = "ed80afc1-d159-11ea-80a3-94c691d81cf0";
	// 泥位
	public static final String NW_ID = "ed80c324-d159-11ea-80a3-94c691d81cf0";
	// 视频
	public static final String SP_ID = "ed80d856-d159-11ea-80a3-94c691d81cf0";
	// 深部位移
	public static final String SBWY_ID = "ec80d856-d159-11ea-80a3-94c691d81ct0";
	// 报警正常代码
	public static final String ALARM_ZERO = "00000000000000000";
	
	// 项目EXCHANGE前缀
	public static final String SUBJECT_EXCHANGE = "XM-%s";
	// 项目QUEUE前缀
	public static final String SUBJECT_QUEUE = "XM-QUE-%s";

	/**
	 * 传感器属性代码
	 */
	// 雨量
	public static final String YLAttr = "雨量计";
	// 预警站
	public static final String YJAttr = "预警仪";
	// 入户报警器
	public static final String RHAttr = "入户报警器";
	// gnss
	public static final String GNSSXAttr = "gnss X方向";
	// gnss
	public static final String GNSSYAttr = "gnss Y方向";
	// gnss
	public static final String GNSSZAttr = "gnss Z方向";
	// 裂缝
	public static final String LFAttr = "裂缝";
	// 含水率 10cm
	public static final String HSL10Attr = "含水率10公分处";
	// 含水率 20cm
	public static final String HSL20Attr = "含水率20公分处";
	// 含水率 30cm
	public static final String HSL30Attr = "含水率30公分处";
	// 含水率 40cm
	public static final String HSL40Attr = "含水率40公分处";
	// 含水率 50cm
	public static final String HSL50Attr = "含水率50公分处";
	// 含水率 60cm
	public static final String HSL60Attr = "含水率60公分处";
	// 含水率 70cm
	public static final String HSL70Attr = "含水率70公分处";
	// 含水率 80cm
	public static final String HSL80Attr = "含水率80公分处";
	// 倾角
	public static final String QJXAttr = "倾角计X轴";
	// 倾角
	public static final String QJYAttr = "倾角计Y轴";
	// 倾角
	public static final String QJZAttr = "倾角计Z轴";
	// 泥位
	public static final String NWAttr = "泥位";
	// 视频
	public static final String SPAttr = "视频";
	/**
	 * mq队列名称
	 */
	// 广播通知队列
	public static final String ALARM_HORN_QUEUE = "Alarm_Horn_Queue";

	public static final String ALARM_HORN_EXCHANGE = "Alarm_Horn_Exchange";

}
