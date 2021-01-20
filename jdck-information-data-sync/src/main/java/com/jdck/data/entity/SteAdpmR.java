package com.jdck.data.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @Description
 * @Author zyc
 * @Date 2020/8/5 10:22
 */
@Data
@Entity
@Table(name = "STE_ADPM_R")
public class SteAdpmR {
    @Id
    private String stcd;
    private Date tm;
    private Double d;
    private Double ns;
    private Double we;
    private Double h;
    private Double dd;
    private BigDecimal nsd;
    private BigDecimal wed;
    private BigDecimal hd;
    private Double MAR;
}
