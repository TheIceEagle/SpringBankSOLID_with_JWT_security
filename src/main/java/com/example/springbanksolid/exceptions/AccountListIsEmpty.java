package com.example.springbanksolid.exceptions;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
public class AccountListIsEmpty extends RuntimeException {
    int httpStatus;

    public AccountListIsEmpty(String message, int httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
