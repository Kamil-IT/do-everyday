package com.doeveryday.doeverydaytodoapi.controller.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ParserObjectToString {
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
