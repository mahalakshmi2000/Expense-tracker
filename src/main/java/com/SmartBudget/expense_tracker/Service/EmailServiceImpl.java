package com.SmartBudget.expense_tracker.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.properties.mail.smtp.from}")
    private String fromEmail;

    public void sendEmail(String toEmail, String subject, String body) {
       try {
           System.out.println("Sending email to: " + toEmail);
           SimpleMailMessage message = new SimpleMailMessage();
           message.setFrom(fromEmail);
           message.setTo(toEmail);
           message.setText(body);
           message.setSubject(subject);
           mailSender.send(message);
       } catch (Exception e) {
          throw new RuntimeException( e.getMessage());
       }
    }

}
