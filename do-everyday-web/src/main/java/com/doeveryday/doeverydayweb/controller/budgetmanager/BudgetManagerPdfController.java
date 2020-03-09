package com.doeveryday.doeverydayweb.controller.budgetmanager;

import com.doeveryday.doeverydaybudgetmanager.pdfgenerator.PdfBudgetGenerator;
import com.doeveryday.doeverydaybudgetmanager.service.BudgetService;
import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Controller
public class BudgetManagerPdfController {

    private final BudgetService budgetService;
    private final String BALANCE_FILE_NAME = "balance.pdf";

    public BudgetManagerPdfController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping("budgetmanager/budget/{id}/pdfdocumentation")
    public String getPfdBudget(HttpServletResponse response,
                               @PathVariable("id") Long budgetId) throws IOException, DocumentException {
        PdfBudgetGenerator pdfBudgetGenerator = new PdfBudgetGenerator(BALANCE_FILE_NAME);
        pdfBudgetGenerator.generatePdfFile(budgetService.findById(budgetId));

        Path file = Paths.get(BALANCE_FILE_NAME);
        if (Files.exists(file)) {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename=" + BALANCE_FILE_NAME);
            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return "redirect:/budgetmanager";
    }
}
