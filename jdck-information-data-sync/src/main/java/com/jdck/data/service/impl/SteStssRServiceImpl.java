package com.jdck.data.service.impl;

import com.jdck.data.dao.SteStssRDao;
import com.jdck.data.entity.SteStssR;
import com.jdck.data.service.SteStssRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SteStssRServiceImpl implements SteStssRService {
    @Autowired
    private SteStssRDao steStssRDao;
    @Override
    public void save(SteStssR steStssR) {
        steStssRDao.save(steStssR);
    }
}
