package com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.mail.MessagingException;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        MailService mailService = context.getBean(MailService.class);
        try {
            for (int i = 0; i < 200; i++){
                mailService.send("", "sub", Integer.toString(i));
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
