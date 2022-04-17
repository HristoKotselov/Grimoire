package com.dnd.Grimoire.controller;

import com.dnd.Grimoire.model.Account;
import com.dnd.Grimoire.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("api/account")
@AllArgsConstructor
public class AccountController {

    @NonNull
    private final AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("id") final Long accountId) {
        try {
            final Optional<Account> account = accountService.getAccountById(accountId);
            if (account.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(account.get());
            } else {
                log.error("Account with id {} was not found", accountId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("Retrieving account with id {} failed because {}", accountId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody final Account account) {
        try {
            accountService.createAccount(account);
            return ResponseEntity.status(HttpStatus.OK).body(account);
        } catch (Exception e) {
            log.error("Adding account {} failed because {}", account, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Account>> getAllAccounts() {
        try {
            List<Account> allAccounts = accountService.getAllAccounts();
            return ResponseEntity.status(HttpStatus.OK).body(allAccounts);
        } catch (Exception e) {
            log.error("Retrieving account list failed because {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
