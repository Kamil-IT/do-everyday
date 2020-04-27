package com.doeveryday.doeverydayweb.controller.user;

import com.doeveryday.doeverydaysecurity.service.AppUserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserAdminController {

    private final AppUserService appUserService;

    public UserAdminController(@Qualifier("appUserServiceImpl") AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PreAuthorize("hasAnyAuthority('read:admin::details')")
    @GetMapping("/details/{username}")
    public String getUserDetailsById(@PathVariable("username") String username, Model model) throws NotFoundException {
        model.addAttribute("user", appUserService.findByUsername(username));
        return "user/details";
    }
}
