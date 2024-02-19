package com.example.springbanksolid.exceptions;

public class NotFoundAccount extends RuntimeException{
    private String meesage;

    public NotFoundAccount(String message) {
        super(message);
    }
}
