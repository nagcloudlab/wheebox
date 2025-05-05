package com.example.repository;

import com.example.model.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


//@Component
@Repository
@Qualifier("jpa")
public class JpaAccountRepository implements AccountRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public JpaAccountRepository() {
        // Constructor
        System.out.println("JpaAccountRepository initialized");
    }

    //    @Transactional(readOnly = true)
    public Account loadAccount(String number) {
        System.out.println("Loading account with number: " + number);
        return entityManager.find(Account.class, number);
    }

    public void updateAccount(Account account) {
        System.out.println("Updating account: " + account);
        entityManager.merge(account);
        System.out.println("Account updated: " + account);
    }

}
