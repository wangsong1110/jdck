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
 * @Author  Hunter
 * @Date 2020-08-06 
 */

@Data
@Entity
@Table ( name ="STE_WTLV_H" )
@IdClass(PrimaryKey.class)
public class SteWtlvH implements Serializable {

	private static final long serialVersionUID =  1875361451388848929L;

   	@Column(name = "STCD" )
	@Id
	private String stcd;

   	@Column(name = "TM" )
	@Id
	private Date tm;

   	@Column(name = "ZM" )
	private Double zm;

   	@Column(name = "ZK" )
	private Double zk;

   	@Column(name = "Z" )
	private Double Z;

   	@Column(name = "MAR" )
	private Integer mar;

}
