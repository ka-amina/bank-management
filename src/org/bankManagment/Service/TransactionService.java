package org.bankManagment.Service;

import org.bankManagment.Domain.Transaction;
import org.bankManagment.Repository.TransactionRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void addDesposit(UUID ownerId, String accountId, BigDecimal amount) {
        Transaction DespositAmount = new Transaction(ownerId, accountId, amount, null, Transaction.TransactionType.DESPOSIT);
        transactionRepository.save(DespositAmount);
    }

    public void addWithdraw(UUID ownerId, String accountId, BigDecimal amount) {
        Transaction WithdrawAmount = new Transaction(ownerId, accountId, amount, null, Transaction.TransactionType.WITHDRAW);
        transactionRepository.save(WithdrawAmount);
    }

    public List<Transaction> getAllTransactions(UUID ownerId) {
        return transactionRepository.findByAccountId(ownerId);
    }
}
