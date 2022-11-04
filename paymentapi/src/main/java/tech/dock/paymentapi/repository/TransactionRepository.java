package tech.dock.paymentapi.repository;

import tech.dock.paymentapi.core.base.BaseRepository;
import tech.dock.paymentapi.models.Transaction;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends BaseRepository<Transaction, Integer> {

    List<Transaction> findAllByAccountIdOrderById(Integer accountId);

    List<Transaction> findAllByAccountIdAndTransactionDate(Integer accountId, Date transactionDate);

    List<Transaction> findAllByAccountIdAndTransactionDateBetween(
            Integer accountID,
            Date dateStart,
            Date dateEnd);
}
