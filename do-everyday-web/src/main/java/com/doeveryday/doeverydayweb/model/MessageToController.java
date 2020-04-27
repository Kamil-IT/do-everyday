package com.doeveryday.doeverydayweb.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageToController {
    private String message;
    private BootstrapAlert alert;
}
