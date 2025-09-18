package org.bankManagment.Domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Account {
    private String accountId;
    private UUID userId;
    private BigDecimal balance;
    private LocalDateTime createdAt;
    private boolean isActive;

    public Account(String accountId, UUID userId) {
        this.accountId = accountId;
        this.userId = userId;
        this.balance = BigDecimal.valueOf(0);
        this.isActive = true;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getUserId() {
        return this.userId;
    }

    public String getAccountId() {
        return this.accountId;
    }

    public boolean getStatus() {
        return this.isActive;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void disposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }

    public void closeAccount() {
        this.isActive = false;
    }

    @Override
    public String toString() {
        return "{accountId= " + accountId + ", userId=" + userId + ", balance=" + balance + ", createdAt=" + createdAt + ", isActive=" + (isActive ? "Active" : "Not active") + "}";
    }
}
