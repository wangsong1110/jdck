package com.jdck.data.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "STE_PPTN_H")
@IdClass(PrimaryKey.class)
public class StePptnH {
    @Id
    private String stcd;
    @Id
    private Date tm;

    private Double drp;

    private Double intv;

    private Double dyp;

    private Double curp;

    private Double accp;

    private Byte mar;

}