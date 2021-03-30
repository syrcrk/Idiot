package com.activemq.amqdemo.nospring.queuemessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSConsumer {

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(JMSProducer.ACTIVEMQ_URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(JMSProducer.QUEUE_NAME);

        MessageConsumer consumer = session.createConsumer(queue);
//        while (true) {
//            //TextMessage get = (TextMessage) consumer.receive(4000);
//            TextMessage get = (TextMessage) consumer.receive();
//            if (get != null) {
//                System.out.println("Get Message "+get.getText());
//            } else {
//                break;
//            }
//        }
        System.out.println("Start Get Message ");
        for (int i = 0; i < 4; i++) {
            TextMessage textMessage = (TextMessage)consumer.receive();
            System.out.println("Get Message "+textMessage.getText());
        }

        consumer.close();
        session.close();
        connection.close();
    }
}
