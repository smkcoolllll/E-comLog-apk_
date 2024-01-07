package com.EcomcataLog_.EcomcataLog_.DTO;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String email, String subject, String content) throws MessagingException, jakarta.mail.MessagingException {
        jakarta.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content, true); // Set the content as HTML
        javaMailSender.send(mimeMessage);
    }
}