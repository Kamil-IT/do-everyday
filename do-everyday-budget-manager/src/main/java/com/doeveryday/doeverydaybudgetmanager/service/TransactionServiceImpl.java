package com.doeveryday.doeverydaybudgetmanager.service;

import com.doeveryday.doeverydaybudgetmanager.exception.ExistsInDatabaseException;
import com.doeveryday.doeverydaybudgetmanager.exception.NotFoundException;
import com.doeveryday.doeverydaybudgetmanager.model.Budget;
import com.doeveryday.doeverydaybudgetmanager.model.Transaction;
import com.doeveryday.doeverydaybudgetmanager.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        if (transaction.getId() == null){
            return transactionRepository.save(transaction);
        }
        else if (transactionRepository.existsById(transaction.getId())){
            throw new ExistsInDatabaseException("transaction with id: " + transaction.getId() + " its already exist in db you cannot create ");
        }
        else {
            return transactionRepository.save(transaction);
        }
    }

    @Override
    public List<Transaction> getTransaction() {
        List<Transaction> transactions = new ArrayList<>();
        transactionRepository.findAll().forEach(transactions::add);
        return transactions;
    }

    @Override
    public Transaction findById(Long id) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);
        if (transactionOptional.isEmpty()){
            throw new NotFoundException("Not Found transaction with id: " + id);
        }
        else {
            return transactionOptional.get();
        }
    }

    @Override
    public void deleteById(Long id) {
        if (!transactionRepository.existsById(id)){
            throw new NotFoundException("Not Found budget with id: " + id);
        }
        else {
            transactionRepository.deleteById(id);
        }
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        if (transaction.getId() == null){
            transactionRepository.save(transaction);
        }
        else if (!transactionRepository.existsById(transaction.getId())){
            throw new NotFoundException("Not Found transaction with id: " + transaction.getId());
        }
        else {
            transactionRepository.save(transaction);
        }
    }

    @Override
    public boolean existsById(Long id) {
        return transactionRepository.existsById(id);
    }
}
