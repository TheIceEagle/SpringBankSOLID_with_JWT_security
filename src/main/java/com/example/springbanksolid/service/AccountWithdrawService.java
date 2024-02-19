package com.example.springbanksolid.service;


import com.example.springbanksolid.model.Account.Account;

public interface AccountWithdrawService {
    void withdraw(double amount, Account account);
}