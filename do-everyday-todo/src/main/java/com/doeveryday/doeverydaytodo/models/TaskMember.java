package com.doeveryday.doeverydaytodo.models;

import com.doeveryday.doeverydaysecurity.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for keeping Users and connect they to Task Manager
 * It will be useful when log panel will be create
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task_member")
public class TaskMember extends BaseEntity{

    @OneToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUsers;

    @ManyToMany
    @JoinTable(name = "task_member_task_manager",
            joinColumns = @JoinColumn(name = "task_manager_id"),
            inverseJoinColumns = @JoinColumn(name = "task_member_id")
    )
    private List<TaskManager> taskManagers = new ArrayList<>();
}
