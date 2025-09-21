package org.bankManagment.RepositoryMemory;

import org.bankManagment.Domain.Account;
import org.bankManagment.Repository.AccountRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class inMemoryAccountRepository implements AccountRepository {
    List<Account> accounts = new ArrayList<>();

    @Override
    public void createAccount(Account account) {
        accounts.add(account);
    }

    @Override
    public List<Account> getAllAccounts(UUID userId) {
        return accounts.stream().filter(acc -> acc.getUserId().equals(userId)).toList();
    }

    @Override
    public boolean withdraw(String accountId, BigDecimal amount) {
        Optional<Account> account = accounts.stream().filter(acc -> acc.getAccountId().equals(accountId)).findFirst();
        if (account.isEmpty()) return false;

        Account acc = account.get();
        if (acc.getBalance().compareTo(amount) <= 0) return false;
        acc.withdraw(amount);
        return true;
    }

    @Override
    public boolean desposit(String accountId, BigDecimal amount){
        Optional<Account> account = accounts.stream().filter(acc->acc.getAccountId().equals(accountId)).findFirst();

        if (account.isEmpty()) return false;

        Account acc = account.get();

        acc.disposit(amount);
        return true;
    }

}
