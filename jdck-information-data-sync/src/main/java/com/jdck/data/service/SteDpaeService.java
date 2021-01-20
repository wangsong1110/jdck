package com.jdck.data.service;
import com.alibaba.fastjson.JSONObject;
import com.jdck.data.entity.SteDpaeR;

/**
 * @Description
 * @Author zyc
 * @Date 2020/8/5 11:06
 */
public interface SteDpaeService {
	void save(SteDpaeR steDpaer);

	public SteDpaeR findByStcd(String stcd);
	
	public void saveAndFlush(SteDpaeR steDpaeR);
 
	public void qjCurrentData(JSONObject gnssStr,String alarmLevel);

}
