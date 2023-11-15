package com.example.springbanksolid.Services;

import com.example.springbanksolid.Entities.AccountWithdraw;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionWithdrawCLI {
    private TransactionWithdraw transactionWithdraw;
    private WithdrawDepositOperationCLIUI withdrawDepositOperationCLIUI;
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