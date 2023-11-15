package com.example.springbanksolid.DAO;

import com.example.springbanksolid.Entities.Account;
import com.example.springbanksolid.Entities.AccountWithdraw;

import java.util.List;

public interface AccountDAO {
    List<Account> getClientAccounts(String clientID);
    void createNewAccount(Account account);
    void updateAccount(Account account);
    List<Account> getClientAccountsByType(String clientID, String accountType);
    AccountWithdraw getClientWithdrawAccount(String clientID, String accountID);
    Account getClientAccount(String clientID, String accountID);
}
