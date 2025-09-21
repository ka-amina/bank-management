package org.bankManagment.Repository;

import org.bankManagment.Domain.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository {
    void save(Transaction transaction);
    List<Transaction> findByAccountId(UUID accountId);
}
