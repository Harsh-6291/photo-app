package com.start.springrestdemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.start.springrestdemo.models.Account;


public interface AccountRepository extends JpaRepository<Account, Long>{

    Optional<Account> findByEmail(String email);
    
}