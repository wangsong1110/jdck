package com.jdck.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jdck.data.common.AlarmUtils;
import com.jdck.data.dao.SteSmstHDao;
import com.jdck.data.dao.SteSmstRDao;
import com.jdck.data.entity.Eqinfo;
import com.jdck.data.entity.SteSmstH;
import com.jdck.data.entity.SteSmstR;
import com.jdck.data.entity.SteStssR;
import com.jdck.data.service.EqinfoService;
import com.jdck.data.service.SteSmstService;
import com.jdck.data.service.SteStssRService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@Slf4j
public class SteSmstServiceImpl implements SteSmstService {
    @Autowired
    private SteSmstRDao steSmstRDao;
    @Autowired
    private SteSmstHDao steSmstHDao;
    @Autowired
    private EqinfoService eqinfoService;
    @Autowired
    private SteStssRService steStssRService;

    @Override
    public void RateWaterData(JSONObject jsonObject, String alarmLevel) {
        SteSmstR steSmstR = JSONObject.parseObject(JSON.toJSONString(jsonObject), SteSmstR.class);
        Eqinfo firstByEidEquals = eqinfoService.findFirstByEidEquals(steSmstR.getStcd(), "含水率");
        if (null == firstByEidEquals) {
            return;
        }
        Optional<SteSmstR> byId = steSmstRDao.findById(steSmstR.getStcd());
        if (byId.isPresent()) {
            SteSmstR steSmstR1 = byId.get();
            if (steSmstR1.getTm().before(steSmstR.getTm())) {
                steSmstRDao.save(steSmstR);
            }
        } else {
            steSmstRDao.save(steSmstR);
        }
        SteSmstH steSmstH = new SteSmstH();
        steSmstH.setStcd(steSmstR.getStcd());
        steSmstH.setTm(steSmstR.getTm());
        steSmstH.setMar(steSmstR.getMar());
        steSmstH.setMb(steSmstR.getMb());
        steSmstH.setMt(steSmstR.getMt());
        steSmstH.setMm(steSmstR.getMm());
        steSmstHDao.save(steSmstH);
        log.info("-----------含水率数据同步处理完成" + steSmstR);
        //更新测站报警状态
        String status = "00000000000000000000000010000000";
        if (!StringUtils.isEmpty(alarmLevel)) {
            String mb = alarmLevel.substring(16, 17);
            String alarmmb = AlarmUtils.getAlarmLevel(mb, 2);
            String mm = alarmLevel.substring(12, 13);
            String alarmmm = AlarmUtils.getAlarmLevel(mm, 2);
            String mt = alarmLevel.substring(9, 10);
            String alarmmt = AlarmUtils.getAlarmLevel(mt, 2);
            status = "000000" + alarmmb + alarmmm + alarmmt + "00000000000010000000";
        }
        Integer zt = Integer.valueOf(status, 2);
        SteStssR steStssR = new SteStssR();
        steStssR.setStcd(steSmstR.getStcd());
        steStssR.setTm(steSmstR.getTm());
        steStssR.setZt(zt);
        steStssRService.save(steStssR);
        log.info("含水率测站" + steSmstR.getStcd() + "报警状态更改为" + zt);
    }
}
