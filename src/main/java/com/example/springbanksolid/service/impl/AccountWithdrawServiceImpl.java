package com.example.springbanksolid.service.impl;

import com.example.springbanksolid.DAO.AccountDAO;
import com.example.springbanksolid.model.Account.Account;
import com.example.springbanksolid.service.AccountWithdrawService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class AccountWithdrawServiceImpl implements AccountWithdrawService {
    @Autowired
    private AccountDAO accountDAO;

    @Override
    public void withdraw(double amount,Account account) {
        if(amount > 0) {
            log.info("Account updating");
            double newBalance = account.getBalance()-amount;
            account.setBalance(newBalance);
            String id = account.getId();
            accountDAO.updateAccount(id,newBalance);
            log.info("account after updating"+account.toString());
        } else {
            log.error("The amount of money is either negative or there is not enough money in account balance to withdraw");
        }

    }
}