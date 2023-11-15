package com.example.springbanksolid.Services;

import com.example.springbanksolid.Entities.Account;

public interface AccountDepositService {
    void deposit(double amount, Account account);
}