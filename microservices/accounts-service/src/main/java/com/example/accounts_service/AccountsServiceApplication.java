package com.example.accounts_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@Data
class Account {
	private String number;
	private double balance;
}

@RestController
class AccountApiController {
	
	@GetMapping("/api/accounts/{accountNumber}")
	public Account getAccount(@PathVariable String accountNumber) {
		Account account = new Account();
		account.setNumber(accountNumber);
		account.setBalance(1000.00);
		return account;
	}

}


@SpringBootApplication
public class AccountsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsServiceApplication.class, args);
	}

}
