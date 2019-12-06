/* IST & CC CLOSSET Marine LENOIR Guillaume */
package receiver;

import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyReceiver {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try{

			/* --------------------------------topic------------------------------------*/
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			TopicConnectionFactory factoryTopic = (TopicConnectionFactory) applicationContext.getBean("connectionFactory");
			Topic topic = (Topic) applicationContext.getBean("topic");
			TopicConnection connection = factoryTopic.createTopicConnection();
			TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			TopicSubscriber receiver = session.createSubscriber(topic);
			connection.start();
			Message message = receiver.receive();
			System.out.println(message);
			
			/*-------------------------------------queues-------------------------------------
			 * 	
			 QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
	 		 Queue queue = (Queue) applicationContext.getBean("queue");
			 QueueConnection connection = factory.createQueueConnection();
			 QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			 connection.start();
			 QueueReceiver receiver = session.createReceiver(queue);
			 Message message = receiver.receive();
			 System.out.println(message); */ 
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		

}		
