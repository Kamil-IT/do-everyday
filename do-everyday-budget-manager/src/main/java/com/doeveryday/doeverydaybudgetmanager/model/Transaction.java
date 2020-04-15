package com.doeveryday.doeverydaybudgetmanager.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Entity
public class Transaction extends BaseEntity{

    /**
     * It can have value on minus
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

    @PrePersist
    private void setDefaultValueForData(){
        if (date == null){
            date = new Date();
        }
    }

}
