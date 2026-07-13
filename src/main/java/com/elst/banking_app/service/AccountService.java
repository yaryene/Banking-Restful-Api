package com.elst.banking_app.service;

import com.elst.banking_app.dto.AccountDto;
import com.elst.banking_app.entity.Account;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);
    AccountDto deposit(Long id, Double amount);
    AccountDto depositAmount(Long id, Double amount);
    AccountDto withdraw(Long id, Double amount);
    AccountDto withdrawAmount(Long id, Double amount);

   List<AccountDto>  getAllAccounts();
    void deleteAccount(Long id);
}
