package ar.edu.unju.fi.poo.clinica.service.imp;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import ar.edu.unju.fi.poo.clinica.util.EmailBody;

@Service
public class EmailService {

	@Autowired
	private final JavaMailSender mailSender;
	
	private final TemplateEngine templateEngine;
	
	public EmailService(TemplateEngine templateEngine ,JavaMailSender mailSender) {
		this.mailSender = mailSender;
		this.templateEngine = templateEngine;
	}
			
	/**
	 * EL metodo sendEmail se en carga de enviar los email.
	 * @param emailBody
	 * @param email
	 * @param html
	 * @throws MessagingException
	 */
	public void sendEmail(EmailBody emailBody, String email, String html ) throws MessagingException
	{
		Context context = new Context();
		context.setVariable("emailBody", emailBody);
		
		
		String process = templateEngine.process(html, context);
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		
		helper.setFrom("clinica.poo.grupo7@gmail.com");
		helper.setSubject(emailBody.getSubject());
		helper.setText(process, true);
		helper.setTo(email);
		
		
		mailSender.send(mimeMessage);
			
		
	}
	
	/**
	 * El método enviarCorreos(), enviara el email al paciente y medico. 
	 * @param emailBody
	 * @param html
	 * @throws MessagingException
	 */
	public void enviarCorreos(EmailBody emailBody, String html) throws MessagingException {
		
		sendEmail(emailBody, emailBody.getMailPaciente(), html);
		sendEmail(emailBody, emailBody.getMailMedico(), html);
	}
}
