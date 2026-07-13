package com.elst.banking_app.controller;

import com.elst.banking_app.dto.AccountDto;
import com.elst.banking_app.dto.request.DepositReq;
import com.elst.banking_app.dto.request.WithdrawReq;
import com.elst.banking_app.dto.response.ApiResponse;
import com.elst.banking_app.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        AccountDto savedAccount = accountService.createAccount(accountDto);
        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        AccountDto account = accountService.getAccountById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
    @GetMapping("/{id}/details")
    public ResponseEntity<ApiResponse<AccountDto>> getAccountByIdWithDetails(@PathVariable Long id) {
        AccountDto account = accountService.getAccountById(id);
        ApiResponse<AccountDto> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                true,
                null,
                account,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
    @PatchMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @Valid @RequestBody DepositReq req) {
        AccountDto account = accountService.deposit(id, req.getAmount());
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PatchMapping("/{id}/deposit_amount")
    public ResponseEntity<AccountDto> depositAmount(@PathVariable Long id, @Valid @RequestBody Map<String, Double> req) {
        Double amount = req.get("amount");
        AccountDto account = accountService.depositAmount(id, amount);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @Valid @RequestBody WithdrawReq req) {
        AccountDto account = accountService.withdraw(id, req.getAmount());
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping("/{id}/withdraw_amount")
    public ResponseEntity<AccountDto> withdrawAmount(@PathVariable Long id, @Valid @RequestBody Map<String, Double> req) {
        Double amount = req.get("amount");
        AccountDto account = accountService.withdrawAmount(id, amount);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        ApiResponse<Void> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                true,
                "Account deleted successfully",
                null,
                LocalDateTime.now()
        );
        return  ResponseEntity.ok(response);
    }
}
