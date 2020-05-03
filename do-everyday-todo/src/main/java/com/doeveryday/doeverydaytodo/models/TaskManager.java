package com.doeveryday.doeverydaytodo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Class for keeping information about - is task done
 */
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
    private boolean isDone = false;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToMany(mappedBy = "taskManagers")
    private List<TaskMember> taskMember = new ArrayList<>();

    @PrePersist
    private void checkPriority(){
        if (priority == null){
            priority = Priority.NORMAL;
        }
    }
}
