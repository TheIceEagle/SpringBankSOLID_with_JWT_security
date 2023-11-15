package com.example.springbanksolid.Services;

import com.example.springbanksolid.Entities.Account;
import com.example.springbanksolid.DAO.TransactionDAO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionDeposit {
    private AccountDepositService accountDepositService;
    private TransactionDAO transactionDAO;

    public void execute(Account account, double amount) {
        accountDepositService.deposit(amount, account);
        transactionDAO.addTransaction(new Transaction(String.format("%.2f$ transferred to %s account", amount, account.getId())));
        System.out.println(transactionDAO.getTransactions().get(transactionDAO.getTransactions().size() - 1).transaction);
    }
}