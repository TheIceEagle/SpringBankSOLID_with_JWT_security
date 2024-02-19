package com.example.springbanksolid.exceptions;

public class NotFoundUser extends RuntimeException{
    private String meesage;

    public NotFoundUser(String message) {
        super(message);
    }
}
