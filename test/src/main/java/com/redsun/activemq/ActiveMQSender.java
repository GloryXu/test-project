package com.redsun.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class ActiveMQSender {

    Logger logger = LoggerFactory.getLogger(ActiveMQSender.class);

    private String queueTest;

    public String getQueueTest() {
        return queueTest;
    }

    public void setQueueTest(String queueTest) {
        this.queueTest = queueTest;
    }

    public JmsTemplate getJmsQueueTemplate() {
        return jmsQueueTemplate;
    }

    public void setJmsQueueTemplate(JmsTemplate jmsQueueTemplate) {
        this.jmsQueueTemplate = jmsQueueTemplate;
    }

    private JmsTemplate jmsQueueTemplate;

    public void send(String msg) {

        jmsQueueTemplate.send(queueTest, new MessageCreator() {
            public Message createMessage(Session session)
                    throws JMSException {
                return session.createTextMessage(msg);
            }
        });
        logger.info("JMS sent:" + queueTest);

    }
}
