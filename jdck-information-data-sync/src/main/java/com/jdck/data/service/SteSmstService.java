package com.jdck.data.service;

import com.alibaba.fastjson.JSONObject;

public interface SteSmstService {
    void RateWaterData(JSONObject jsonObject,String alarmLevel);
}
