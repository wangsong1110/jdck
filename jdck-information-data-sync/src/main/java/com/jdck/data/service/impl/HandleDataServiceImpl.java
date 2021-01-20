package com.jdck.data.service.impl;

import com.jdck.data.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HandleDataServiceImpl implements HandleDataService {
	@Autowired
	private SteSmstService steSmstService;

	@Autowired
	private SteAdpmService steAdpmService;

	@Autowired
	private SteDpaeService steDpaeService;
	@Autowired
	private StePptnService stePptnService;
	@Autowired
	private SteRdpmService steRdpmService;
	@Autowired
	private SteWtlvService steWtlvService;
	@Autowired
	private SteDdpmService steDdpmService;

	@Override
	public void handleCurrentDate(JSONObject jsonObject) {

		String identification = jsonObject.get("identification").toString();

		String alarmLevel = "";

		if (null != jsonObject.get("alarmLevel")) {
			alarmLevel = jsonObject.get("alarmLevel").toString();
		}

		Object o = jsonObject.get("data");
		JSONObject data = JSONObject.parseObject(JSON.toJSONString(o));

		if (data == null || data.isEmpty()) {
			return;
		}

		// 类型标识 1:降雨量，2：含水率，3：gnss，4：倾角，5：裂缝，6：泥位
		switch (identification) {
		case "1":
			log.info("-----------接收到降雨量实时数据" + jsonObject);
			System.out.println("-----------接收到降雨量实时数据:" + jsonObject);
			stePptnService.RainData(data, alarmLevel);
			break;
		case "2":
			log.info("-----------接收到含水率实时数据" + jsonObject);
			steSmstService.RateWaterData(data, alarmLevel);
			break;
		case "3":
			log.info("-----------接收到gnss实时数据" + jsonObject);
			steAdpmService.gnssCurrentData(data, alarmLevel);
			break;
		case "4":
			log.info("-----------接收到倾角实时数据" + jsonObject);
			steDpaeService.qjCurrentData(data, alarmLevel);
			break;
		case "5":
			log.info("-----------接收到裂缝实时数据" + jsonObject);
			steRdpmService.lfwyCurrentData(data, alarmLevel);
			break;
		case "6":
			log.info("-----------接收到泥位实时数据" + jsonObject);
			steWtlvService.nwCurrentData(data, alarmLevel);
			break;
		case "7":
			log.info("-----------接收到水位实时数据" + jsonObject);
			steWtlvService.dxsdtCurrentData(data, alarmLevel);
			break;
		case "8":
			log.info("-----------接收到深部位移实时数据" + jsonObject);
			steDdpmService.sbwyCurrentData(data, alarmLevel);
			break;
		}
	}
}
