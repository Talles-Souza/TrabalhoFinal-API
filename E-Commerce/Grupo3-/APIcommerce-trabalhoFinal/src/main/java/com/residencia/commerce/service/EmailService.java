package com.residencia.commerce.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.residencia.commerce.entity.ItemPedido;
import com.residencia.commerce.entity.Pedido;
@Service
public class EmailService {
	
	@Autowired private JavaMailSender mailSender;
	
	public String sendMail(Pedido pedido, ItemPedido itemPedido) {
        try {
            MimeMessage mail = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper( mail );
            helper.setTo( "louzadaspedro@gmail.com" );
            helper.setSubject( "Pedido Realizado!" );
            helper.setText("<h1>Seu pedido foi finalizado!</h1>" +  "<br>"
            + "<p>O valor total do pedido foi: " +  itemPedido.getValorBrutoItemPedido() +  "<br>" 
            + "<p>Data do pedido: " + pedido.getDataPedido() +  "<br>"
            + "<p>Data de entrega do pedido: " + pedido.getDataEntregaPedido() +  "<br>"
            + "<p>O seu pedido: " +  itemPedido.getProduto() +  "<br>"
            + "<p>O valor total do pedido foi: " +  itemPedido.getValorBrutoItemPedido() +  "<br>" 
                    , true);
            
            mailSender.send(mail);

            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar e-mail";
        }
    }
	
	
	
	
	
	
	
}
