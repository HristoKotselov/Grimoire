package com.dnd.Grimoire.service;

import com.dnd.Grimoire.model.Account;
import com.dnd.Grimoire.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {

    @NonNull
    private final AccountRepository accountRepository;

    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccount(Account account) {
        accountRepository.delete(account);
    }

    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }

    public long getTotalAccountCount() {
        return accountRepository.count();
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
