package com.example.springbanksolid.service.impl;

import com.example.springbanksolid.DAO.AccountDAO;
import com.example.springbanksolid.model.Account.Account;
import com.example.springbanksolid.service.AccountDepositService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class AccountDepositServiceImpl implements AccountDepositService {
    @Autowired
    private AccountDAO accountDAO;

    @Override
    public void deposit(double amount, Account account) {
        if(!(amount <= 0)) {
            account.setBalance(account.getBalance()+amount);
            log.info("info of deposit money:"+account.getBalance());
            double balance = account.getBalance();
            String id = account.getId();
            accountDAO.updateAccount(id,balance);
        } else {
            log.error("The amount of money is either negative or zero");
        }
    }
}