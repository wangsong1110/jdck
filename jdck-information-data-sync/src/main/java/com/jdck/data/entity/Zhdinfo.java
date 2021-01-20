package com.jdck.data.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name="Zhdinfo")
public class Zhdinfo {
    private String tybh;
    @Id
    private String dfbh;

    private Integer ucode;

    private String ywcode;

    private String zname;

    private String province;

    private String city;

    private String county;

    private String village;

    private String addr;

    private Double x;

    private Double y;

    private String apdate;

    private Integer dpeople;

    private Integer tpeople;

    private Double zjlost;

    private Double tmoney;

    private String wdx;

    private String gm;

    private String zq;

    private String xq;

    private String ztype;

    private String reperson;

    private Integer quanjing;

    private String dzgk;

    private String sbfbt;

    private String hdfx;

    private Integer wxzh;

    private Integer wxfw;

    private String wxdx;

    private String zbxx;

    private String fzcs;

    private String zjly;

    private Double zjed;

    private String fzlb;

    private String jsdw;

    private String jldw;

    private String sgdw;

    private String jd;

    private String ztxz;

    private Double qdrcz;

    private Integer jcdexist;

    private Integer verifyuserid;

    private Date verifytime;

    private Integer verifystatus;

    private Integer isqcqf;


}