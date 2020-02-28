package com.doeveryday.doeverydaytodo.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
/*
Class for checking - is done task
 */
@Builder
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task_manager")
public class TaskManager extends BaseEntity{

    @OneToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(name = "is_done")
    private boolean isDone;

    @ManyToMany(mappedBy = "taskManagers")
    private List<TaskMember> taskMember = new ArrayList<>();
}
