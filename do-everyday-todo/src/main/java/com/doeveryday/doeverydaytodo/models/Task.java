package com.doeveryday.doeverydaytodo.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Task extends BaseEntity{

    private String name;
    private String description;

    @Lob
    private Byte[] photo;
}
