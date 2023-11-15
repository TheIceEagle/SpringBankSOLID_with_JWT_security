package com.example.springbanksolid.Services;

import com.example.springbanksolid.Entities.Account;
import com.example.springbanksolid.Entities.AccountWithdraw;

import java.util.List;

public interface AccountListingService {
    Account getClientAccount(String clientID, String accountID);
    AccountWithdraw getClientWithdrawAccount(String clientID, String accountID);
    List<Account> getClientAccounts(String clientID);
    List<Account> getClientAccountsByType(String clientID, String accountType);

}
