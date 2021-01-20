package com.jdck.data.service;

import com.jdck.data.entity.Eqinfo;

/**
 * @Description
 * @Author zyc
 * @Date 2020/8/5 11:18
 */
public interface EqinfoService {
    Eqinfo findFirstByEidEquals(String eid, String etype);
}
