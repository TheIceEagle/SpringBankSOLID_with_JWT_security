package com.example.springbanksolid.controller;

import com.example.springbanksolid.DAO.AccountDAO;
import com.example.springbanksolid.DAO.AccountTransferRequest;
import com.example.springbanksolid.DAO.TransactionDAO;
import com.example.springbanksolid.DAO.UserRepository;
import com.example.springbanksolid.DTO.AccountRequestType;
import com.example.springbanksolid.DTO.RequestAmount;
import com.example.springbanksolid.auth.AuthenticationRequest;
import com.example.springbanksolid.auth.AuthenticationService;
import com.example.springbanksolid.config.JwtService;
import com.example.springbanksolid.model.Account.Account;
import com.example.springbanksolid.model.Transaction.Transaction;
import com.example.springbanksolid.model.User.User_table;
import com.example.springbanksolid.service.*;
import com.example.springbanksolid.exceptions.NotFoundAccount;
import com.example.springbanksolid.service.impl.BankCore;
import com.example.springbanksolid.service.impl.TransactionDeposit;
import com.example.springbanksolid.service.impl.TransactionWithdraw;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.*;

@RestController
@Slf4j
@SecurityRequirement(name = "basicauth")
@RequestMapping("/acccounts")
public class AccountController {
    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private AccountListingService accountListingService;
    @Autowired
    private AccountCreationService accountCreationService;
    @Autowired
    private BankCore bankCore;
    @Autowired
    AccountDepositService accountDepositService;
    @Autowired
    TransactionWithdraw transactionWithdraw;
    @Autowired
    TransactionDeposit transactionDeposit;
    @Autowired
    TransactionDAO transactionDAO;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationService service;
    @Autowired
    UserRepository userRepository;


    @GetMapping()
    public List<Account> getClientsAccount(HttpServletRequest request) {
            return accountListingService.getClientAccounts(service.getClientIdFromRequest(request));
        }
//        String jwt =request.getHeader("Authorization").substring(7);
//        User_table client = userRepository.findUser_tableByUsername(jwtService.extractUsername(jwt)).get();
//        String clientId = String.valueOf(client.getId());
//        return accountListingService.getClientAccounts(clientId);

    @PostMapping()
    public ResponseEntity<?> createAccount(HttpServletRequest request,@RequestBody AccountRequestType accountRequestType) {
        String id = service.getClientIdFromRequest(request);
        HashMap<String, String> message = new HashMap<>();
        try {
            bankCore.createNewAccount(accountRequestType.getAccountType(), id);
            message.put("message", "Account successfully created");
            return new ResponseEntity(message, CREATED);
        } catch (Exception e) {
            message.put("message", "Error! Please enter the correct account type");
            return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @GetMapping("/{account_id}")
    public ResponseEntity<Object> getClientAccount(HttpServletRequest request ,@RequestParam String account_id) {
        String id = service.getClientIdFromRequest(request);
        HashMap<String, Object> message = new HashMap<>();
        HttpStatus httpStatus;
        try {
            Account account = accountListingService.getClientAccount(id, account_id);
            httpStatus = FOUND;
            return new ResponseEntity<>(account, httpStatus);
        } catch (Exception e){
            message.put("message", "Account was not found");
            httpStatus = NOT_FOUND;
            return new ResponseEntity<>(message, httpStatus);
        }
    }

    @PostMapping("/{account_id}/withdraw")
    public ResponseEntity<?> withdrawFromAccount(@RequestParam String account_id ,@RequestBody RequestAmount requestAmount,HttpServletRequest request) {
        HashMap<String, Object> message = new HashMap<>();
        String id = service.getClientIdFromRequest(request);
        try {
            Account account = accountListingService.getClientAccount(id,account_id);
            if (account.isWithdraw_allowed() == false) {
                message.put("message", "FIXED  acccounts are not allowed to withdraw money");
                return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
            }
            if (account.getBalance() < requestAmount.getAmount()) {
                message.put("message", "Not enough money on account");
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }
            transactionWithdraw.execute(account, requestAmount.getAmount());
            message.put("message", String.format("%.2f was transferred from Account: %s", requestAmount.getAmount(), account_id));
            return new ResponseEntity<>(message, HttpStatus.OK);

        } catch (Exception e) {
            message.put("message", "Error! Withdraw is not possible");
            return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @PostMapping("{account_id}/deposit")
    public ResponseEntity<?> depositToAccountByAccountId(HttpServletRequest request, @RequestBody RequestAmount requestAmount, @PathVariable("account_id") String account_id) {
        HashMap<String, Object> message = new HashMap<>();
        String id = service.getClientIdFromRequest(request);
        try {
            Account account = accountListingService.getClientAccount(id,account_id);
            transactionDeposit.execute(account, requestAmount.getAmount());
            message.put("message", String.format("%.2f was transferred to Account: %s", requestAmount.getAmount(), account_id));
            return new ResponseEntity<>(message, HttpStatus.OK);

        } catch (Exception e) {
            message.put("message", "Error! Deposit is not possible");
            return new ResponseEntity<>(message, BAD_REQUEST);
        }
    }

    @GetMapping("/{account_id}/transactions")
    public List<Transaction> getTransactions (@RequestParam String account_id) {
        return transactionDAO.getTransactions(account_id).orElseThrow(()->new NotFoundAccount("Transactions not found"));
    }

    @DeleteMapping("/{account_id}")
    public ResponseEntity<?> deleteAccountByAccountId(HttpServletRequest request,@PathVariable("account_id") String accountID) {
        String id = service.getClientIdFromRequest(request);
        HashMap<String, String> message = new HashMap<>();
        if (accountID != null) {
            accountDAO.deleteAccountByAccountId(accountID,id);
            message.put("message", String.format("Account %s was deleted", accountID));
            return new ResponseEntity(message, HttpStatus.OK);
        } else {
            message.put("message", "Account ID was empty, please enter Account ID");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{account_id}/transfer")
    public ResponseEntity<Object> postTransfer (HttpServletRequest request, @RequestBody AccountTransferRequest requestTransaction, @PathVariable("account_id") String accountIdFrom) throws Exception {
        Map<String, Object> message = new HashMap<>();
        String authHeader = request.getHeader("Authorization");
        String jwt = authHeader.substring(7);
        User_table client = userRepository.findUser_tableByUsername(jwtService.extractUsername(jwt)).get();
        String clientId = String.valueOf(client.getId());
        try {
            transactionWithdraw.execute(accountListingService.getClientAccount(clientId, accountIdFrom), requestTransaction.getAmount());
            transactionDeposit.execute(accountListingService.getClientAccount(clientId, requestTransaction.getDestination_account_id()), requestTransaction.getAmount());
            System.out.println(requestTransaction.getAmount() + "$ was transferred to " + accountListingService.getClientAccount(clientId, requestTransaction.getDestination_account_id()) +
                    "from " + accountListingService.getClientAccount(clientId, accountIdFrom));

        } catch (Exception e) {
            message.put("message", "Transaction ended with mistake");
            return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
        }
        message.put("message",String.format("%.2f was transferred to Account: %s from Account: %s", requestTransaction.getAmount(), requestTransaction.getDestination_account_id(),accountIdFrom));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
