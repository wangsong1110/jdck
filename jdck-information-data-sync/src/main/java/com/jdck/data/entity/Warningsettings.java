package com.jdck.data.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Data
@Entity
public class Warningsettings {
    @Id
    private Integer id;

    private String tybh;

    private String eqbh;

    private String datatype;

    private Integer usewarning;

    private Integer warningway;

    private Double warningvalue;

    private Double weight;

    private Double dx;

    private Double dxqz;

    private Double dy;

    private Double dyqz;

    private Double dh;

    private Double dhqz;

    private Double dxy;

    private Double dxyqz;

    private Double dxyh;

    private Double dxyhqz;

    private Double rain30m;

    private Double rain1h;

    private Double rain3h;

    private Double rain6h;

    private Double rain12h;

    private Double rain24h;

    private Double nw0;

    private Double nw1;

    private Integer ylcountYl;

    private Integer ylcountNw;

    private Integer dxstate;

    private Integer ledstate;

    private String lasttime;

    private Integer warnlevel;

    private Boolean dxon;

    private Integer dxsendrecent;

    private Boolean ledon;

    private Integer ledsendrecent;

    private Date lasttimeled;
}