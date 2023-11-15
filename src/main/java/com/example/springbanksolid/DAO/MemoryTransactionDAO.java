package com.example.springbanksolid.DAO;


import com.example.springbanksolid.Services.Transaction;

import java.util.ArrayList;
import java.util.List;

public class MemoryTransactionDAO implements TransactionDAO {
    private List<Transaction> transactions = new ArrayList<Transaction>();

    @Override
    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }
}