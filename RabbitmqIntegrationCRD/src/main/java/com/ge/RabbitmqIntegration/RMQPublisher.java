package com.ge.RabbitmqIntegration;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RMQPublisher {
    private final static String QUEUE_NAME = "RabbitMQ_Queue";
    public static void sendMessage() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(System.getenv("SECRET_USERNAME"));
        factory.setPassword(System.getenv("SECRET_PASSWORD"));
        factory.setVirtualHost(System.getenv("SECRET_VHOST"));
        factory.setHost(System.getenv("SECRET_HOST"));
        factory.setPort(Integer.parseInt(System.getenv("SECRET_PORT")));
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "test message for RabbitMQ logs";
        
        // To publish a message to a queue, use Channel.basicPublish as follows:
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
        System.out.println("Sent " + message);
        channel.close();
        connection.close();
    }


}
