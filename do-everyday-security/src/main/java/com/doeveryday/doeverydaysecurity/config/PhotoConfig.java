package com.doeveryday.doeverydaysecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class PhotoConfig {

    /**
     * 10485760B = 10MB
     */
    public static final int MAX_UPLOAD_SIZE_BYTES = 10485760;

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver
                = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(MAX_UPLOAD_SIZE_BYTES);
        return multipartResolver;
    }
}
