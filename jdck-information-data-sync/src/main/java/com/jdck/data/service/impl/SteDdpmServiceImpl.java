package com.jdck.data.service.impl;

import java.util.Optional;

import com.jdck.data.service.SteStssRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jdck.data.common.AlarmUtils;
import com.jdck.data.dao.EqinfoDao;
import com.jdck.data.dao.SteDdpmHDao;
import com.jdck.data.dao.SteDdpmRDao;
import com.jdck.data.dao.SteStssRDao;
import com.jdck.data.entity.Eqinfo;
import com.jdck.data.entity.SteDdpmH;
import com.jdck.data.entity.SteDdpmR;
import com.jdck.data.entity.SteStssR;
import com.jdck.data.service.SteDdpmService;
import com.jdck.util.SystemConstant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * @Description 深部位移
 * @Author zyc
 * @Date 2020/8/5 11:03
 */
@Slf4j
@Service
public class SteDdpmServiceImpl implements SteDdpmService {
    @Autowired
    private SteDdpmHDao steDdpmHDao;

    @Autowired
    private SteDdpmRDao steDdpmRDao;

    @Autowired
    private SteStssRDao steStssRDao;

    @Autowired
    private EqinfoDao eqinfoDao;
    @Autowired
    private SteStssRService steStssRService;

    @Override
    public void sbwyCurrentData(JSONObject sbwyStr, String alarmLevel) {

        SteDdpmR steDdpmR = JSONObject.parseObject(JSON.toJSONString(sbwyStr), SteDdpmR.class);

        Optional<Eqinfo> eqInfo = eqinfoDao.findFirstByEidEqualsAndEtypeEquals(steDdpmR.getStcd(), "深部位移");
        // 验证设备是否存在
        if (null == eqInfo || !eqInfo.isPresent()) {
            log.error("根据深部位移传感器编码未查询到相关信息，stcd:" + sbwyStr.getString("stcd"));
            return;
        }

        Optional<SteDdpmR> byId = steDdpmRDao.findById(sbwyStr.getString("stcd"));
        if (byId.isPresent()) {
            SteDdpmR steDdpmR1 = byId.get();
            if (steDdpmR1.getTm().before(steDdpmR.getTm())) {
                steDdpmRDao.save(steDdpmR);
                saveSteStss(steDdpmR, alarmLevel);
            }
        } else {
            steDdpmRDao.save(steDdpmR);
            saveSteStss(steDdpmR, alarmLevel);
        }
        // 数据推送到历史表
        SteDdpmH steDdpmH = new SteDdpmH();
        // 测站编码
        steDdpmH.setStcd(steDdpmR.getStcd());
        // 时间
        steDdpmH.setTm(steDdpmR.getTm());
        steDdpmH.setDtt(steDdpmR.getDtt());
        steDdpmH.setDjt(steDdpmR.getDjt());
        steDdpmH.setDtm(steDdpmR.getDtm());
        steDdpmH.setDjm(steDdpmR.getDjm());
        steDdpmH.setDtb(steDdpmR.getDtb());
        steDdpmH.setDjb(steDdpmR.getDjb());
        steDdpmHDao.save(steDdpmH);
        log.info("-----------深部位移数据同步处理完成--------:" + sbwyStr.toJSONString());
    }

    private void saveSteStss(SteDdpmR steDdpmR, String alarmLevel) {
        //更新测站报警状态
        String status = "00000000000000000000000010000000";
        if (!StringUtils.isEmpty(alarmLevel)) {
            String dtt = alarmLevel.substring(17, 18);
            String alarmdtt = AlarmUtils.getAlarmLevel(dtt, 2);
            String dtm = alarmLevel.substring(18, 19);
            String alarmdtm = AlarmUtils.getAlarmLevel(dtm, 2);
            String dtb = alarmLevel.substring(19, 20);
            String alarmdtb = AlarmUtils.getAlarmLevel(dtb, 2);
            status = "00000000000" + alarmdtb + alarmdtm + alarmdtt + "000010000000";
        }
        Integer zt = Integer.valueOf(status, 2);
        SteStssR steStssR = new SteStssR();
        steStssR.setStcd(steDdpmR.getStcd());
        steStssR.setTm(steDdpmR.getTm());
        steStssR.setZt(zt);
        steStssRService.save(steStssR);
        log.info("深部位移测站" + steDdpmR.getStcd() + "报警状态更改为" + zt);
        steStssRDao.save(steStssR);
    }
}
