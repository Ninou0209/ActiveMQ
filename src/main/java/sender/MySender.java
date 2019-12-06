/* IST & CC CLOSSET Marine LENOIR Guillaume */
package sender;

import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.QueueConnectionFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MySender {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try{
			
			/* --------------------------------Topic------------------------------------*/
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			TopicConnectionFactory factoryTopic = (TopicConnectionFactory) applicationContext.getBean("connectionFactory");	
			Topic topic = (Topic) applicationContext.getBean("topic");	
			TopicConnection connection = factoryTopic.createTopicConnection();
			TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			TopicPublisher sender = session.createPublisher(topic);
			Message message = session.createTextMessage("coucou topic");
			sender.send(message);
			session.close();
			connection.close();
			
			/*-------------------------------------Queues-------------------------------------
			QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
			Queue queue = (Queue) applicationContext.getBean("queue");
			QueueConnection connection = factory.createQueueConnection();
			QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueSender sender = session.createSender(queue);
			Message message = session.createTextMessage("coucou");
			sender.send(message);
			session.close();
			connection.close();
			*/
			
		} catch(Exception e) {			
			e.printStackTrace();
		}

	}

}