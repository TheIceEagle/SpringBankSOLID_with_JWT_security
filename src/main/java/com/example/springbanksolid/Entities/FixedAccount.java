package com.example.springbanksolid.Entities;


import com.example.springbanksolid.Services.AccountBasicCLI;

public class FixedAccount extends AccountBasicCLI.AccountDeposit {
    public FixedAccount(String accountType, String id, String clientID, double balance) {
        super(accountType, id, clientID, balance);
    }
}
