package com.example.springbanksolid;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SecurityScheme(name = "basicauth", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER, bearerFormat = "JWT")
@SpringBootApplication
public class SolidBankApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SolidBankApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.printf("Application SolidBank has started.");
    }
}
