package com.doeveryday.doeverydaybudgetmanager.service;

import com.doeveryday.doeverydaybudgetmanager.model.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction saveTransaction(Transaction transaction);

    List<Transaction> getTransaction();

    Transaction findById(Long id);

    void deleteById(Long id);

    void updateTransaction(Transaction transaction);

    boolean existsById(Long id);

    List<Transaction> getTransactionCreateToday();
}
