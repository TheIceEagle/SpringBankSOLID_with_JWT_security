package com.example.springbanksolid.service.impl;


import com.example.springbanksolid.model.Account.Account;
import com.example.springbanksolid.service.AccountListingService;
import com.example.springbanksolid.service.CreateAccountOperationUI;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class AccountBasicCLI {
    @Autowired
    private CreateAccountOperationUI createAccountOperationUI;
    @Autowired
    private BankCore bankCore;
    @Autowired
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
