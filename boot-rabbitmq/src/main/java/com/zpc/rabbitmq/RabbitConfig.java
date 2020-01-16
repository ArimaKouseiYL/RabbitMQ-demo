package com.zpc.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 队列的配置
 */

@Configuration
public class RabbitConfig {
    @Bean
    public Queue queue() {
        return new Queue("q_hello", true);
    }
}
