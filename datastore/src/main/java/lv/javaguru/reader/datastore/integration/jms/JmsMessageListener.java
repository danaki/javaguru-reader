package lv.javaguru.reader.datastore.integration.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(JmsMessageListener.class);

    @JmsListener(destination = "myQueue")
    public void processOrder(String data) {
        System.out.println("Received a message.");
        System.out.println(data);
    }

}