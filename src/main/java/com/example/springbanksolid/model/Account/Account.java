package com.example.springbanksolid.model.Account;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Table(name = "Account")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Column(name = "account_type")
    private String account_type;
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "clientID")
    private String clientID;

    @Column(name ="balance")
    private double balance;

    @Column(name = "withdraw_allowed")
    private boolean withdraw_allowed;



    @Override
    public String toString() {
        return String.format("Account{accountType=%s, id='%s', clientID='%s', balance=%.1f}", account_type, id, clientID, balance);
    }




}