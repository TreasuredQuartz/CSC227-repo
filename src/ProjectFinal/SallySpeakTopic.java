package ProjectFinal;

import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(name = "SallySpeakTopic", activationConfig= {
        @ActivationConfigProperty(propertyName = "destinationType", propertyType = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destination", propertyType = "topic/SALLYSPEAKTopic"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyType = "Auto-acknowledge")
})
public class SallySpeakTopic implements MessageListener {

    private static final Logger LOGGER = Logger.getLogger(SallySpeaksTopic.class.toString());

    public void onMessage(Message rcvMessage) {
        textMessage msg = null;
        try {
            if (rcvMessage instanceof TextMessage) {
                msg = (TextMessage) rcvMessage;
                LOGGER.info("Received Message from topic: " + msg.getText());
            } else {
                LOGGER.warning("Message of wrong type: " + rcvMessage.getClass().getName());
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

}
