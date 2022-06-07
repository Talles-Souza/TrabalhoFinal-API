package com.residencia.commerce.service;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	JavaMailSender emailSender;

	@Value("${spring.mail.host}")
	private String mailHost;

	@Value("${spring.mail.port}")
	private int mailPort;

	@Value("${spring.mail.username}")
	private String mailUserName;

	@Value("${spring.mail.password}")
	private String mailPassword;

	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		Properties prop = new Properties();
		mailSender.setHost(mailHost);
		mailSender.setPort(mailPort);
		mailSender.setUsername(mailUserName);
		mailSender.setPassword(mailPassword);
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", true);
		mailSender.setJavaMailProperties(prop);
		return mailSender;
	}

	public EmailService(JavaMailSender javaMailSender) {
		this.emailSender = javaMailSender;
	}

	public void enviarEmailTexto(String destinatarioEmail, String assunto, String mensagemEmail) {
		SimpleMailMessage sMailMessage = new SimpleMailMessage();
		sMailMessage.setTo(destinatarioEmail);
		sMailMessage.setSubject(assunto);
		sMailMessage.setText(mensagemEmail);
		// cuidado no momento de usar um servidor real, para setar um remetente valido
		// abaixo
		sMailMessage.setFrom("teste@teste.com");
		emailSender.send(sMailMessage);
	}
    
	//COLOCAR ESSE MÉTODO PARA RODAR, TEMOS ATE AMANHA AS 17;30
	public void enviarEmailHTML(String destinoEmail, String assunto, String msgEmail) throws MessagingException {
		MimeMessage mimeMessage = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		helper.setTo(destinoEmail);
		helper.setSubject(assunto);
		helper.setText(msgEmail, true);
		helper.setFrom("teste@teste.com");
		emailSender.send(mimeMessage);
	}
}
