package com.jdck.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdck.data.common.DateUtil;
import com.jdck.data.entity.SteAdpmH;
import com.jdck.data.service.SteAdpmService;
import com.jdck.util.SystemConstant;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

/**
 * @description: 信息化实时数据获取
 * @create: 2020-07-29
 **/
@Slf4j
@RestController
public class DataParserController {

    @Autowired
    private SteAdpmService steAdpmHService;

    /***
     * 倾角数据 实时数据获取
     *
     */
    @RequestMapping("/qj/currentData")
    public String qjCurrentData(@RequestBody JSONObject qjData) {

        log.info("参数 qjData:" + qjData);

        if (null == qjData) {
            log.error("qjData接收到的数据参数为空");
            return SystemConstant.RESULT_FAIL;
        }

        try {

            // 数据推送到历史表
            SteAdpmH steAdpmH = new SteAdpmH();
            // 测站编码
            steAdpmH.setStcd("789456");
            // 时间
            steAdpmH.setTm(DateUtil.getCurrentDate());
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
            // 创建时间
            //steAdpmH.setCREATEDATE(DateUtil.getCurrentDate());
            steAdpmHService.save(steAdpmH);

        } catch (Exception e) {
            log.error(e.toString());
            return SystemConstant.RESULT_FAIL;
        }
        return SystemConstant.RESULT_SUCCESS;
    }
}
