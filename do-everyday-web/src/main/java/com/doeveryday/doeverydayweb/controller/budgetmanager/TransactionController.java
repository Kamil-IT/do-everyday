package com.doeveryday.doeverydayweb.controller.budgetmanager;

import com.doeveryday.doeverydaybudgetmanager.model.Transaction;
import com.doeveryday.doeverydaybudgetmanager.service.BudgetService;
import com.doeveryday.doeverydaybudgetmanager.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Slf4j
@Controller
public class TransactionController {

    private TransactionService transactionService;
    private BudgetService budgetService;

    @GetMapping("budgetmanager/budget/transaction/{id}/edit")
    public String initUpdateTransaction(@PathVariable("id") Long id, Model model){
        model.addAttribute("transaction", transactionService.findById(id));

        return "budgetmanager/budget/addtransaction";
    }

    @GetMapping("budgetmanager/budget/{id}/transaction/add")
    public String initAddTransaction(@PathVariable("id") Long budgetId, Model model){
        Transaction transaction = new Transaction();
        transaction.setBudget(budgetService.findById(budgetId));
        model.addAttribute("transaction", transaction);

        return "budgetmanager/budget/addtransaction";
    }

    @PostMapping("budgetmanager/budget/{id}/transaction/edit")
    public String addOrUpdateTransaction(Transaction transaction, @PathVariable("id") Long boardId){
        transaction.setBudget(budgetService.findById(boardId));
        if (transaction.getId() == null){
            transactionService.saveTransaction(transaction);
        }
        else if(transactionService.existsById(transaction.getId())){
            transactionService.updateTransaction(transaction);
        }
        else {
            transactionService.saveTransaction(transaction);
        }
        return "redirect:/budgetmanager/budget";
    }

    @PostMapping("budgetmanager/budget/transaction/{id}/delete")
    public String deleteTransaction(@PathVariable("id") Long id){
        transactionService.deleteById(id);

        return "redirect:/budgetmanager/budget";
    }
}
