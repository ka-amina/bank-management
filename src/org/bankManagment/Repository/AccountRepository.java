package org.bankManagment.Repository;

import org.bankManagment.Domain.Account;

import java.util.List;
import java.util.UUID;

public interface AccountRepository {
    void createAccount(Account account);
    List<Account> getAllAccounts(UUID userId);

}
