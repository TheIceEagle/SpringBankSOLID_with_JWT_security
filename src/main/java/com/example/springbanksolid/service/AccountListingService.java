package com.example.springbanksolid.service;

import com.example.springbanksolid.model.Account.Account;
import com.example.springbanksolid.model.Account.AccountWithdraw;

import java.util.List;

public interface AccountListingService {
    Account getClientAccount(String clientID, String accountID);
    AccountWithdraw getClientWithdrawAccount(String clientID, String accountID);
    List<Account> getClientAccounts(String clientID);
    List<Account> getClientAccountsByType(String clientID, String accountType);
    Account getAccount(String id);

}
