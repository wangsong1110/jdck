package com.jdck.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jdck.data.common.AlarmUtils;
import com.jdck.data.dao.SteWtlvHDao;
import com.jdck.data.dao.SteWtlvRDao;
import com.jdck.data.entity.*;
import com.jdck.data.service.EqinfoService;
import com.jdck.data.service.SteStssRService;
import com.jdck.data.service.SteWtlvService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@Slf4j
public class SteWtlvServiceImpl implements SteWtlvService {
    @Autowired
    private EqinfoService eqinfoService;
    @Autowired
    private SteWtlvRDao steWtlvRDao;
    @Autowired
    private SteWtlvHDao steWtlvHDao;
    @Autowired
    private SteStssRService steStssRService;

    @Override
    public void nwCurrentData(JSONObject data, String alarmLevel) {
        SteWtlvR steWtlvR = JSONObject.parseObject(JSON.toJSONString(data), SteWtlvR.class);
        Eqinfo firstByEidEquals = eqinfoService.findFirstByEidEquals(steWtlvR.getStcd(), "泥位");
        if (null == firstByEidEquals) {
            return;
        }
        Optional<SteWtlvR> byId = steWtlvRDao.findById(steWtlvR.getStcd());
        if (byId.isPresent()) {
            SteWtlvR steWtlvR1 = byId.get();
            if (steWtlvR1.getTm().before(steWtlvR.getTm())) {
                steWtlvRDao.save(steWtlvR);
            }
        } else {
            steWtlvRDao.save(steWtlvR);
        }
        SteWtlvH steWtlvH = new SteWtlvH();
        steWtlvH.setStcd(steWtlvR.getStcd());
        steWtlvH.setTm(steWtlvR.getTm());
        steWtlvH.setZ(steWtlvR.getZ());
        steWtlvH.setZk(steWtlvR.getZk());
        steWtlvH.setZm(steWtlvR.getZm());
        steWtlvHDao.save(steWtlvH);
        //String status = "000000000000000000010000";
        String status = "00000000000000000000000010000000";
        if (!StringUtils.isEmpty(alarmLevel)) {
            String substring = alarmLevel.substring(3, 4);
            String alarm = AlarmUtils.getAlarmLevel(substring, 1);
            status = "00000000000000000000000010000" + alarm + "00";
        }
        Integer zt = Integer.valueOf(status, 2);
        SteStssR steStssR = new SteStssR();
        steStssR.setStcd(steWtlvR.getStcd());
        steStssR.setTm(steWtlvR.getTm());
        steStssR.setZt(zt);
        steStssRService.save(steStssR);
        log.info("泥位测站" + steWtlvR.getStcd() + "报警状态更改为" + zt);
    }
    
    public void dxsdtCurrentData(JSONObject data, String alarmLevel) {
        SteWtlvR steWtlvR = JSONObject.parseObject(JSON.toJSONString(data), SteWtlvR.class);
        Eqinfo firstByEidEquals = eqinfoService.findFirstByEidEquals(steWtlvR.getStcd(), "地下水动态");
        if (null == firstByEidEquals) {
            return;
        }
        Optional<SteWtlvR> byId = steWtlvRDao.findById(steWtlvR.getStcd());
        if (byId.isPresent()) {
            SteWtlvR steWtlvR1 = byId.get();
            if (steWtlvR1.getTm().before(steWtlvR.getTm())) {
                steWtlvRDao.save(steWtlvR);
            }
        } else {
            steWtlvRDao.save(steWtlvR);
        }
        SteWtlvH steWtlvH = new SteWtlvH();
        steWtlvH.setStcd(steWtlvR.getStcd());
        steWtlvH.setTm(steWtlvR.getTm());
        steWtlvH.setZ(steWtlvR.getZ());
        steWtlvH.setZk(steWtlvR.getZk());
        steWtlvH.setZm(steWtlvR.getZm());
        steWtlvHDao.save(steWtlvH);
        //String status = "000000000000000000010000";
        String status = "00000000000000000000000010000000";
        if (!StringUtils.isEmpty(alarmLevel)) {
            String substring = alarmLevel.substring(3, 4);
            String alarm = AlarmUtils.getAlarmLevel(substring, 1);
            status = "00000000000000000000000010000" + alarm + "00";
        }
        Integer zt = Integer.valueOf(status, 2);
        SteStssR steStssR = new SteStssR();
        steStssR.setStcd(steWtlvR.getStcd());
        steStssR.setTm(steWtlvR.getTm());
        steStssR.setZt(zt);
        steStssRService.save(steStssR);
        log.info("水位测站" + steWtlvR.getStcd() + "报警状态更改为" + zt);
    }
}
