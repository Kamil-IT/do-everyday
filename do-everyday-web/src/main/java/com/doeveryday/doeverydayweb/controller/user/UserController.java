package com.doeveryday.doeverydayweb.controller.user;

import com.doeveryday.doeverydaysecurity.model.AppUser;
import com.doeveryday.doeverydaysecurity.service.AppUserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import static com.doeveryday.doeverydaysecurity.model.AppUserRole.USER;


@Controller
@RequestMapping("/user")
public class UserController {

    private final AppUserService appUserService;

    public UserController(@Qualifier("appUserServiceImpl") AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PreAuthorize("hasAnyAuthority('user:details:get', 'user:details:getcreator')")
    @GetMapping("/details")
    public String getUserDetails(Principal principal, Model model) throws NotFoundException {
        model.addAttribute("user", appUserService.findByUsername(principal.getName()));
        return "user/details";
    }

    @PreAuthorize("hasAnyAuthority('user:details:get')")
    @GetMapping("/details/{username}")
    public String getUserDetailsById(@PathVariable("username") String username, Model model) throws NotFoundException {
        model.addAttribute("user", appUserService.findByUsername(username));
        return "user/details";
    }

    @PostMapping({"", "/"})
    public String createNewUser(AppUser user){
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setRole(USER);
        appUserService.saveUser(user);
        return "redirect:/login";
    }


}
