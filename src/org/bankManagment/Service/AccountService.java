package org.bankManagment.Service;

import org.bankManagment.Domain.Account;
import org.bankManagment.Domain.User;
import org.bankManagment.Repository.AccountRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.UUID;


public class AccountService {

    private AccountRepository accountRepository;
    private final Random random = new Random();

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public String generateID() {
        String id = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(id.charAt(random.nextInt(id.length())));
        }
        return "BA-" + sb;
    }

    public boolean createAccount(UUID ownerId) {
        String accountId = generateID();
        Account account = new Account(accountId, ownerId);
        accountRepository.createAccount(account);
        return true;
    }

    public List<Account> getAllAccounts(UUID userId) {
        return accountRepository.getAllAccounts(userId);
    }

    public boolean withdraw(String accountId, BigDecimal amount) {
        return accountRepository.withdraw(accountId, amount);
    }

    public boolean desposit(String accountId, BigDecimal amount) {
        return accountRepository.desposit(accountId, amount);
    }

    public String closeAccount(String accountId, UUID ownerId) {
        Account account = accountRepository.findByAccountId(accountId);

        if (account == null) {
            return "Account not found.";
        }

        if (!account.getUserId().equals(ownerId)) {
            return "You can only close your own accounts.";
        }

        if (!account.getStatus()) {
            return "Account is already closed.";
        }

        if (account.getBalance().compareTo(BigDecimal.ZERO) != 0) {
            return "Account balance must be zero to close the account. Current balance: " + account.getBalance();
        }

        if (accountRepository.closeAccount(accountId, ownerId)) {
            return "SUCCESS";
        } else {
            return "Failed to close account. Please try again.";
        }
    }

}
