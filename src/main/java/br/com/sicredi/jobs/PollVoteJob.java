package br.com.sicredi.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Componente de envio e recebimento de mensagens jms
 * Para fins de teste, esse componente envia e recebe a mensagem
 * @author lucaskoch
 *
 */
@Component
public class PollVoteJob {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	
	public  void sendMessage(String message) {
		
		jmsTemplate.convertAndSend("queue.poll", message);
		
	}
	
	@JmsListener(destination = "queue.poll")
	public void onReceiverQueue(String message) {

		System.out.println( "Recebendo mensagem : " + message );
	}
	
}
