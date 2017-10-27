package com.redsun.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActiveMQListener implements MessageListener {

    Logger logger = LoggerFactory.getLogger(ActiveMQListener.class);

    private ConnectionFactory jmsConnectionFactory;

    public ConnectionFactory getJmsConnectionFactory() {
        return jmsConnectionFactory;
    }

    public void setJmsConnectionFactory(ConnectionFactory jmsConnectionFactory) {
        this.jmsConnectionFactory = jmsConnectionFactory;
    }

    private String queueTest;

    public String getQueueTest() {
        return queueTest;
    }

    public void setQueueTest(String queueTest) {
        this.queueTest = queueTest;
    }

    @Override
    public void onMessage(Message message) {
        logger.info("收到的消息为:" + message.toString());

    }

    public void init() {
        try {
            Connection connect = jmsConnectionFactory.createConnection();
            connect.start();
            Session session = connect.createSession(false, Session.AUTO_ACKNOWLEDGE);

            session.createConsumer(session.createQueue(queueTest)).setMessageListener(this);
            logger.info("创建消费者成功,订阅队列:" + queueTest);
            logger.trace("this is trace!");
        } catch (Exception e) {
            e.printStackTrace();
            logger.trace("JMS消费者创建失败", e);
        }
    }
}
