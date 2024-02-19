package com.example.springbanksolid.service.impl;

import com.example.springbanksolid.model.Account.Account;
import com.example.springbanksolid.DAO.TransactionDAO;
import com.example.springbanksolid.model.Transaction.Transaction;
import com.example.springbanksolid.service.AccountDepositService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Service
@Slf4j
public class TransactionDeposit {
    @Autowired
    private AccountDepositService accountDepositService;
    @Autowired
    private TransactionDAO transactionDAO;
    private final String type = "DEPOSIT";
    private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";
    public void execute(Account account, double amount) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        String formatDateTime = localDateTime.format(formatter);

        accountDepositService.deposit(amount, account);
        log.info("After deposit done");
        Transaction transaction = Transaction.builder().
                amount(amount).
                transaction(String.format("%.2f$ transferred to %s account", amount, account.getId())).
                date(formatDateTime).
                account_id(account.getId()).
                type(type).
                build();
        log.info(transaction.toString());
        transactionDAO.addTransaction(transaction.getAccount_id(), transaction.getAmount(), transaction.getTransaction(), transaction.getDate(), transaction.getType());
//        System.out.println(transactionDAO.getTransactions().get(transactionDAO.getTransactions().size() - 1).transaction);
        log.info("After adding transactions"+transaction.toString());
    }
}