package com.residencia.commerce.controller;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/email-send")
public class EmailController {
	 
	@Autowired private JavaMailSender mailSender;

	@GetMapping
    public String sendMail() {
        try {
            MimeMessage mail = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper( mail );
            helper.setTo( "louzadaspedro@gmail.com" );
            helper.setSubject( "Teste Envio de e-mail" );
            helper.setText("<h1>Seu pedido foi finalizado!</h1>" + "<br>"
                    + "<p>O valor total do pedido foi: </p>", true);
            mailSender.send(mail);

            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar e-mail";
        }
    }
	
	
	
	
	
	
	
	
	
	
	    
	    
}
