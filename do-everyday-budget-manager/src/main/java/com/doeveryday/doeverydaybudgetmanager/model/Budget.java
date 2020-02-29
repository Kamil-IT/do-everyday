package com.doeveryday.doeverydaybudgetmanager.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class Budget extends BaseEntity {

    @NotNull
    String name;

    String Description;

    @OneToMany
    @JoinColumn(name = "budget_id")
    List<Transaction> transaction = new ArrayList<>();

}
