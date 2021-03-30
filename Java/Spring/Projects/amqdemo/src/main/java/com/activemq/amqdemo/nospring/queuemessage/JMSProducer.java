package com.activemq.amqdemo.nospring.queuemessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSProducer {
    public static final String ACTIVEMQ_URL="tcp://192.168.1.3:61616";
    public static final String QUEUE_NAME="GIFT";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);

        MessageProducer producer = session.createProducer(queue);

        for (int i = 0; i < 4; i++) {
            TextMessage textMessage = session.createTextMessage("msg ->" + i);
            producer.send(textMessage);
        }
        producer.close();
        session.close();
        connection.close();
    }
}
