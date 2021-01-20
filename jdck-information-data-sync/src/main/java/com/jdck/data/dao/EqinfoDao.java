package com.jdck.data.dao;

import com.jdck.data.config.jpa.BaseRepository;
import com.jdck.data.entity.Eqinfo;

import java.util.Optional;

public interface EqinfoDao extends BaseRepository<Eqinfo, String> {
    Optional<Eqinfo> findFirstByEidEqualsAndEtypeEquals(String eid,String etype);
}
