package com.jdck.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

/**
 * @Description  
 * @Author  Hunter
 * @Date 2020-08-06 
 */

@Setter
@Getter
@ToString
@Entity
@Table ( name ="STE_SMST_R" )
public class SteSmstR  implements Serializable {

	private static final long serialVersionUID =  2245748633959651114L;
    @Id
   	@Column(name = "STCD" )
	private String stcd;

   	@Column(name = "TM" )
	private Date tm;

   	@Column(name = "MT" )
	private Double mt;

   	@Column(name = "MM" )
	private Double mm;

   	@Column(name = "MB" )
	private Double mb;

   	@Column(name = "MAR" )
	private Integer mar;


}
