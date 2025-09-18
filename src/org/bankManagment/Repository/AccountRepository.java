package org.bankManagment.Repository;

import org.bankManagment.Domain.Account;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface AccountRepository {
    void createAccount(Account account);
    List<Account> getAllAccounts(UUID userId);
    boolean withdraw(String accountId, BigDecimal amount);
    boolean desposit(String accountId, BigDecimal amount);
    boolean closeAccount(String accountId, UUID ownerId);
    Account findByAccountId(String accountId);


}
