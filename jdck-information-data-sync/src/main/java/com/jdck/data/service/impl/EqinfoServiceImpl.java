package com.jdck.data.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdck.data.dao.EqinfoDao;
import com.jdck.data.entity.Eqinfo;
import com.jdck.data.service.EqinfoService;

/**
 * @Description
 * @Author zyc
 * @Date 2020/8/5 11:19
 */
@Service
public class EqinfoServiceImpl implements EqinfoService {
    @Autowired
    private EqinfoDao eqinfoDao;

    @Override
    public Eqinfo findFirstByEidEquals(String eid, String etype) {
        Optional<Eqinfo> optionalEqinfo = eqinfoDao.findFirstByEidEqualsAndEtypeEquals(eid, etype);
        if (optionalEqinfo.isPresent()) {
            return optionalEqinfo.get();
        }
        return null;
    }
}
