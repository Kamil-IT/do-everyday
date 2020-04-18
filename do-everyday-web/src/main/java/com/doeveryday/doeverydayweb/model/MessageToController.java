package com.doeveryday.doeverydayweb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageToController {
    private String message;
    private BootstrapAlert alert;
}
