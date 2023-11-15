package com.example.springbanksolid.Entities;

import com.example.springbanksolid.Entities.AccountWithdraw;

public class SavingAccount extends AccountWithdraw {
    public SavingAccount(String accountType, String id, String clientID, double balance) {
        super(accountType, id, clientID, balance);
    }
}
