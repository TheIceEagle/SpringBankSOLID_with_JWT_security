package com.example.springbanksolid.Services;

import com.example.springbanksolid.DAO.AccountDAO;
import com.example.springbanksolid.Entities.Account;
import com.example.springbanksolid.Entities.AccountWithdraw;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AccountListingServiceImpl implements AccountListingService{
    private AccountDAO accountDAO;


    @Override
    public Account getClientAccount(String clientID, String accountID) {
        return accountDAO.getClientAccount(clientID, accountID);
    }

    @Override
    public AccountWithdraw getClientWithdrawAccount(String clientID, String accountID) {
        return accountDAO.getClientWithdrawAccount(clientID, accountID);
    }

    @Override
    public List<Account> getClientAccounts(String clientID) {
        return accountDAO.getClientAccounts(clientID);
    }

    @Override
    public List<Account> getClientAccountsByType(String clientID, String accountType) {
        return accountDAO.getClientAccountsByType(clientID, accountType);
    }
}
