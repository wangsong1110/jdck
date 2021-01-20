package com.jdck.data.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

/**
 * @Description
 * @Author zyc
 * @Date 2020/8/5 10:18
 */
@Data
@Entity
@Table(name = "STE_ADPM_H")
@IdClass(PrimaryKey.class)
public class SteAdpmH {
    @Id
    private String stcd;
    @Id
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
