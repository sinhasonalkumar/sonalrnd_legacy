package com.sonal.spring.jms.producer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

   final static Logger logger = Logger.getLogger(MessageProducer.class);

   @Autowired
   private JmsTemplate jmsTemplate;

   public void send(final String msg) throws Exception {
      
      if (jmsTemplate != null) {
         MessageCreator mc = new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
               try {
                  TextMessage message = session.createTextMessage(msg);
                  return message;
               } 
                catch (JMSException je) {
                  logger.error("JMS Exception : ", je);
                  return null;
               }
            }
         };
         jmsTemplate.send(mc);
         /*Message receivedMessage = jmsTemplate.receive();
         System.out.println(receivedMessage.toString());*/
        
      }
   }
}