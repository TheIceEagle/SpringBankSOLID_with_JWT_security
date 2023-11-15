package com.example.springbanksolid.DAO;

import com.example.springbanksolid.Services.Transaction;

import java.util.List;

public interface TransactionDAO {
    List<Transaction> getTransactions();
    void addTransaction(Transaction transaction);
}