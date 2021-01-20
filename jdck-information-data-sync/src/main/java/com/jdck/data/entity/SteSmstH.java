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
@Table(name = "STE_SMST_H")
@IdClass(PrimaryKey.class)
public class SteSmstH implements Serializable {

    private static final long serialVersionUID = 2032430765273632540L;
    @Id
    @Column(name = "STCD")
    private String stcd;
    @Id
    @Column(name = "TM")
    private Date tm;

    @Column(name = "MT")
    private Double mt;

    @Column(name = "MM")
    private Double mm;

    @Column(name = "MB")
    private Double mb;

    @Column(name = "MAR")
    private Integer mar;

}
