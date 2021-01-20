package com.jdck.data.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author zyc
 * @Date 2020/8/5 10:58
 */
@Data
public class PrimaryKey implements Serializable {
    private String stcd;
    private Date tm;
}
