package com.jdck.data.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * (SteStssR)实体类
 *
 * @author makejava
 * @since 2020-08-13 10:52:27
 */
@Data
@Entity
@Table(name="STE_STSS_R")
public class SteStssR implements Serializable {
	private static final long serialVersionUID = -31380400017215301L;
	@Id
	private String stcd;

	private Date tm;

	private Float vt;

	private Float vs;

	private byte csq;

	private Integer zt;

}