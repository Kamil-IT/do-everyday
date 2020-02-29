package com.doeveryday.doeverydayweb.controller.budgetmanager;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Slf4j
@Controller
public class IndexBudgetManagerController {

    @GetMapping(value = {"budgetmanager/index", "budgetmanager", "budgetmanager/budget/index"})
    public String showBudget(){
        return "redirect:budgetmanager/budget";
    }
}
