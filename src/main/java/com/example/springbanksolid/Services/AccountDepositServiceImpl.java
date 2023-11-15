package com.example.springbanksolid.Services;

import com.example.springbanksolid.DAO.AccountDAO;
import com.example.springbanksolid.Entities.Account;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountDepositServiceImpl implements AccountDepositService {
    private AccountDAO accountDAO;

    @Override
    public void deposit(double amount, Account account) {
        account.setBalance(account.getBalance()+amount);
    }
}