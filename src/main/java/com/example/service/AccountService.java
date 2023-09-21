package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AccountService {
    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }


    //1R
    public Account registerAccount(Account account){
        if (accountRepository.findAccountByUsername(account.getUsername()) == null){
            accountRepository.save(account);
            return  account;
        }
        return null;
    }

    //2R
    public Account loginAccount(Account account){
        return accountRepository.findAccountByUsernameAndPassword(account.getUsername(), account.getPassword());
    }
}
