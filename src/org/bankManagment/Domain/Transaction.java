package org.bankManagment.Domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class Transaction {

    public enum TransactionType {
        WITHDRAW, DESPOSIT
    }

    private UUID id;
    private UUID ownerId;
    private String accountId;
    private Instant timestamp;
    private TransactionType type;
    private BigDecimal amount;
    private String counterPartyAccountId;

    public Transaction( UUID ownerId, String accountId, BigDecimal amount, String counterPartyAccountId, TransactionType type) {
        this.id = UUID.randomUUID();
        this.ownerId = ownerId;
        this.accountId = accountId;
        this.amount = amount;
        this.counterPartyAccountId = counterPartyAccountId;
        this.type = type;
        this.timestamp = Instant.now();
    }

    public UUID getOwnerId() {
        return this.ownerId;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }

    @Override
    public String toString() {
        return "this done in -> [" + timestamp + "] and its type is -> " +
                "[" + type + "] and the amount is ->[" + amount + (counterPartyAccountId != null ? " -> " +
                "" + counterPartyAccountId : "]");
    }

}
