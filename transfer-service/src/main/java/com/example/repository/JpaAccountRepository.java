package com.example.repository;

import com.example.model.Account;

public class JdbcAccountRepository  {

    public JdbcAccountRepository() {
        // Constructor
        System.out.println("JdbcAccountRepository initialized");
    }

    public Account loadAccount(String number) {
        // Simulate loading an account from a database
        return new Account(number,1000.00);
    }

    public void updateAccount(Account account) {
        // Simulate updating an account in a database
        System.out.println("Account updated: " + account);
    }

}
