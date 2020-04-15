package com.doeveryday.doeverydaybudgetmanager.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class Budget extends BaseEntity {

    @NotNull
    String name;

    String description;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    List<Transaction> transaction = new ArrayList<>();

}
