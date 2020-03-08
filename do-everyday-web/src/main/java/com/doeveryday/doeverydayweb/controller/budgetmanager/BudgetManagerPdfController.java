package com.doeveryday.doeverydayweb.controller.budgetmanager;

import com.doeveryday.doeverydaybudgetmanager.model.Budget;
import com.doeveryday.doeverydaybudgetmanager.model.Transaction;
import com.doeveryday.doeverydaybudgetmanager.pdfgenerator.PdfGenerator;
import com.doeveryday.doeverydaybudgetmanager.service.BudgetService;
import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class BudgetManagerPdfController {

    private final BudgetService budgetService;
    private List<String> header;

    public BudgetManagerPdfController(BudgetService budgetService) {
        this.budgetService = budgetService;
        header =  new ArrayList<>();
        header.add("Description");
        header.add("Date");
        header.add("value");
    }

    @GetMapping("budgetmanager/budget/{id}/pdfdocumentation")
    public String getPfdBudget(@PathVariable("id") Long budgetId) throws IOException, DocumentException {
        Budget budget = budgetService.findById(budgetId);

        PdfGenerator pdfGenerator = new PdfGenerator("balance.pdf");

        pdfGenerator.setTitle("Balance for " + budget.getName());
        List<String> rows = new ArrayList<>();
        for (Transaction transaction:
             budget.getTransaction()) {
            rows.add(transaction.getDescription());
            if (transaction.getDate() != null){
                rows.add(transaction.getDate().toString());
            }
            else {
                rows.add("null");
            }

            rows.add(transaction.getValue().toString() + transaction.getCurrency());

        }
        pdfGenerator.addTable(header, rows);

        pdfGenerator.closeAndSaveDocument();

        return "redirect:/budgetmanager";
    }
}
