package com.example.springbanksolid.Services;

import com.example.springbanksolid.Entities.AccountWithdraw;

public interface AccountWithdrawService {
    void withdraw(double amount, AccountWithdraw account);
}