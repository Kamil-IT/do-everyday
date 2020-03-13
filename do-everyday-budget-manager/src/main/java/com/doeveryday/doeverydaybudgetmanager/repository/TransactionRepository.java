package com.doeveryday.doeverydaybudgetmanager.repository;

import com.doeveryday.doeverydaybudgetmanager.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
