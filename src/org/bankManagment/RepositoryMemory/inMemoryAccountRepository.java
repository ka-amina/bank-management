package org.bankManagment.RepositoryMemory;

import org.bankManagment.Domain.Account;
import org.bankManagment.Repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;
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

}
