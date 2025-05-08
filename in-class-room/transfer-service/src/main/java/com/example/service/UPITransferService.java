package com.example.service;

import com.example.model.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// design & performance issues

// tight-coupling b/w dependent class and dependency class
// -> cant extend with new features easily
// unit testability issue
// -> dev & bug fix slow


// too many duplicate dependencies created & destroyed
// resource use high / responsiveness low

// why these issues ?

// dependent itself managing its own dependencies

// solution: dont create dependency in dependent home , lookup ( e.g factory pattern )

// problem : on every transfer, we are creating new JdbcAccountRepository object

// best solution: Dont create dependency in dependent home , Dont lookup on factory , inject by third-person
// aka inversion of control
// aka dependency injection


//@Component
@Service("upiTransferService")
public class UPITransferService implements TransferService {

    private AccountRepository accountRepository;

    @Autowired
    public UPITransferService(AccountRepository accountRepository) {
        // Constructor
        this.accountRepository = accountRepository;
        System.out.println("UPITransferService initialized");
    }

    @Transactional()
    public void transfer(int amount, String fromAccountNumber, String toAccountNumber) {

        //        System.out.println("SecurityCheck: hasPermission() called");
        System.out.println("UPITransferService: transfer() called");

        //JdbcAccountRepository accountRepository = new JdbcAccountRepository(); // Dont create

        // load 'from' account
        Account fromAccountObj = accountRepository.findById(fromAccountNumber).get();
        // load 'to' account
        Account toAccountObj = accountRepository.findById(toAccountNumber).get();
        System.out.println(fromAccountObj);
        System.out.println(toAccountObj);
        // check if 'from' account has sufficient balance
        if (fromAccountObj.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance in account " + fromAccountNumber);
        }
        // deduct amount from 'from' account
        fromAccountObj.setBalance(fromAccountObj.getBalance() - amount);

        // add amount to 'to' account
        toAccountObj.setBalance(toAccountObj.getBalance() + amount);

        // update 'from' account
        accountRepository.save(fromAccountObj);

        // update 'to' account
        accountRepository.save(toAccountObj);

        // print success message
        System.out.println("UPITransferService: transfer() completed successfully");


    }

    public void bm() {
//        System.out.println("SecurityCheck: hasPermission() called");
    }

}
