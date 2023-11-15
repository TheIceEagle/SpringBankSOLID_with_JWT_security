package com.example.springbanksolid.Services;

import com.example.springbanksolid.DAO.AccountDAO;
import com.example.springbanksolid.Entities.Account;
import com.example.springbanksolid.Entities.CheckingAccount;
import com.example.springbanksolid.Entities.FixedAccount;
import com.example.springbanksolid.Entities.SavingAccount;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountCreationServiceImpl implements AccountCreationService{
    private AccountDAO accountDAO;

    @Override
    public void create(String accountType, long bankID, String clientID, long accountID) {
        String id = String.format("%03d%06d", bankID, accountID);
        Account account = switch (accountType) {
            case "SAVING" -> new SavingAccount(accountType, id, clientID, 0);
            case "CHECKING" -> new CheckingAccount(accountType, id, clientID, 0);
            case "FIXED" -> new FixedAccount(accountType, id, clientID, 0);
            default -> throw new IllegalStateException("Unexpected value: " + accountType);
        };
        accountDAO.createNewAccount(account);
    }
}
