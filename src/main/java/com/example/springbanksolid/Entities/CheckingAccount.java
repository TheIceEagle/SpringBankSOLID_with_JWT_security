package com.example.springbanksolid.Entities;

import com.example.springbanksolid.Entities.AccountWithdraw;

public class CheckingAccount extends AccountWithdraw {
    public CheckingAccount(String accountType, String id, String clientID, double balance) {
        super(accountType, id, clientID, balance);
    }
}
