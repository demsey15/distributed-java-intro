package pl.edu.amu.dji.jms.lab2.retailer.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

public class BuyService implements MessageListener {

    private JmsTemplate jmsTemplate;

    private Double maxPrice;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public void onMessage(Message message) {
        if(message instanceof MapMessage){
            MapMessage message1 = (MapMessage) message;
            try {
                Double price = message1.getDouble("price");
                if(price.compareTo(maxPrice) == 1){
                    jmsTemplate.send(message.getJMSDestination(), new MessageCreator() {
                        @Override
                        public Message createMessage(Session session) throws JMSException {
                            MapMessage message = session.createMapMessage();
                            message.setString("retailerID", getClass().getName());
                            message.setDouble("quantity", 10);
                            return message;
                        }
                    });
                }
            }
            catch(JMSException e){
                e.printStackTrace();
            }

        }
    }
}
