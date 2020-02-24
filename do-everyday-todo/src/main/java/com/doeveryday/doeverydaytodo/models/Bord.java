package com.doeveryday.doeverydaytodo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bord")
public class Bord extends BaseEntity{

    private String name;
    private Color color;

    @OneToMany(mappedBy = "bord")
    List<Task> tasks = new ArrayList<>();
}
