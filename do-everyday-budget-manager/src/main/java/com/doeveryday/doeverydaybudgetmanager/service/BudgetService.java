package com.doeveryday.doeverydaybudgetmanager.service;

import com.doeveryday.doeverydaybudgetmanager.model.Budget;

import java.util.List;

public interface BudgetService {

    Budget saveBudget(Budget budget);

    List<Budget> getBudgets();

    Budget findById(Long id);

    void deleteById(Long id);

    void updateBudget(Budget budget);

    boolean existsById(Long id);
}
