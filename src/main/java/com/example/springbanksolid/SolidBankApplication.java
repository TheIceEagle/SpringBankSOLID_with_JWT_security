package com.example.springbanksolid;

import com.example.springbanksolid.Services.AccountBasicCLI;
import com.example.springbanksolid.Services.TransactionDepositCLI;
import com.example.springbanksolid.Services.TransactionWithdrawCLI;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@SpringBootApplication
@AllArgsConstructor
public class SolidBankApplication implements CommandLineRunner {
    private final AccountBasicCLI accountBasicCLI;
    private final TransactionWithdrawCLI transactionWithdrawCLI;
    private final TransactionDepositCLI transactionDepositCLI;

    public static void main(String[] args) {
        SpringApplication.run(SolidBankApplication.class);
    }
    @Override
    public void run(String... arg0) {
        printManual();
        Scanner scanner = new Scanner(System.in);
        String clientID = "1";
        while (true) {
            System.out.print("> ");
            String number = scanner.nextLine();
            switch (number) {
                case "1" -> accountBasicCLI.getAccounts(clientID);
                case "2" -> accountBasicCLI.createAccountRequest(clientID);
                case "3" -> transactionDepositCLI.depositMoney(clientID);
                case "4" -> transactionWithdrawCLI.withdrawMoney(clientID);
                case "5" -> printNotImplemented();
                case "6" -> printManual();
                case "7" -> exit();
                default -> System.out.println("Please enter valid number");
            }
        }
    }

    public static void printNotImplemented() {
        System.out.println("This feature is not implemented in the UML diagram");
    }

    private static void printManual() {
        try {
            File read_file = new File("src/main/resources/manual.txt");
            Scanner scanner = new Scanner(read_file);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void exit() {
        System.out.println("Application Closed");
        System.exit(0);
    }
}
