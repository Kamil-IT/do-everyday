package com.doeveryday.doeverydaybudgetmanager.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

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
    @Column(nullable = false)
    Double value;

    @Nullable
    String description;

    @Enumerated(EnumType.STRING)
    Currency currency;

    @NotNull
    @Column(nullable = false)
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
