package com.doeveryday.doeverydayweb.controller.user;

import com.doeveryday.doeverydaysecurity.model.AppUser;
import com.doeveryday.doeverydaysecurity.service.AppUserService;
import javassist.NotFoundException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping({"", "/"})
    public String createNewUser(AppUser user){
        user.setDefaultValueToWorkAccount();
        user.setRole(USER);
        appUserService.saveUser(user);
        return "redirect:/login";
    }

    @PreAuthorize("hasAnyAuthority('user:details:get', 'user:details:getcreator')")
    @PostMapping("/image")
    public String addOrChangeImage(Principal principal, @RequestParam("imagefile") MultipartFile file) throws NotFoundException {
        if (file.isEmpty()){
            return "redirect:/user/details";
        }
        appUserService.addImage(appUserService.findByUsername(principal.getName()).getId(), file);
        return "redirect:/user/details";
    }

    @PreAuthorize("hasAnyAuthority('user:details:get', 'user:details:getcreator')")
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


}
