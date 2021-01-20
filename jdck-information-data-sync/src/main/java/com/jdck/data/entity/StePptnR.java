package com.jdck.data.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
@Data
@Entity
@Table(name="STE_PPTN_R")
public class StePptnR {
    @Id
    private String stcd;

    private Date tm;

    private Double drp;

    private Double intv;

    private Double dyp;

    private Double curp;

    private Double accp;

    private Byte mar;
}