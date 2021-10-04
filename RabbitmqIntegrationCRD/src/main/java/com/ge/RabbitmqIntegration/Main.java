package com.ge.RabbitmqIntegration;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
public class Main {
    public static void main(String args[]) {
        try {
            testConnection();
            if (System.getenv("type").equals("Publisher")) {
                RMQPublisher.sendMessage();
            }
            if (System.getenv("type").equals("Receiver")) {
                Receiver.getMessage();
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static boolean testConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        try {
            factory.setUsername(System.getenv("SECRET_USERNAME"));
            factory.setPassword(System.getenv("SECRET_PASSWORD"));
            factory.setVirtualHost(System.getenv("SECRET_VHOST"));
            factory.setHost(System.getenv("SECRET_HOST"));
            factory.setPort(Integer.parseInt(System.getenv("SECRET_PORT")));
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.close();
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }

        System.out.println("RabbitMQ Connection Successful.\n");
        return false;
    }


}

