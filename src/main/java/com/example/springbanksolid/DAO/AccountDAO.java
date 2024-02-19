package com.example.springbanksolid.DAO;

import com.example.springbanksolid.model.Account.Account;
import com.example.springbanksolid.model.Account.AccountWithdraw;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountDAO extends CrudRepository<Account,Long> {
    @Query(value = "select * from Account  where clientID = :clientID",
    nativeQuery = true)
    Optional<List<Account>> getClientAccounts(@Param("clientID") String clientID);

    @Transactional
    @Modifying
    @Query(value = "insert into Account (account_type,id,clientID,balance,withdraw_allowed) values (:account_type,:id,:clientID,:balance,:withdraw_allowed)",
    nativeQuery = true)
    void createNewAccount(@Param("account_type")String account_type,@Param("id") String id,@Param("clientID") String clientID,@Param("balance") double balance,@Param("withdraw_allowed") boolean withdraw_allowed);

    @Transactional
    @Modifying
    @Query(value = "update Account set balance = :amount where id = :id",
    nativeQuery = true)
    void updateAccount(String id,double amount);

    @Query(value = "select * from Account  where clientID = :clientID and account_type= :accountType"
    ,nativeQuery = true)
    List<Account> getClientAccountsByType(String clientID, String accountType);
    @Query(value = "select  from Account  where clientID = :clientID  and id =:id",
    nativeQuery = true)
    AccountWithdraw getClientWithdrawAccount(String clientID, String id);
    @Query(value = "select * from Account acc where clientID = :clientID and id = :id",
    nativeQuery = true)
    Optional<Account> getClientAccount(String clientID, String id);

    @Query(value = "select * from Account acc where id = :id",
            nativeQuery = true)
    Optional<Account> getAccount(String id);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Account where id = :id and clientID = :clientID",
            nativeQuery = true)
    void deleteAccountByAccountId(String id, String clientID);
}
