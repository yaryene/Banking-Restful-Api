package com.elst.banking_app.service;

import com.elst.banking_app.dto.AccountDto;
import com.elst.banking_app.entity.Account;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
}
