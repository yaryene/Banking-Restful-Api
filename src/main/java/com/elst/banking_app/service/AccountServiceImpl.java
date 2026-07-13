package com.elst.banking_app.service;

import com.elst.banking_app.dto.AccountDto;
import com.elst.banking_app.dto.WithdrawReq;
import com.elst.banking_app.entity.Account;
import com.elst.banking_app.mapper.AccountMapper;
import com.elst.banking_app.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepo accountRepo;


    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepo.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, Double amount) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        Account savedAccount = accountRepo.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto depositAmount(Long id, Double amount) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        Double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepo.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, Double amount) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        Account savedAccount = accountRepo.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdrawAmount(Long id, Double amount) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        Account savedAccount = accountRepo.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepo.findAll();
        return accounts.stream().map(AccountMapper::mapToAccountDto).toList();
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        accountRepo.delete(account);
    }
}
