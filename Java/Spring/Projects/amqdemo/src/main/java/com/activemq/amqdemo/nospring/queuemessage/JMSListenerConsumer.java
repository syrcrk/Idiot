package com.activemq.amqdemo.nospring.queuemessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class JMSListenerConsumer {

    public static void main(String[] args) throws JMSException, IOException, InterruptedException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(JMSProducer.ACTIVEMQ_URL);
        Connection connection = factory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(JMSProducer.QUEUE_NAME);

        MessageConsumer consumer = session.createConsumer(queue);
        session.setMessageListener((tMessage)->{
            try {
                TextMessage tmp =  (TextMessage)tMessage;
                System.out.println(" Lisent "+tmp.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
        connection.start();

        Thread.sleep(10000);
        consumer.close();
        session.close();
        connection.close();
    }
}
