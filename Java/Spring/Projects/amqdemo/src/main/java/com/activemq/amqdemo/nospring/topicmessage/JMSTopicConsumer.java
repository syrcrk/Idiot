package com.activemq.amqdemo.nospring.topicmessage;

import com.activemq.amqdemo.nospring.queuemessage.JMSProducer;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSTopicConsumer {

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(JMSTopicProducer.ACTIVEMQ_URL);
        Connection connection = factory.createConnection();
        //持久化必须
        connection.setClientID("myID");
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(JMSTopicProducer.QUEUE_NAME);
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic,"remark");
        while (true){
            TextMessage textMessage = (TextMessage)topicSubscriber.receive();
            if(textMessage!=null){
                System.out.println("Get Message "+textMessage.getText());
            }else{
                break;
            }
        }
        topicSubscriber.close();
        session.close();
        connection.close();
    }
}
