package com.jdck.data.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Data
@Entity
@Table(name="Eqinfo")
public class Eqinfo {
    @Id
    private String eqbh;

    private String ename;

    private String tybh;

    private String jcdbh;

    private String etype;

    private String ywbh;

    private String eid;

    private String tdcode;

    private String tmission;

    private String simid;

    private String beidou;

    private Double x;

    private Double y;

    private String altitude;

    private String inlocation;

    private String wlevel;

    private Date wtime;

    private String estate;

    private Date lastrecivetime;

    private Boolean oneday;

    private Boolean twoday;

    private Boolean threeday;

    private String dcause;

    private Double bzswz;

    private String gkposition;

    private String ptype;

    private String pinfo;

    private String gsignal;

    private String g3signal;

    private String bnorms;

    private String cinfo;

    private String lithology;

    private String lighting;

    private String signal;

    private String winfo;

    private Date cdate;

    private Date pdate;

    private String cperson;

    private String ckperson;

    private String aperson;

    private String surface;

    private Boolean pole;

    private Boolean solar;

    private Boolean acquisition;

    private Boolean transmission;

    private Date instaldate;

    private Date completedate;

    private String inperson;

    private String inckperson;

    private String inaperson;

    private String hno;

    private String inunit;

    private Double maxvalue;

    private Double minvalue;

    private Double bzpower;

    private Double curpower;

    private Double cgqms;

    private Byte issgbj;

    private String remark;
}