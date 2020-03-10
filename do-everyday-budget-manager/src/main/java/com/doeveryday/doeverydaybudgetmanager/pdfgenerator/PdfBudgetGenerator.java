package com.doeveryday.doeverydaybudgetmanager.pdfgenerator;

import com.doeveryday.doeverydaybudgetmanager.model.Budget;
import com.doeveryday.doeverydaybudgetmanager.model.Currency;
import com.doeveryday.doeverydaybudgetmanager.model.Transaction;
import com.itextpdf.text.DocumentException;
import lombok.Getter;
import lombok.Setter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class PdfBudgetGenerator {
    private PdfGenerator pdfGenerator;

    public PdfBudgetGenerator(String fileName) throws FileNotFoundException, DocumentException {
        this.pdfGenerator = new PdfGenerator(fileName);
    }

    public PdfBudgetGenerator() throws FileNotFoundException, DocumentException {
        this.pdfGenerator = new PdfGenerator("budget.pdf");
    }

    public void generatePdfFile(Budget budget) throws DocumentException, IOException {

        pdfGenerator.setTitle("Balance for " + budget.getName());

        pdfGenerator.addTable(header(), rows(budget.getTransaction()));

        pdfGenerator.addSummary();

        pdfGenerator.addTextAlignRight(
                buildSummaryString(
                    summarySpendMoneyByCurrency(budget.getTransaction())
                ));

        pdfGenerator.closeAndSaveDocument();
    }

    private List<String> rows(List<Transaction> transactions){
        List<String> list = new ArrayList<>();

        for (Transaction transaction:
                transactions) {
            list.add(transaction.getDescription());
            if (transaction.getDate() != null){
                list.add(transaction.getDate().toString());
            }
            else {
                list.add("-");
            }

            list.add(transaction.getValue().toString() + transaction.getCurrency());

        }
        return list;
    }

    private List<String> header(){
        List<String> list =  new ArrayList<>();
        list.add("Description");
        list.add("Date");
        list.add("value");
        return list;
    }

    private HashMap<Currency, Double> summarySpendMoneyByCurrency(List<Transaction> transactions){
        HashMap<Currency, Double> currencyHashMap = new HashMap<>();
        for (Transaction transaction :
                transactions) {
            Currency currency = transaction.getCurrency();
            if (currencyHashMap.containsKey(currency)){
                currencyHashMap.put(currency, currencyHashMap.get(currency) + transaction.getValue());
            }
            else {
                currencyHashMap.put(transaction.getCurrency(), transaction.getValue());
            }
        }
        return currencyHashMap;
    }

    private String buildSummaryString(HashMap<Currency, Double> currencyValueHashMap){
        String returnString = "";

        for (Currency currency :
                currencyValueHashMap.keySet()) {
            returnString += currencyValueHashMap.get(currency) + currency.toString() + "\n";
        }
        return returnString;
    }

}
