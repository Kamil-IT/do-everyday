package com.doeveryday.doeverydaytodo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
Board is for keeping tasks in same category
 */
@Slf4j
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "board")
public class Board extends BaseEntity{

    private String name;

    /**
     * If color will be not in hex schema, it will be set default to #282828
     */
    @Column(name = "color_hex")
    private String colorHex;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    List<Task> tasks = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if(colorHex == null || colorHex.length() != 7){
            colorHex = "#282828";
        }
        else if (colorHex.charAt(0) != '#'){
            colorHex = "#282828";
        }
    }
}
