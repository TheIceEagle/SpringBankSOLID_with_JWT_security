package com.example.springbanksolid.service.impl;

import com.example.springbanksolid.model.Account.Account;
import com.example.springbanksolid.DAO.TransactionDAO;
import com.example.springbanksolid.model.Transaction.Transaction;
import com.example.springbanksolid.service.AccountWithdrawService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Service
@Slf4j
public class TransactionWithdraw {

    private AccountWithdrawService accountWithdrawService;

    private TransactionDAO transactionDAO;
    private final String type = "WITHDRAW";
    private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";
    public void execute(Account account, double amount) {
        if (account.getBalance() > amount) {
            log.info("Withdraw function starts");
            accountWithdrawService.withdraw(amount, account);
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
            String formatDateTime = localDateTime.format(formatter);
            log.info("After date");
            Transaction transaction = Transaction.builder().
                    amount(amount).
                    transaction(String.format("%.2f$ transferred from %s account", amount, account.getId())).
                    date(formatDateTime).
                    account_id(account.getId()).
                    type(type).
                    build();
            transactionDAO.addTransaction(transaction.getAccount_id(), transaction.getAmount(), transaction.getTransaction(), transaction.getDate(), transaction.getType());
            log.info("After adding WITHDRAW transaction to database ");
        } else {
            System.out.println("You do not have sufficient funds for this operation");
        }
    }
}