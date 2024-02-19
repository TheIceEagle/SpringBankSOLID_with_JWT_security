package com.example.springbanksolid.service.impl;

import com.example.springbanksolid.model.Account.Account;
import com.example.springbanksolid.service.AccountListingService;
import com.example.springbanksolid.service.WithdrawDepositOperationCLIUI;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TransactionDepositCLI {
    @Autowired
    TransactionDeposit transactionDeposit;
    @Autowired
    WithdrawDepositOperationCLIUI withdrawDepositOperationCLIUI;
    @Autowired
    AccountListingService accountListing;

    public void depositMoney(String clientID) {
        double amount = withdrawDepositOperationCLIUI.requestClientAmount();
        String accountID = withdrawDepositOperationCLIUI.requestClientAccountNumber();
        Account account = accountListing.getClientAccount(clientID, accountID);
        if (account == null) {
            System.out.println("Error, you entered wrong account number");
            return;
        }
        transactionDeposit.execute(account, amount);
    }
}