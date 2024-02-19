package com.example.springbanksolid.service.impl;

import com.example.springbanksolid.DAO.AccountDAO;
import com.example.springbanksolid.model.Account.Account;
import com.example.springbanksolid.model.Account.AccountWithdraw;
import com.example.springbanksolid.exceptions.AccountListIsEmpty;
import com.example.springbanksolid.exceptions.NotFoundAccount;
import com.example.springbanksolid.service.AccountListingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AccountListingServiceImpl implements AccountListingService {
    @Autowired
    private AccountDAO accountDAO;


    @Override
    public Account getClientAccount(String accountID,String clientID) {
        return accountDAO.getClientAccount(accountID,clientID).orElseThrow(()-> new NotFoundAccount("Erorr during search in the database"));
    }

    @Override
    public AccountWithdraw getClientWithdrawAccount(String clientID, String id) {
        return accountDAO.getClientWithdrawAccount(clientID, id);
    }

    @Override
    public List<Account> getClientAccounts(String clientID) {
         return accountDAO.getClientAccounts(clientID).orElseThrow(()->new AccountListIsEmpty("The list of accounts is empty",HttpStatus.OK.value()));
    }

    @Override
    public List<Account> getClientAccountsByType(String clientID, String accountType) {
        return accountDAO.getClientAccountsByType(clientID, accountType);
    }

    @Override
    public Account getAccount(String id) {
        return accountDAO.getAccount(id).orElseThrow(() ->new NotFoundAccount("Error, during fetching account"));
    }


}
