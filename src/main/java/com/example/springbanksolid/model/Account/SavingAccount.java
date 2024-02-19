package com.example.springbanksolid.model.Account;


public class SavingAccount extends AccountWithdraw {
    public SavingAccount(String accountType, String id, String clientID, double balance) {
        super(accountType, id, clientID, balance);
    }
}
