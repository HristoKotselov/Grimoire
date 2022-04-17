package com.dnd.Grimoire.repository;

import com.dnd.Grimoire.model.Account;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @NonNull List<Account> findAll();
}
