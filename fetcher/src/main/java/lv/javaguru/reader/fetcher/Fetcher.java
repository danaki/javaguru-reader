package lv.javaguru.reader.fetcher;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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

    @JmsListener(destination = "fetcherInput")
    @SendTo("fetcherOutput")
    public FeedDataMessage processOrder(String msg) {
        // order processing
        System.out.println("pong");

        try {
            URL inputUrl = new URL(msg);

            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(inputUrl));
            System.out.println("Sending data");

            return new FeedDataMessage(msg, feed);
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        } catch (FeedException e) {
        }

        return null;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Fetcher.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Clean out any ActiveMQ data from a previous run
//        FileSystemUtils.deleteRecursively(new File("activemq-data"));
//
//        final SyndFeed feed;
//
//        String url = args[0];
//        URL inputUrl = new URL(url);
//
//        SyndFeedInput input = new SyndFeedInput();
//        feed = input.build(new XmlReader(inputUrl));
//
//        System.out.println("Sending a new message.");
//        sendFeed(url, feed);

        FakeTask fakeTask = (FakeTask) context.getBean("receiver");
        fakeTask.start();

//        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

//        jmsTemplate.setDefaultDestinationName("myQueue");

    }

    private void sendFeed(String url, final SyndFeed feed)
    {
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage msg = session.createObjectMessage();
                msg.setObject(new FeedDataMessage(url, feed));
                return msg;
            }
        };

        jmsTemplate.send("myQueue", messageCreator);
    }
}