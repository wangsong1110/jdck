package com.jdck.data.service;

import com.alibaba.fastjson.JSONObject;
import com.jdck.data.entity.SteAdpmH;
/**
 * @Description
 * @Author zyc
 * @Date 2020/8/5 11:02
 */
public interface SteAdpmService {
	void save(SteAdpmH steAdpmH);

	void gnssCurrentData(JSONObject gnssStr, String alarmLevel);
}
