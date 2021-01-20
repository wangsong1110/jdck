package com.jdck.data.rabbitmq.rabbithandle;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.jdck.data.common.IpUtils;
import com.jdck.data.service.HandleDataService;
import com.jdck.util.SystemConstant;

@Component
public class CurrentDataHandle {
	@Autowired
	private HandleDataService handleDataService;

	@Value("${spring.rabbitmq.listener.queues}")
	private String[] queueNames;

	@Bean
	private String[] queueNames() {
		return queueNames;
	}

	@Bean
	public String[] queNames() {
		String localMac = IpUtils.getLocalMac();
		String[] list = null;
		if (null != queueNames && queueNames.length > 0) {

			list = new String[queueNames.length];

			for (int i = 0; i < queueNames.length; i++) {
				String qname = String.format(SystemConstant.SUBJECT_QUEUE, queueNames[i]) + "-" + localMac;
				list[i] = qname;
			}
		}
		return list;
	}
//
//	@RabbitListener(containerFactory = "mq1ContainerFactory", bindings = @QueueBinding(value = @Queue("#{queue}"), exchange = @Exchange(value = "dataExchange", type = ExchangeTypes.FANOUT, durable = "true", ignoreDeclarationExceptions = "true")))
//	@RabbitHandler
//	private void onMessage(@Payload String message) {
//		System.out.println("有数据：" + message);
//		if (StringUtils.isEmpty(message)) {
//			return;
//		}
//		JSONObject jsonObject = JSONObject.parseObject(message);
//		handleDataService.handleCurrentDate(jsonObject);
//	}

	@RabbitListener(containerFactory = "mq1ContainerFactory", queues = { "#{queNames}" })
	@RabbitHandler
	private void onMessages(@Payload String message) {
		System.out.println("message收到数据：" + message);
		if (StringUtils.isEmpty(message)) {
			return;
		}
		JSONObject jsonObject = JSONObject.parseObject(message);
		handleDataService.handleCurrentDate(jsonObject);
	}
}
