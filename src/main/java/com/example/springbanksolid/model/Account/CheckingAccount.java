package com.example.springbanksolid.model.Account;





public class CheckingAccount extends AccountWithdraw {
    public CheckingAccount(String accountType, String id, String clientID, double balance) {
        super(accountType, id, clientID, balance);
    }
}
