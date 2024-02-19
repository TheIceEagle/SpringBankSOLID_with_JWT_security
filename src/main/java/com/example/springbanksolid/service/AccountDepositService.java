package com.example.springbanksolid.service;

import com.example.springbanksolid.model.Account.Account;

public interface AccountDepositService {
    void deposit(double amount, Account account);
}