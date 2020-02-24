package com.doeveryday.doeverydaytodo.models;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "task")
public class Task extends BaseEntity{

    private String name;
    private String description;

    @Lob
    private Byte[] photo;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToOne(mappedBy = "task")
    private TaskManager taskManager;
}
