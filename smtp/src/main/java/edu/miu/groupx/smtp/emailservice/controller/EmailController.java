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

    
    @PostMapping("/{email}/{orderNumber}")
    public void sentEmail(@PathVariable("email") String email, @PathVariable("orderNumber") String orderNumber, @RequestBody String template) throws MessagingException{

        emailService.sendEmailWithTemplate(email,orderNumber,template);

    }

}
