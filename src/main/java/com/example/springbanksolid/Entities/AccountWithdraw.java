package com.example.springbanksolid.Entities;

import com.example.springbanksolid.Entities.Account;

public class AccountWithdraw extends Account {
    public AccountWithdraw(String accountType, String id, String clientID, double balance) {
        super(accountType, id, clientID, balance, true);
    }
}
