package com.doeveryday.doeverydayweb.controller.user;

import com.doeveryday.doeverydaysecurity.model.AppUser;
import com.doeveryday.doeverydaysecurity.service.AppUserService;
import com.doeveryday.doeverydayweb.model.BootstrapAlert;
import com.doeveryday.doeverydayweb.model.MessageToController;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.*;
import java.security.Principal;

import static com.doeveryday.doeverydaysecurity.model.AppUserRole.USER;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    public static final String USER_DETAILS_GET_AND_GET_CREATOR = "'read:admin::details', 'read::details'";
    public static final MessageToController SUCCESSFUL_UPDATE_MESSAGE =
            new MessageToController("Update was successful", BootstrapAlert.SUCCESS);
    public static final String PATH_DEFAULT_USER_PHOTO =
            "do-everyday-web/src/main/resources/static/resources/user/images/no_photo_user.png";
    public static final String BUSY_USERNAME_MESSAGE = "This username is already busy";
    private final AppUserService appUserService;
    private final PasswordEncoder passwordEncoder;

    public UserController(@Qualifier("appUserServiceImpl") AppUserService appUserService,
                          PasswordEncoder passwordEncoder) {
        this.appUserService = appUserService;
        this.passwordEncoder = passwordEncoder;
    }

//    Create user

    @PostMapping({"", "/"})
    public String createNewUser(AppUser user) {
        String username = user.getUsername();
        if (appUserService.UsernameExist(username)){
            return "redirect:/signup?faildmessage=" + BUSY_USERNAME_MESSAGE;
        }
        else if(username.isBlank()){
            return "redirect:/signup?faildmessage=" + "Username cannot be blanck";
        }
        else if(user.getPassword().isBlank()){
            return "redirect:/signup?faildmessage=" + "Password cannot be blanck";
        }
        user.setDefaultValueToWorkAccount();
        user.setRole(USER);
        appUserService.saveUser(user);
        return "redirect:/login";
    }

//    Getting or Update existing user

    @PreAuthorize("hasAnyAuthority(" + USER_DETAILS_GET_AND_GET_CREATOR + ")")
    @GetMapping("/details")
    public String getUserDetails(Principal principal,
                                 Model model,
                                 @PathParam("success") boolean success,
                                 @PathParam("messagefailed") String messageFailed) throws NotFoundException {
        if (success) {
            model.addAttribute("message", SUCCESSFUL_UPDATE_MESSAGE);
        }
        else if (messageFailed != null){
            model.addAttribute("message",
                    MessageToController.builder()
                            .message(messageFailed)
                            .alert(BootstrapAlert.DANGER).build());
        }
        model.addAttribute("user", appUserService.findByUsername(principal.getName()));

        return "user/details";
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @PreAuthorize("hasAnyAuthority(" + USER_DETAILS_GET_AND_GET_CREATOR + ")")
    public String getUserDetailsUpdateFailed(Principal principal,
                                             Model model,
                                             IllegalArgumentException e) throws NotFoundException {
        model.addAttribute("message", new MessageToController(e.getMessage(), BootstrapAlert.DANGER));
        model.addAttribute("user", appUserService.findByUsername(principal.getName()));

        return "user/details";
    }

    @PreAuthorize("hasAnyAuthority(" + USER_DETAILS_GET_AND_GET_CREATOR + ")")
    @PostMapping("/image")
    public String addOrChangeImage(Principal principal,
                                   @RequestParam("imagefile") MultipartFile file) throws NotFoundException {
        if (file.isEmpty()) {
            return "redirect:/user/details";
        }
        appUserService.addImage(appUserService.findByUsername(principal.getName()).getId(), file);
        return "redirect:/user/details";
    }

    @PreAuthorize("hasAnyAuthority(" + USER_DETAILS_GET_AND_GET_CREATOR + ")")
    @GetMapping("/details/image")
    public void renderImage(Principal principal, HttpServletResponse response) throws IOException, NotFoundException {
        AppUser user = appUserService.findByUsername(principal.getName());
        response.setContentType("image/jpeg");
        InputStream inputStream;

        if (user.getPhoto() != null) {
            byte[] byteArray = new byte[user.getPhoto().length];
            int i = 0;

            for (Byte wrappedByte : user.getPhoto()) {
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            inputStream = new ByteArrayInputStream(byteArray);

        }
        else {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            ImageIO.write(
                    ImageIO.read(
                            new File(PATH_DEFAULT_USER_PHOTO)),
                    "png",
                    output);
            byte [] byteArray = new byte[output.toByteArray().length];
            int i = 0;
            for (Byte wrappedByte: output.toByteArray()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            inputStream = new ByteArrayInputStream(byteArray);
        }

        IOUtils.copy(inputStream, response.getOutputStream());
    }

    @PreAuthorize("hasAnyAuthority(" + USER_DETAILS_GET_AND_GET_CREATOR + ")")
    @PostMapping("/username")
    public String updateUsername(String username, Principal principal) throws NotFoundException {
        if (appUserService.UsernameExist(username)) {
            throw new IllegalArgumentException(BUSY_USERNAME_MESSAGE);
        }
        AppUser user = appUserService.findByUsername(principal.getName());

        user.setUsername(username);

        appUserService.updateUsername(
                appUserService.findByUsername(principal.getName()).getId(),
                username);

        return "redirect:/logout";
    }

    @PreAuthorize("hasAnyAuthority(" + USER_DETAILS_GET_AND_GET_CREATOR + ")")
    @PostMapping("/password")
    public String updatePassword(String password, Principal principal) throws NotFoundException {
        AppUser user = appUserService.findById(appUserService.findByUsername(principal.getName()).getId());
        user.setPassword(password);
        appUserService.updateUser(user);
        return "redirect:/user/details?success=true";
    }

    @PreAuthorize("hasAnyAuthority(" + USER_DETAILS_GET_AND_GET_CREATOR + ")")
    @PostMapping("/email")
    public String updateEmail(String email, Principal principal) throws NotFoundException {
        AppUser user = appUserService.findByUsername(principal.getName());
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email must have '@'");
        }
        user.setEmail(email);
        appUserService.updateUser(user);
        return "redirect:/user/details?success=true";
    }

    @ExceptionHandler(IOException.class)
    public void IOExceptionHandler(IOException e){
        log.error(e.getMessage());
    }
}
