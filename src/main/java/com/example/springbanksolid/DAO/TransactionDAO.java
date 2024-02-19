package com.example.springbanksolid.DAO;

import com.example.springbanksolid.model.Transaction.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionDAO extends CrudRepository<Transaction,Long> {
    @Query(value = "select * from Transaction where account_id = :account_id",
    nativeQuery = true)
    Optional<List<Transaction>> getTransactions(String account_id);
    @Transactional
    @Modifying
    @Query(value = "insert into Transaction (account_id, amount,transaction,date,type) values(:account_id,:amount,:transaction,:date,:type)",
    nativeQuery = true)
    void addTransaction(String account_id, double amount, String transaction, String date,String type);



}