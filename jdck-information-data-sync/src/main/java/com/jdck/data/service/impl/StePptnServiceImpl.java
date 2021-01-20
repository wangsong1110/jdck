package com.jdck.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jdck.data.common.AlarmUtils;
import com.jdck.data.dao.StePptnHDao;
import com.jdck.data.dao.StePptnRDao;
import com.jdck.data.entity.*;
import com.jdck.data.service.EqinfoService;
import com.jdck.data.service.StePptnService;
import com.jdck.data.service.SteStssRService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@Slf4j
public class StePptnServiceImpl implements StePptnService {
    @Autowired
    private StePptnRDao stePptnRDao;
    @Autowired
    private StePptnHDao stePptnHDao;
    @Autowired
    private EqinfoService eqinfoService;
    @Autowired
    private SteStssRService steStssRService;

    @Override
    public void RainData(JSONObject data, String alarmLevel) {
        StePptnR stePptnR = JSONObject.parseObject(JSON.toJSONString(data), StePptnR.class);
        try {
            Eqinfo firstByEidEquals = eqinfoService.findFirstByEidEquals(stePptnR.getStcd(), "雨量");
            if (null == firstByEidEquals) {
                return;
            }
            Optional<StePptnR> byId = stePptnRDao.findById(stePptnR.getStcd());
            if (byId.isPresent()) {
                StePptnR stePptnR1 = byId.get();
                if (stePptnR1.getTm().before(stePptnR.getTm())) {
                    System.out.println("开始保存");
                    stePptnRDao.save(stePptnR);
                }
            } else {
                System.out.println("开始保存");
                stePptnRDao.save(stePptnR);
            }
            StePptnH stePptnH = new StePptnH();
            stePptnH.setStcd(stePptnR.getStcd());
            stePptnH.setTm(stePptnR.getTm());
            stePptnH.setMar(stePptnR.getMar());
            stePptnH.setAccp(stePptnR.getAccp());
            stePptnH.setCurp(stePptnR.getCurp());
            stePptnH.setDrp(stePptnR.getDrp());
            stePptnH.setDyp(stePptnR.getDyp());
            stePptnH.setIntv(stePptnR.getIntv());
            stePptnHDao.save(stePptnH);
            //更新测站报警状态
            String status = "00000000000000000000000010000000";
            if (!StringUtils.isEmpty(alarmLevel)) {
                String substring = alarmLevel.substring(4, 5);
                String alarm = AlarmUtils.getAlarmLevel(substring, 1);
                status = "00000" + alarm + "00000000000000000010000000";
            }
            Integer zt = Integer.valueOf(status, 2);
            SteStssR steStssR = new SteStssR();
            steStssR.setStcd(stePptnR.getStcd());
            steStssR.setTm(stePptnR.getTm());
            steStssR.setZt(zt);
            steStssRService.save(steStssR);
            log.info("雨量测站" + stePptnR.getStcd() + "报警状态更改为" + zt);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void main(String[] args) {
        String s = "000001000000000000000000000000000";
        Integer integer = Integer.valueOf(s, 2);
        System.out.println(integer);
    }
}
