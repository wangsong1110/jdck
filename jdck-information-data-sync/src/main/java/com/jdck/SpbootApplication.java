package com.jdck;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EntityScan(basePackages = {"com.jdck.data.entity","com.jdck.data.otherEntity"})//扫描实体类
@MapperScan(basePackages={"com.jdck.data.dao"})
@EnableScheduling
@EnableConfigurationProperties
public class SpbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpbootApplication.class, args);
	}
}
