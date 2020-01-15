package com.zpc.rabbitmq.util;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

public class ConnectionUtil {

    public static Connection getConnection() throws Exception {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("192.168.20.131");
        //amqp 协议端口，注意端口
        factory.setPort(5672);
        //设置账号信息，用户名、密码、virtualHost
        //virtualHost必须是该用户下存在的，不然报错
        factory.setVirtualHost("testHost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        // 通过工程获取连接
        Connection connection = factory.newConnection();
        return connection;
    }
}
