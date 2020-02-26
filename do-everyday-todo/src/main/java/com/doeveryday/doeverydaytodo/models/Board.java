package com.doeveryday.doeverydaytodo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "board")
public class Board extends BaseEntity{

    private String name;

    @Column(name = "color_name")
    private String colorName;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    List<Task> tasks = new ArrayList<>();
}
