package com.doeveryday.doeverydayweb.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;

@Slf4j
@Controller
public class AuthorisationController {

    public static final String LOGOUT_SUCCESSFUL_MESSAGE = "Logout successful";
    public static final String FAILED_LOG_IN_MESSAGE = "Incorrect password or username ";

    private final UserDetailsService userDetailsService;

    public AuthorisationController(@Qualifier("appUserServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/login")
    public String getLoginPage(@PathParam("logout") boolean logout,
                               @PathParam("failedlogin") boolean failedlogin,
                               Model model) {
        if (logout) {
            log.error("Welcome in login?logout");
            model.addAttribute("message", LOGOUT_SUCCESSFUL_MESSAGE);
        } else if (failedlogin) {
            log.error("Welcome in login?failedlogin");
            model.addAttribute("message", FAILED_LOG_IN_MESSAGE);
        }
        return "user/loginpage";
    }

    @GetMapping("/recoverypassword")
    public ModelAndView recoveryPassword(){
        return new ModelAndView();
    }


    @GetMapping("/signup")
    public ModelAndView createNewAccount(){
        return new ModelAndView();
    }


}