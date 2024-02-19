package com.example.springbanksolid.model.Transaction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@Data
@Builder
@Table(name = "Transaction")
@Entity
@NoArgsConstructor
public class Transaction {
    @Id
    @Column(name = "id")
    Long id;
    @Column(name = "account_id")
    String account_id;
    @Column(name = "amount")
    Double amount;
    @Column(name="transaction")
    String transaction;
    @Column(name = "date")
    String date;
    @Column(name="type")
    String type;
}