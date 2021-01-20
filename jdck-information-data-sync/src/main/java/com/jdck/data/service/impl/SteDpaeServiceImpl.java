package com.jdck.data.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jdck.data.common.AlarmUtils;
import com.jdck.data.common.DateUtil;
import com.jdck.data.dao.EqinfoDao;
import com.jdck.data.dao.SteDpaeHDao;
import com.jdck.data.dao.SteDpaeRDao;
import com.jdck.data.dao.SteStssRDao;
import com.jdck.data.entity.Eqinfo;
import com.jdck.data.entity.SteDpaeH;
import com.jdck.data.entity.SteDpaeR;
import com.jdck.data.entity.SteStssR;
import com.jdck.data.service.SteDpaeService;
import com.jdck.util.SystemConstant;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description
 * @Author zyc
 * @Date 2020/8/5 11:06
 */
@Slf4j
@Service
public class SteDpaeServiceImpl implements SteDpaeService {
	@Autowired
	private SteDpaeRDao steDpaeRDao;
	@Autowired
	private SteDpaeHDao steDpaeHDao;
	@Autowired
	private EqinfoDao eqinfoDao;
	@Autowired
	private SteStssRDao steStssRDao;

	@Override
	public void save(SteDpaeR steDpaeR) {
		steDpaeRDao.save(steDpaeR);
	}

	@Override
	public void saveAndFlush(SteDpaeR steDpaeR) {
		steDpaeRDao.saveAndFlush(steDpaeR);
	}

	@Override
	public SteDpaeR findByStcd(String stcd) {
		Optional<SteDpaeR> byId = steDpaeRDao.findById(stcd);
		if (byId.isPresent()) {
			return byId.get();
		} else {
			return null;
		}

	}

	@Override
	public void qjCurrentData(JSONObject data, String alarmLevel) {

		SteDpaeR steDpaeR = JSONObject.parseObject(JSON.toJSONString(data), SteDpaeR.class);

		Optional<Eqinfo> eqInfo = eqinfoDao.findFirstByEidEqualsAndEtypeEquals(data.getString("stcd"), "倾角计");
		// 验证设备是否存在
		if (null == eqInfo || !eqInfo.isPresent()) {
			log.error("根据倾角传感器编码未查询到相关信息，stcd:" + steDpaeR.getStcd());
			return;
		}

		Optional<SteDpaeR> byId = steDpaeRDao.findById(steDpaeR.getStcd());
		if (byId.isPresent()) {
			SteDpaeR steDpaeR1 = byId.get();
			if (steDpaeR1.getTm().before(steDpaeR.getTm())) {
				steDpaeRDao.save(steDpaeR);
				saveSteStss(steDpaeR, alarmLevel);
			}
		} else {
			steDpaeRDao.save(steDpaeR);
			saveSteStss(steDpaeR, alarmLevel);
		}

		SteDpaeH steDpaeH = new SteDpaeH();
		steDpaeH.setStcd(steDpaeR.getStcd());
		steDpaeH.setTm(steDpaeR.getTm());
		steDpaeH.setTiltangle(steDpaeR.getTiltangle());
		steDpaeH.setTiltdirection(steDpaeR.getTiltdirection());
		steDpaeH.setX(steDpaeR.getX());
		steDpaeH.setY(steDpaeR.getY());
		steDpaeH.setZ(steDpaeR.getZ());
		steDpaeH.setCreatetime(DateUtil.getCurrentDate());
		steDpaeHDao.save(steDpaeH);

		log.info("-----------倾角数据同步处理完成--------:" + data.toJSONString());
	}

	private void saveSteStss(SteDpaeR steDpaeR, String alarmLevel) {
		SteStssR steStssR = new SteStssR();
		steStssR.setStcd(steDpaeR.getStcd());
		steStssR.setTm(steDpaeR.getTm());
		steStssR.setZt(AlarmUtils.getAlarmLevel(alarmLevel, SystemConstant.QJ_ID));
		steStssRDao.save(steStssR);
	}
}
