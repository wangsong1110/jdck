package com.jdck.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Description
 * @Author Hunter
 * @Date 2020-08-06
 */

@Setter
@Getter
@ToString
@Entity
@Table(name = "STE_WTLV_R")
public class SteWtlvR implements Serializable {

    private static final long serialVersionUID = 3833016682168807804L;
    @Id
    @Column(name = "STCD")
    private String stcd;

    @Column(name = "TM")
    private Date tm;

    @Column(name = "ZM")
    private Double zm;

    @Column(name = "ZK")
    private Double zk;

    @Column(name = "Z")
    private Double Z;

    @Column(name = "MAR")
    private Integer mar;

}
