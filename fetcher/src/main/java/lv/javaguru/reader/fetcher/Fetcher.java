package lv.javaguru.reader.fetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileSystemUtils;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.File;


@ComponentScan
@Configuration
@EnableAutoConfiguration
@Controller
@EnableJms
public class Fetcher implements CommandLineRunner {
    @Autowired
    private ApplicationContext context;

    @Autowired
	JmsTemplate jmsTemplate;

//    @Bean
//    ActiveMQConnectionFactory connectionFactory() {
//        return new ActiveMQConnectionFactory();
//    }
//
//    @Bean
//    ActiveMQQueue messageQueue() {
//        return new ActiveMQQueue("myMessageQueue");
//    }
//
//    @Bean
//    JmsTemplate jmsTemplate() {
//        JmsTemplate jt = new JmsTemplate();
//
//        jt.setConnectionFactory(context.getBean(ActiveMQConnectionFactory.class));
//        jt.setDefaultDestination(context.getBean(ActiveMQQueue.class));
//        return jt;
//    }

//    @Bean
//    Receiver receiver() {
//        return new Receiver();
//    }
//
//    @Bean
//    MessageListenerAdapter adapter(Receiver receiver) {
//        MessageListenerAdapter messageListener
//                = new MessageListenerAdapter(receiver);
//        messageListener.setDefaultListenerMethod("receiveMessage");
//        return messageListener;
//    }
//
//    @Bean
//    SimpleMessageListenerContainer container(MessageListenerAdapter messageListener,
//                                             ConnectionFactory connectionFactory) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setMessageListener(messageListener);
//        container.setConnectionFactory(connectionFactory);
//        container.setDestinationName(mailboxDestination);
//        return container;
//    }

//    @Bean
//    public DefaultJmsListenerContainerFactory myContainerFactory() {
//        DefaultJmsListenerContainerFactory factory =
//                new DefaultJmsListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory());
////        factory.setDestinationResolver(destinationResolver());
//        factory.setConcurrency("3-10");
//        return factory;
//    }

//    @Bean DestinationResolver destinationResolver() {
//        return new DynamicDestinationResolver();
//    }
//
//	@Bean
//	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory,
//			DestinationResolver destinationResolver) {
//		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
////		factory.setId("default");
//		factory.setConnectionFactory(connectionFactory);
//		factory.setDestinationResolver(destinationResolver);
//		return factory;
//	}

    @Override
    public void run(String... args) throws Exception {
        // Clean out any ActiveMQ data from a previous run
        FileSystemUtils.deleteRecursively(new File("activemq-data"));

        // Send a message
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("ping!");
            }
        };

//        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

//        jmsTemplate.setDefaultDestinationName("myQueue");
        System.out.println("Sending a new message.");
        jmsTemplate.send("myQueue", messageCreator);
    }

    public static void main(String[] args) {
        SpringApplication.run(Fetcher.class, args);
    }
}