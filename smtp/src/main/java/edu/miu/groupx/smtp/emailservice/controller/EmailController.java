package edu.miu.groupx.smtp.emailservice.controller;


import edu.miu.groupx.smtp.emailservice.service.EmailService;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/smtp")
public class EmailController {

    @Autowired
    private EmailService emailService;

    
    @GetMapping
    public void getOrders() throws MessagingException{
        emailService.sendEmail("nvu135@gmail.com");
    }
    
    @PostMapping("/{email}")
    public void sentEmail(@PathVariable("email") String email, @RequestBody String template) throws MessagingException{

        emailService.sendEmail(email);

    }

}
