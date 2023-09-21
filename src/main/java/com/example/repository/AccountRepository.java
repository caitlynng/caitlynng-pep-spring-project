package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    /**
     * 1R: This method will find a record with the specified username in the database
     * @param username
     * @return an instance of the Account entity associated with that username if success, otherwise return null
     */
    Account findAccountByUsername(String username);

    /**
     * 2R: This method will find a record with the specified username and password in the database
     * @param username and password
     * @return an instance of the Account entity if success, otherwise return null
     */
    Account findAccountByUsernameAndPassword(String username, String password);
}
