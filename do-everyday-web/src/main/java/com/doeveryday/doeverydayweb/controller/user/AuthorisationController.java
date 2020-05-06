package com.doeveryday.doeverydayweb.controller.user;

import com.doeveryday.doeverydaysecurity.service.AppUserService;
import com.doeveryday.doeverydayweb.model.BootstrapAlert;
import com.doeveryday.doeverydayweb.model.MessageToController;
import lombok.extern.slf4j.Slf4j;
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
    public static final String ACCOUNT_CREATED_MESSAGE = "Your account was created";
    public static final String USERNAME_CHANGED = "After change username you have to login again";

    private final AppUserService appUserService;

    public AuthorisationController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/login")
    public String getLoginPage(@PathParam("logout") boolean logout,
                               @PathParam("failedlogin") boolean failedlogin,
                               @PathParam("newuser") boolean newuser,
                               @PathParam("changeusername") boolean changeusername,
                               Model model) {
        if (logout) {
            model.addAttribute("message", LOGOUT_SUCCESSFUL_MESSAGE);
        } else if (failedlogin) {
            model.addAttribute("message", FAILED_LOG_IN_MESSAGE);
        } else if (newuser){
            model.addAttribute("message", ACCOUNT_CREATED_MESSAGE);
        } else if (changeusername){
            model.addAttribute("message", USERNAME_CHANGED);
        }
        return "user/loginpage";
    }

    @GetMapping("/recoverypassword")
    public ModelAndView recoveryPassword(){
        return new ModelAndView();
    }


    @GetMapping("/signup")
    public String createNewAccount(@PathParam("faildmessage") String faildmessage, Model model){
        if (faildmessage != null){
            model.addAttribute("message", MessageToController.builder()
                    .message(faildmessage)
                    .alert(BootstrapAlert.DANGER).build());
        }
        return "user/adduser";
    }


}