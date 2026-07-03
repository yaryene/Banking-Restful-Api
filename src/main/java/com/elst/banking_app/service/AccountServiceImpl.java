package com.elst.banking_app.service;

import com.elst.banking_app.dto.AccountDto;
import com.elst.banking_app.entity.Account;
import com.elst.banking_app.mapper.AccountMapper;
import com.elst.banking_app.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepo accountRepo;


    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepo.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }
}
