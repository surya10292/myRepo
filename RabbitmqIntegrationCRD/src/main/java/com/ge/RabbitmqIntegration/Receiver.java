package com.ge.RabbitmqIntegration;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receiver {
    private static final String QUEUE_NAME = "RabbitMQ_Queue";

    public static void getMessage() throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(System.getenv("SECRET_USERNAME"));
        factory.setPassword(System.getenv("SECRET_PASSWORD"));
        factory.setVirtualHost(System.getenv("SECRET_VHOST"));
        factory.setHost(System.getenv("SECRET_HOST"));
        factory.setPort(Integer.parseInt(System.getenv("SECRET_PORT")));
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        
        // explicit request for messages to arrive
        GetResponse response = channel.basicGet(QUEUE_NAME, true);
        if (response != null) {
            String message = new String(response.getBody(), "UTF-8");
            System.out.println("Received " + message);
        }
        
        // No message retrieved.
        if (response == null) {
            System.out.println("No message retrieved");
        }
        
        channel.close();
        connection.close();



    }

}
