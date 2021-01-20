package com.jdck.data.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.jdck.data.common.IpUtils;
import com.jdck.util.SystemConstant;

@Configuration
public class RabbitMqConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Value("${spring.rabbitmq.listener.queues}")
    private String[] queueNames;

    /**
     * @author songzhenghong
     * @version 1.0
     * @date 2019/6/16 10:52
     * Broker:它提供一种传输服务,它的角色就是维护一条从生产者到消费者的路线，保证数据能按照指定的方式进行传输,
     * Exchange：消息交换机,它指定消息按什么规则,路由到哪个队列。 Queue:消息的载体,每个消息都会被投到一个或多个队列。
     * Binding:绑定，它的作用就是把exchange和queue按照路由规则绑定起来. Routing
     * Key:路由关键字,exchange根据这个关键字进行消息投递。
     * vhost:虚拟主机,一个broker里可以有多个vhost，用作不同用户的权限分离。 Producer:消息生产者,就是投递消息的程序.
     * Consumer:消息消费者,就是接受消息的程序. Channel:消息通道,在客户端的每个连接里,可建立多个channel.
     */
    // 声明连接工厂连接开发服务器
    @Primary
    @Bean(name = "mq1ConnectionFactory")
    public ConnectionFactory mq1ConnectionFactory(@Value("${spring.rabbitmq.mq1.host}") String host,
                                                  @Value("${spring.rabbitmq.mq1.port}") Integer port,
                                                  @Value("${spring.rabbitmq.mq1.username}") String username,
                                                  @Value("${spring.rabbitmq.mq1.password}") String password) {
        // 使用@Value直接读取配置文件中的信息
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        return connectionFactory;
    }

    // 声明mq1d的rabbitTemplate
    @Bean(name = "mq1RabbitTemplate")
    @Primary
    public RabbitTemplate mq1RabbitTemplate(@Qualifier("mq1ConnectionFactory") ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }

    @Bean(name = "mq1ContainerFactory")
    public SimpleRabbitListenerContainerFactory mq1SimpleRabbitListenerContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer rabbitListenerContainerFactoryConfigurer,
            @Qualifier("mq1ConnectionFactory") ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory containerFactory = new SimpleRabbitListenerContainerFactory();
        rabbitListenerContainerFactoryConfigurer.configure(containerFactory, connectionFactory);
        return containerFactory;
    }

    @Bean(name = "mq1RabbitAdmin")
    @Primary
    public RabbitAdmin mq1RabbitAdmin(@Qualifier("mq1ConnectionFactory") ConnectionFactory connectionFactory) {

        return new RabbitAdmin(connectionFactory);
    }

    // 创建A队列消息（广播模式类型交换机）
//	@Bean
//	public FanoutExchange appExchange() {
//		return new FanoutExchange("dataExchange");
//	}

    @Bean
    public Object appExchange1(@Qualifier("mq1RabbitAdmin") RabbitAdmin rabbitAdmin) {
        if (null != queueNames && queueNames.length > 0) {
            Queue queue = null;
            FanoutExchange exchange = null;
            for (int i = 0; i < queueNames.length; i++) {
                queue = new Queue(String.format(SystemConstant.SUBJECT_QUEUE, queueNames[i]) + "-" + IpUtils.getLocalMac());
                exchange = new FanoutExchange(String.format(SystemConstant.SUBJECT_EXCHANGE, queueNames[i]));
                rabbitAdmin.declareQueue(queue);
                rabbitAdmin.declareExchange(exchange);
                rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange));
            }
        }
        return null;
    }
}
