package com.example.springbanksolid.Services;

import com.example.springbanksolid.DAO.AccountDAO;
import com.example.springbanksolid.Entities.AccountWithdraw;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountWithdrawServiceImpl implements AccountWithdrawService {
    private AccountDAO accountDAO;

    @Override
    public void withdraw(double amount, AccountWithdraw account) {
        account.setBalance(account.getBalance()-amount);
    }
}