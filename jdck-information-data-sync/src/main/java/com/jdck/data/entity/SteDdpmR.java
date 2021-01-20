package com.jdck.data.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "STE_DDPM_R")
public class SteDdpmR {

	@Id
	private String stcd;

	private Date tm;

	private Integer djt;

	private Integer dtt;

	private Integer djm;

	private Integer dtm;

	private Integer djb;

	private Integer dtb;

	private Byte mar;

}