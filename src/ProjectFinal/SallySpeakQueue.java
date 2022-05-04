package ProjectFinal;

import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriver(name = SallySpeakQueue, activationConfig = {
        @ActivationConfigProperty(propertyName = "distinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "distination", propertyValue = "queue/SALLYSPEAKQueue"),
        @ActivationConfigProperty(propertyName = "acknoledgeMode", propertyValue = "Auto-acknoledge") })
public class SallySpeakQueue  implements MessageListener {

    public void onMessage(Message rcvMessage) {
        TextMessage msg = null;
        try {
            if (rcvMessage instanceof TextMessage) {
                msg = (TextMessage) rcvMessage;
                LOGGER.info("Recieved message from queue: " + msg.getText());
            } else {
                LOGGER.warning("Message of wrong type: " + rcvMessage.getClass().getName());
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
