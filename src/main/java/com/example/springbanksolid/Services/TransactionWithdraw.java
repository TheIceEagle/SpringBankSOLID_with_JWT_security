package com.example.springbanksolid.Services;

import com.example.springbanksolid.Entities.AccountWithdraw;
import com.example.springbanksolid.DAO.TransactionDAO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionWithdraw {
    private AccountWithdrawService accountWithdrawService;
    private TransactionDAO transactionDAO;

    public void execute(AccountWithdraw account, double amount) {
        if (account.getBalance() > amount) {
            accountWithdrawService.withdraw(amount, account);
            transactionDAO.addTransaction(new Transaction(String.format("%.2f$ transferred from %s account", amount, account.getId())));
            System.out.println(transactionDAO.getTransactions().get(transactionDAO.getTransactions().size() - 1).transaction);
        } else {
            System.out.println("You do not have sufficient funds for this operation");
        }

    }
}