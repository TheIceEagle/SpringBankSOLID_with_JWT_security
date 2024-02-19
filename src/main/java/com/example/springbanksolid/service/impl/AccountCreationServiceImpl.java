package com.example.springbanksolid.service.impl;

import com.example.springbanksolid.DAO.AccountDAO;
import com.example.springbanksolid.model.Account.Account;
import com.example.springbanksolid.model.Account.CheckingAccount;
import com.example.springbanksolid.model.Account.FixedAccount;

import com.example.springbanksolid.model.Account.SavingAccount;
import com.example.springbanksolid.service.AccountCreationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class AccountCreationServiceImpl implements AccountCreationService {
    @Autowired
    private AccountDAO accountDAO;

    @Override
    public void create(String accountType, long bankID, String clientID, long accountID) {

        String id = String.format("%03d%06d", bankID, accountID);
        log.info("Account creation");
        Account account = switch (accountType) {
            case "SAVING" -> new SavingAccount(accountType, id, clientID, 0);
            case "CHECKING" -> new CheckingAccount(accountType, id, clientID, 0);
            case "FIXED" -> new FixedAccount(accountType, id, clientID, 0);
            default -> throw new IllegalArgumentException("Unexpected value: " + accountType);
        };
        log.info(account.toString());
        accountDAO.createNewAccount(account.getAccount_type(),account.getId(), account.getClientID(),account.getBalance(), account.isWithdraw_allowed());
    }
}
