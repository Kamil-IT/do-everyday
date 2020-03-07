package com.doeveryday.doeverydayweb.controller.spotify;

import com.doeveryday.doeverydayspotify.exception.EmptyStringException;
import com.doeveryday.doeverydayspotify.exception.SpotifyApiException;
import com.doeveryday.doeverydayweb.controller.ErrorPagesController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@AllArgsConstructor
@ControllerAdvice
public class ControllerSpotifyExceptionsHandler {

    private ErrorPagesController errorPagesController;

    @ExceptionHandler(NullPointerException.class)
    public ModelAndView handleSpotifyNullPointerException(SpotifyApiException e){
        return errorPagesController.handleErrorBadRequest(e);
    }

    @ExceptionHandler(SpotifyApiException.class)
    public ModelAndView handleSpotifyApiException(SpotifyApiException e){
        return errorPagesController.handleErrorInternalServerError(e);
    }

    @ExceptionHandler(EmptyStringException.class)
    public ModelAndView handleSpotifyEmptyStringException(EmptyStringException e){
        return errorPagesController.handleErrorBadRequest(e);
    }




}
