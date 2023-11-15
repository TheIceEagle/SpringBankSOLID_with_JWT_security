package com.example.springbanksolid;

import com.example.springbanksolid.DAO.AccountDAO;
import com.example.springbanksolid.DAO.MemoryAccountDAO;
import com.example.springbanksolid.DAO.MemoryTransactionDAO;
import com.example.springbanksolid.DAO.TransactionDAO;
import com.example.springbanksolid.Services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public MyCLI myCLI() {
        return new MyCLI();
    }

    @Bean
    public AccountDAO accountDAO() {
        return new MemoryAccountDAO();
    }

    @Bean
    public AccountCreationService accountCreation() {
        return new AccountCreationServiceImpl(accountDAO());
    }

    @Bean
    public AccountListingService accountListing() {
        return new AccountListingServiceImpl(accountDAO());
    }

    @Bean
    public AccountWithdrawService accountWithdrawService() {
        return new AccountWithdrawServiceImpl(accountDAO());
    }

    @Bean
    public AccountDepositService accountDepositService() {
        return new AccountDepositServiceImpl(accountDAO());
    }

    @Bean
    public BankCore bankCore() {
        return new BankCore(accountCreation());
    }

    @Bean
    public AccountBasicCLI accountBasicCLI() {
        return new AccountBasicCLI(myCLI(), bankCore(), accountListing());
    }

    private TransactionDAO transactionDAO() {
        return new MemoryTransactionDAO();
    }

    @Bean
    public TransactionWithdraw transactionWithdraw() {
        return new TransactionWithdraw(accountWithdrawService(), transactionDAO());
    }

    @Bean
    public TransactionWithdrawCLI transactionWithdrawCLI() {
        return new TransactionWithdrawCLI(transactionWithdraw(), myCLI(), accountListing());
    }

    @Bean
    public TransactionDeposit transactionDeposit() {
        return new TransactionDeposit(accountDepositService(), transactionDAO());
    }

    @Bean
    public TransactionDepositCLI transactionDepositCLI() {
        return new TransactionDepositCLI(transactionDeposit(), myCLI(), accountListing());
    }

}