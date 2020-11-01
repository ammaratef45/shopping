package edu.miu.groupx.security.securityservice;

import edu.miu.groupx.security.securityservice.model.SignupBody;
import edu.miu.groupx.security.securityservice.model.User;
import edu.miu.groupx.security.securityservice.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @GetMapping({"/", "/home"})
    String home() {
        return "{home doesn't need authentication}";
    }

    @PostMapping("/signup")
    String signup(@RequestBody SignupBody signupBody) {
        signupBody.setPassword(passwordEncoder.encode(signupBody.getPassword()));
        User user = User.fromSignupBody(signupBody);
        userDetailsService.saveUser(user);
        return "success";
    }
}
