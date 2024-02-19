package com.example.springbanksolid.model.Account;

public class AccountWithdraw extends Account {
    public AccountWithdraw(String accountType, String id, String clientID, double balance) {
        super(accountType,id,clientID,balance, true);
    }
}
