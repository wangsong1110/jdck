package com.jdck.data.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

/**
 * @Description
 * @Author zyc
 * @Date 2020/8/5 10:18
 */
@Data
@Entity
@Table(name = "STE_DPAE_H")
@IdClass(PrimaryKey.class)
public class SteDpaeH  implements Serializable{
	// 站号
	@Id
	private String stcd;
	@Id
	private Date tm;
	// X向角度变化量，累计变化量（0-360，正北向为0，顺时针增加）
	private Double x;
	// Y向角度变化量，累计变量（0-360，正北向为0，顺时针增加）
	private Double y;
	// Z向角度变化量，累计变量（0-360，正北向为0，顺时针增加）
	private Double z;
	// 合方向上倾斜角度
	private Double tiltangle;
	// 倾斜方向，0-360°
	private Double tiltdirection;
	
	private Date createtime;
}
