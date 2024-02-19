//package com.example.springbanksolid.DAO;
//
//import com.example.springbanksolid.DAO.AccountDAO;
//import com.example.springbanksolid.Entities.Account;
//import com.example.springbanksolid.Entities.AccountWithdraw;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class MemoryAccountDAO {
//    private List<Account> accountList = new ArrayList<>();
//
//
//    public List<Account> getClientAccounts(String clientID) {
//        return this.accountList.stream()
//                .filter(x -> x.getClientID().equals(clientID))
//                .collect(Collectors.toList());
//    }
//
//
//    public void createNewAccount(Account account) {
//        this.accountList.add(account);
//    }
//
//
//    public void updateAccount(Account account) {
//        Account accountToBeUpdated = accountList.stream()
//                .filter(x -> account.getId().equals(x.getId()))
//                .findAny()
//                .orElse(null);
//        System.out.println("This feature is not represented properly in the UML diagram");
//    }
//
//
//    public List<Account> getClientAccountsByType(String clientID, String accountType) {
//        return this.accountList.stream()
//                .filter(x -> x.getAccountType() == accountType)
//                .filter(x -> x.getClientID().equals(clientID))
//                .collect(Collectors.toList());
//    }
//
//
//    public AccountWithdraw getClientWithdrawAccount(String clientID, String accountID) {
//        return (AccountWithdraw) accountList.stream()
//                .filter(x -> x.getClientID().equals(clientID))
//                .filter(x -> x.getId().equals(accountID))
//                .filter(x -> x.isWithdrawAllowed())
//                .findAny()
//                .orElse(null);
//    }
//
//
//    public Account getClientAccount(String clientID, String accountID) {
//        return accountList.stream()
//                .filter(x -> x.getClientID().equals(clientID))
//                .filter(x -> x.getId().equals(accountID))
//                .findAny()
//                .orElse(null);
//    }
//}