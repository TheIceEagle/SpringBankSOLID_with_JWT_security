package com.example.springbanksolid.service.impl;

import com.example.springbanksolid.model.Account.AccountWithdraw;
import com.example.springbanksolid.service.AccountListingService;
import com.example.springbanksolid.service.WithdrawDepositOperationCLIUI;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TransactionWithdrawCLI {
    @Autowired
    private TransactionWithdraw transactionWithdraw;
    @Autowired
    private WithdrawDepositOperationCLIUI withdrawDepositOperationCLIUI;
    @Autowired
    private AccountListingService accountListing;

    public void withdrawMoney(String clientID) {
        double amount = withdrawDepositOperationCLIUI.requestClientAmount();
        String accountID = withdrawDepositOperationCLIUI.requestClientAccountNumber();
        AccountWithdraw account = accountListing.getClientWithdrawAccount(clientID, accountID);
        if (account == null) {
            System.out.println("Error, you there was not Withdraw Account found by this ID");
            return;
        }
        transactionWithdraw.execute(account, amount);
    }
}