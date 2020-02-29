package com.doeveryday.doeverydaybudgetmanager.service;

import com.doeveryday.doeverydaybudgetmanager.exception.ExistsInDatabaseException;
import com.doeveryday.doeverydaybudgetmanager.exception.NotFoundException;
import com.doeveryday.doeverydaybudgetmanager.model.Budget;
import com.doeveryday.doeverydaybudgetmanager.repository.BudgetRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;

    @Override
    public Budget saveBudget(Budget budget) {
        if (budget.getId() == null){
            return budgetRepository.save(budget);
        }
        else if (budgetRepository.existsById(budget.getId())){
            throw new ExistsInDatabaseException("Budget with id: " + budget.getId() + " its already exist in db you cannot create ");
        }
        else {
            return budgetRepository.save(budget);
        }
    }

    @Override
    public List<Budget> getBudgets() {
        List<Budget> budgets = new ArrayList<>();
        budgetRepository.findAll().forEach(budgets::add);
        return budgets;
    }

    @Override
    public Budget findById(Long id) {
        Optional<Budget> budgetOptional = budgetRepository.findById(id);
        if (budgetOptional.isEmpty()){
            throw new NotFoundException("Not Found budget with id: " + id);
        }
        else {
            return budgetOptional.get();
        }
    }

    @Override
    public void deleteById(Long id) {
        if (!budgetRepository.existsById(id)){
            throw new NotFoundException("Not Found budget with id: " + id);
        }
        else {
            budgetRepository.deleteById(id);
        }

    }

    @Override
    public void updateBudget(Budget budget) {
        if (budget.getId() == null){
            budgetRepository.save(budget);
        }
        else if (!budgetRepository.existsById(budget.getId())){
            throw new ExistsInDatabaseException("Not Found budget with id: " + budget.getId());
        }
        else {
            budgetRepository.save(budget);
        }
    }

    @Override
    public boolean existsById(Long id) {
        return budgetRepository.existsById(id);
    }
}
