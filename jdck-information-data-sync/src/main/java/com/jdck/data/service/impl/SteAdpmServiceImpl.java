package com.jdck.data.service.impl;

import java.util.Optional;

import com.jdck.data.service.SteStssRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jdck.data.common.AlarmUtils;
import com.jdck.data.common.DateUtil;
import com.jdck.data.dao.EqinfoDao;
import com.jdck.data.dao.SteAdpmHDao;
import com.jdck.data.dao.SteAdpmRDao;
import com.jdck.data.dao.SteStssRDao;
import com.jdck.data.entity.Eqinfo;
import com.jdck.data.entity.SteAdpmH;
import com.jdck.data.entity.SteAdpmR;
import com.jdck.data.entity.SteStssR;
import com.jdck.data.service.SteAdpmService;
import com.jdck.util.SystemConstant;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description
 * @Author zyc
 * @Date 2020/8/5 11:03
 */
@Slf4j
@Service
public class SteAdpmServiceImpl implements SteAdpmService {
    @Autowired
    private SteAdpmHDao steAdpmHDao;

    @Autowired
    private SteAdpmRDao steAdpmRDao;

    @Autowired
    private SteStssRDao steStssRDao;

    @Autowired
    private EqinfoDao eqinfoDao;

    @Override
    public void save(SteAdpmH steAdpmH) {
        steAdpmHDao.save(steAdpmH);
    }

    @Override
    public void gnssCurrentData(JSONObject gnssStr, String alarmLevel) {

        SteAdpmR steAdpmR = JSONObject.parseObject(JSON.toJSONString(gnssStr), SteAdpmR.class);

        Optional<Eqinfo> eqInfo = eqinfoDao.findFirstByEidEqualsAndEtypeEquals(steAdpmR.getStcd(), "GNSS位移");
        // 验证设备是否存在
        if (null == eqInfo || !eqInfo.isPresent()) {
            log.error("根据gnss传感器编码未查询到相关信息，stcd:" + gnssStr.getString("stcd"));
            return;
        }

        Optional<SteAdpmR> byId = steAdpmRDao.findById(gnssStr.getString("stcd"));
        if (byId.isPresent()) {
            SteAdpmR steAdpmR1 = byId.get();
            if (steAdpmR1.getTm().before(steAdpmR.getTm())) {
                steAdpmRDao.save(steAdpmR);

                saveSteStss(steAdpmR, alarmLevel);
            }
        } else {
            steAdpmRDao.save(steAdpmR);
            saveSteStss(steAdpmR, alarmLevel);
        }

        // 数据推送到历史表
        SteAdpmH steAdpmH = new SteAdpmH();
        // 测站编码
        steAdpmH.setStcd(steAdpmR.getStcd());
        // 时间
        steAdpmH.setTm(steAdpmR.getTm());
        // 基线长度
        steAdpmH.setD(0D);
        // 基线长度南北向分量
        steAdpmH.setNs(0D);
        // 基线长度东西向分量
        steAdpmH.setWe(0D);
        // 基线长度与基准基线长度差值
        steAdpmH.setDd(0D);
        // 基线长度高向分量
        steAdpmH.setH(0D);
        // 基线长度南北向分量与基准基线南北向分量差值
        steAdpmH.setNsd(steAdpmR.getNsd());
        // 基线长度东西向分量与基准基线东西向分量差值
        steAdpmH.setWed(steAdpmR.getWed());
        // 基线长度高向分量与基准基线高向分量差值
        steAdpmH.setHd(steAdpmR.getHd());
        // 报文类型
        // 创建时间
        //steAdpmH.setCREATEDATE(DateUtil.getCurrentDate());
        steAdpmHDao.save(steAdpmH);
        log.info("-----------gnss数据同步处理完成--------:" + gnssStr.toJSONString());
    }

    private void saveSteStss(SteAdpmR steAdpmR, String alarmLevel) {
        SteStssR steStssR = new SteStssR();
        steStssR.setStcd(steAdpmR.getStcd());
        steStssR.setTm(steAdpmR.getTm());
        steStssR.setZt(AlarmUtils.getAlarmLevel(alarmLevel, SystemConstant.GNSS_ID));
        steStssRDao.save(steStssR);
    }

}
