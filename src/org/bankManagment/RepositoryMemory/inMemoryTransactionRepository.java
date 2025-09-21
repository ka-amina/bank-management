package org.bankManagment.RepositoryMemory;

import org.bankManagment.Domain.Transaction;
import org.bankManagment.Repository.TransactionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class inMemoryTransactionRepository implements TransactionRepository {
    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public void save(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public List<Transaction> findByAccountId(UUID ownerId) {
        return transactions.stream().filter(T -> T.getOwnerId().equals(ownerId)).sorted((t1, t2) -> t1.getTimestamp().compareTo(t2.getTimestamp())).toList();
    }
}
