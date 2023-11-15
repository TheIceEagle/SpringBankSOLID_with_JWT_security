package com.example.springbanksolid.Services;

public class BankCore {
    private static long id = 1;
    private long lastAccountNumber = 1;
    private AccountCreationService accountCreation;

    public BankCore(AccountCreationService accountCreation) {
        this.accountCreation = accountCreation;
    }

    public void createNewAccount(String accountType, String clientID){
        accountCreation.create(accountType, id, clientID, lastAccountNumber);
        incrementLastAccountNumber();
        System.out.println("Bank account created");
    }

    private void incrementLastAccountNumber() {
        lastAccountNumber++;
    }
}
