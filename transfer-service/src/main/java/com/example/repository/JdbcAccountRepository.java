package com.example.repository;

import com.example.model.Account;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Component
@Repository
@Qualifier("jdbc")
public class JdbcAccountRepository implements AccountRepository  {

    public JdbcAccountRepository() {
        // Constructor
        System.out.println("JdbcAccountRepository initialized");
    }

    public Account loadAccount(String number) {
        // Simulate loading an account from a database
        return new Account(number,1000);
    }

    public void updateAccount(Account account) {
        // Simulate updating an account in a database
        System.out.println("Account updated: " + account);
    }

}
