package com.brunette.codefellowship.controllers;


import com.brunette.codefellowship.models.ApplicationUser;
import com.brunette.codefellowship.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private HttpServletRequest request;
    @GetMapping("/")
    public String getHomePage (Principal p, Model m){
        if(p != null){
            String username = p.getName();
            ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
            m.addAttribute("username", username);
        }
        return "index.html";
    }
    @GetMapping("/login")
    public String getLogin(){
        return "login.html";
    }
    @GetMapping("/signup")
    public String getSignup(){
        return "signup.html";
    }
    @PostMapping("/signup")
    public RedirectView createUser(String username, String password, String firstName, String lastName, String dateOfBirth, String bio){
        String hashPW = passwordEncoder.encode(password);
        ApplicationUser newUser = new ApplicationUser(username, hashPW, firstName, lastName, dateOfBirth, bio);
        applicationUserRepository.save(newUser);

        authWithHttpServletRequests(username, password);

        return new RedirectView("/");
    }
    public void authWithHttpServletRequests(String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            System.out.println("Error logging in");
            e.printStackTrace();
        }
    }
}
