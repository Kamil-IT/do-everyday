package com.doeveryday.doeverydaybudgetmanager.repository;

import com.doeveryday.doeverydaybudgetmanager.model.Budget;
import org.springframework.data.repository.CrudRepository;

public interface BudgetRepository extends CrudRepository<Budget, Long> {
}
