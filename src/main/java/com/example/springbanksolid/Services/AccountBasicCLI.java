package com.example.springbanksolid.Services;


import com.example.springbanksolid.Entities.Account;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AccountBasicCLI {
    private CreateAccountOperationUI createAccountOperationUI;
    private BankCore bankCore;
    private AccountListingService accountListing;

    public void createAccountRequest(String clientID) {
        String accountType = createAccountOperationUI.requestAccountType();
        if (accountType == null) {
            return;
        }
        bankCore.createNewAccount(accountType, clientID);
    }

    public void getAccounts(String clientID) {
        List<Account> clientAccounts = accountListing.getClientAccounts(clientID);
        System.out.print("[");
        System.out.print(clientAccounts.stream().
                map(Object::toString).
                collect(Collectors.joining(", ")));
        System.out.println(']');
    }

    public static class AccountDeposit extends Account {

        public AccountDeposit(String accountType, String id, String clientID, double balance) {
            super(accountType, id, clientID, balance, false);
        }
    }
}
