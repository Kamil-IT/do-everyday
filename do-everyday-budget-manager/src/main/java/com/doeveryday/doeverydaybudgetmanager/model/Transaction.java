package com.doeveryday.doeverydaybudgetmanager.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Entity
public class Transaction extends BaseEntity{

    /*
    It can have value on minus
     */
    @NotNull
    Double value;

    String description;

    @Enumerated(EnumType.STRING)
    Currency currency;

    Date date;

    @JoinColumn(name = "budget_id")
    @ManyToOne
    Budget budget;

}
