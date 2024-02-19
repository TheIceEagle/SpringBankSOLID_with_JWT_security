package com.example.springbanksolid.service;

public interface AccountCreationService {
    void create(String accountType, long bankID, String clientID, long accountID);
}
