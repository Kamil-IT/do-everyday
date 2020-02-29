package com.doeveryday.doeverydayweb.controller.budgetmanager;

import com.doeveryday.doeverydaybudgetmanager.model.Budget;
import com.doeveryday.doeverydaybudgetmanager.service.BudgetService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@AllArgsConstructor
@Controller
public class BudgetController {

    private final BudgetService budgetService;

    @GetMapping("budgetmanager/budget")
    public String showBudget(Model model){
        model.addAttribute("budgets", budgetService.getBudgets());

        return "budgetmanager/budget/index";
    }

    @GetMapping("budgetmanager/budget/{id}/edit")
    public String initUpdateBudget(@PathVariable("id") Long id, Model model){
        model.addAttribute("budget", budgetService.findById(id));

        return "budgetmanager/budget/formbudget";
    }

    @GetMapping("budgetmanager/budget/add")
    public String initSaveBudget(Model model){
        model.addAttribute("budget", new Budget());

        return "budgetmanager/budget/formbudget";
    }

    @PostMapping("budgetmanager/budget/edit")
    public String UpdateBudget(Budget budget){
        if (budget.getId() == null){
            budgetService.saveBudget(budget);
        }
        else if (!budgetService.existsById(budget.getId())){
            budgetService.saveBudget(budget);
        }
        else {
            budgetService.updateBudget(budget);
        }

        return "redirect:/budgetmanager/budget";
    }

    @PostMapping("budgetmanager/budget/{id}/delete")
    public String DeleteBudget(@PathVariable("id") Long id){
        log.warn("Deleting by id: " + id);
        budgetService.deleteById(id);

        return "redirect:/budgetmanager/budget";
    }
}
