package com.jdck.data.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "STE_DDPM_H")
@IdClass(PrimaryKey.class)
public class SteDdpmH {

	@Id
	private String stcd;

	@Id
	private Date tm;

	private Integer djt;

	private Integer dtt;

	private Integer djm;

	private Integer dtm;

	private Integer djb;

	private Integer dtb;

	private Byte mar;

	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd == null ? null : stcd.trim();
	}

	public Date getTm() {
		return tm;
	}

	public void setTm(Date tm) {
		this.tm = tm;
	}

	public Integer getDjt() {
		return djt;
	}

	public void setDjt(Integer djt) {
		this.djt = djt;
	}

	public Integer getDtt() {
		return dtt;
	}

	public void setDtt(Integer dtt) {
		this.dtt = dtt;
	}

	public Integer getDjm() {
		return djm;
	}

	public void setDjm(Integer djm) {
		this.djm = djm;
	}

	public Integer getDtm() {
		return dtm;
	}

	public void setDtm(Integer dtm) {
		this.dtm = dtm;
	}

	public Integer getDjb() {
		return djb;
	}

	public void setDjb(Integer djb) {
		this.djb = djb;
	}

	public Integer getDtb() {
		return dtb;
	}

	public void setDtb(Integer dtb) {
		this.dtb = dtb;
	}

	public Byte getMar() {
		return mar;
	}

	public void setMar(Byte mar) {
		this.mar = mar;
	}
}