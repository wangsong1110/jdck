package com.jdck.data.entity;

import javax.persistence.*;
import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Description
 * @Author Hunter
 * @Date 2020-08-06
 */

@Data
@Entity
@Table(name = "STE_RDPM_R")
public class SteRdpmR implements Serializable {

    private static final long serialVersionUID = 627451080032173538L;
    @Id
    @Column(name = "STCD")
    private String stcd;

    @Column(name = "TM")
    private Date tm;

    @Column(name = "RDJ")
    private Double rdj;

    @Column(name = "RDT")
    private Double rdt;

    @Column(name = "MAR")
    private Integer mar;
}
