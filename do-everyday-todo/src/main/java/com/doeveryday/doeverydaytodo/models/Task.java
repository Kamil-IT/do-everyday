package com.doeveryday.doeverydaytodo.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "task")
public class Task extends BaseEntity{

    private String name;
    private String description;

    @Column(name = "create_date")
    private Date createDate;

    @Lob
    private Byte[] photo;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToOne(mappedBy = "task", cascade = CascadeType.ALL)
    private TaskManager taskManager;
}
