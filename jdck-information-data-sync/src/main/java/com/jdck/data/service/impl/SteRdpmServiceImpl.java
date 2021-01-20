package com.jdck.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jdck.data.common.AlarmUtils;
import com.jdck.data.dao.SteRdpmHDao;
import com.jdck.data.dao.SteRdpmRDao;
import com.jdck.data.entity.*;
import com.jdck.data.service.EqinfoService;
import com.jdck.data.service.SteRdpmService;
import com.jdck.data.service.SteStssRService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@Slf4j
public class SteRdpmServiceImpl implements SteRdpmService {
    @Autowired
    private EqinfoService eqinfoService;
    @Autowired
    private SteRdpmRDao steRdpmRDao;
    @Autowired
    private SteRdpmHDao steRdpmHDao;
    @Autowired
    private SteStssRService steStssRService;

    @Override
    public void lfwyCurrentData(JSONObject data, String alarmLevel) {
        SteRdpmR steRdpmR = JSONObject.parseObject(JSON.toJSONString(data), SteRdpmR.class);
        Eqinfo firstByEidEquals = eqinfoService.findFirstByEidEquals(steRdpmR.getStcd(), "位移");
        if (null == firstByEidEquals) {
            return;
        }
        Optional<SteRdpmR> byId = steRdpmRDao.findById(steRdpmR.getStcd());
        if (byId.isPresent()) {
            SteRdpmR steRdpmR1 = byId.get();
            if (steRdpmR1.getTm().before(steRdpmR.getTm())) {
                steRdpmRDao.save(steRdpmR);
            }
        } else {
            steRdpmRDao.save(steRdpmR);
        }
        SteRdpmH steRdpmH = new SteRdpmH();
        steRdpmH.setStcd(steRdpmR.getStcd());
        steRdpmH.setTm(steRdpmR.getTm());
        steRdpmH.setRdj(steRdpmR.getRdj());
        steRdpmH.setRdt(steRdpmR.getRdt());
        steRdpmH.setMar(steRdpmR.getMar());
        steRdpmHDao.save(steRdpmH);
        //更改测站报警状态
        String status = "00000000000000000000000010000000";
        if (!StringUtils.isEmpty(alarmLevel)) {
            String substring = alarmLevel.substring(8, 9);
            String alarm = AlarmUtils.getAlarmLevel(substring, 2);
            status = "000000000000" + alarm + "000000000010000000";
        }
        Integer zt = Integer.valueOf(status, 2);
        SteStssR steStssR = new SteStssR();
        steStssR.setStcd(steRdpmR.getStcd());
        steStssR.setTm(steRdpmR.getTm());
        steStssR.setZt(zt);
        steStssRService.save(steStssR);
        log.info("位移测站" + steRdpmR.getStcd() + "报警状态更改为为" + zt);

    }
}
