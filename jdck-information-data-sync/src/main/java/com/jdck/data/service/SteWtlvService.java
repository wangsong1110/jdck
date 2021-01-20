package com.jdck.data.service;

import com.alibaba.fastjson.JSONObject;

public interface SteWtlvService {
	void nwCurrentData(JSONObject jsonObject, String alarmLevel);

	void dxsdtCurrentData(JSONObject jsonObject, String alarmLevel);

}
