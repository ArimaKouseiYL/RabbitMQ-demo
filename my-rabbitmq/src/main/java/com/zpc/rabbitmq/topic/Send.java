package com.zpc.rabbitmq.topic;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import com.rabbitmq.client.ReturnListener;
import com.zpc.rabbitmq.util.ConnectionUtil;

import java.io.IOException;

public class Send {

    private final static String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] argv) throws Exception {

        System.out.println("-------------- 启动 -------------- ");

        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        // 消息内容
        String message = "Hello World!!";

        //设置mandatory为true时，当exchange匹配不到队列时，就会将消息内容返回给生产者
        channel.basicPublish(EXCHANGE_NAME, "routekey.1", true, null, message.getBytes());

        //监听返回的消息
        channel.addReturnListener(new ReturnListener() {
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String returnMes = new String(body);
                System.out.println("返回的消息是： " + returnMes);
            }
        });

        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}