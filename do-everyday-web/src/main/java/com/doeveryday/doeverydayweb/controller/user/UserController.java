package com.doeveryday.doeverydayweb.controller.user;

import com.doeveryday.doeverydaysecurity.model.AppUser;
import com.doeveryday.doeverydaysecurity.service.AppUserService;
import javassist.NotFoundException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.UUID;

import static com.doeveryday.doeverydaysecurity.model.AppUserRole.USER;


@Controller
@RequestMapping("/user")
public class UserController {

    public static final String USER_DETAILS_GET_AND_GET_CREATOR = "'user:details:get', 'user:details:getcreator'";
    private final AppUserService appUserService;
    private final PasswordEncoder passwordEncoder;

    public UserController(@Qualifier("appUserServiceImpl") AppUserService appUserService, PasswordEncoder passwordEncoder) {
        this.appUserService = appUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @PreAuthorize("hasAnyAuthority(" + USER_DETAILS_GET_AND_GET_CREATOR + ")")
    @GetMapping("/details")
    public String getUserDetails(Principal principal, Model model) throws NotFoundException {
        model.addAttribute("user", appUserService.findByUsername(principal.getName()));
        return "user/details";
    }

    @PostMapping({"", "/"})
    public String createNewUser(AppUser user){
        user.setDefaultValueToWorkAccount();
        user.setRole(USER);
        appUserService.saveUser(user);
        return "redirect:/login";
    }

    @PreAuthorize("hasAnyAuthority(" + USER_DETAILS_GET_AND_GET_CREATOR + ")")
    @PostMapping("/image")
    public String addOrChangeImage(Principal principal, @RequestParam("imagefile") MultipartFile file) throws NotFoundException {
        if (file.isEmpty()){
            return "redirect:/user/details";
        }
        appUserService.addImage(appUserService.findByUsername(principal.getName()).getId(), file);
        return "redirect:/user/details";
    }

    @PreAuthorize("hasAnyAuthority(" + USER_DETAILS_GET_AND_GET_CREATOR + ")")
    @GetMapping("/details/image")
    public void renderImage(Principal principal, HttpServletResponse response) throws IOException, NotFoundException {
        AppUser user = appUserService.findByUsername(principal.getName());

        if (user.getPhoto() != null) {
            byte[] byteArray = new byte[user.getPhoto().length];
            int i = 0;

            for (Byte wrappedByte : user.getPhoto()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }

    @PreAuthorize("hasAnyAuthority(" + USER_DETAILS_GET_AND_GET_CREATOR + ")")
    @PostMapping("/username")
    public String updateUsername(UUID id, String usernameNEW) throws NotFoundException {
        try {
            appUserService.findByUsername(usernameNEW);
        } catch (NotFoundException e) {
            return "redirect:/user/details";
        }
        AppUser user = appUserService.findById(id);

        user.setUsername(usernameNEW);

        appUserService.updateUser(user);

        return "redirect:/user/details";
    }

    @PreAuthorize("hasAnyAuthority(" + USER_DETAILS_GET_AND_GET_CREATOR + ")")
    @PostMapping("/password")
    public String updatePassword(UUID id, String password) throws NotFoundException {
        AppUser user = appUserService.findById(id);
        user.setPassword(password);
        appUserService.updateUser(user);
        return "redirect:/user/details";
    }

    @PreAuthorize("hasAnyAuthority(" + USER_DETAILS_GET_AND_GET_CREATOR + ")")
    @PostMapping("/email")
    public String updateEmail(UUID id, String email) throws NotFoundException {
        AppUser user = appUserService.findById(id);
        if (!email.contains("@")){
            throw new IllegalArgumentException("Email must have '@'");
        }
        user.setEmail(email);
        appUserService.updateUser(user);
        return "redirect:/user/details";
    }


}
