package pl.edu.amu.dji.jms.lab2.wholesaler.service;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class OrderService implements MessageListener {

    @Override
    public void onMessage(Message message) {
        if(message instanceof MapMessage){
            MapMessage mapMessage = (MapMessage) message;
            try {
                System.out.print(mapMessage.getDouble("quantity") + " " + mapMessage.getString("retailerID"));
            }
            catch(JMSException e){
                e.printStackTrace();
            }
        }


    }
}
