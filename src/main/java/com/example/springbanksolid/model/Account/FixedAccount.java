package com.example.springbanksolid.model.Account;


import com.example.springbanksolid.service.impl.AccountBasicCLI;


public class FixedAccount extends AccountBasicCLI.AccountDeposit {

    public FixedAccount(String accountType, String id, String clientID, double balance) {
        super(accountType, id, clientID, balance);
    }
}
