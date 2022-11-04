package tech.dock.paymentapi.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.dock.paymentapi.core.base.BaseRepository;
import tech.dock.paymentapi.core.base.BaseService;
import tech.dock.paymentapi.models.Transaction;
import tech.dock.paymentapi.repository.TransactionRepository;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService extends BaseService<Transaction, Integer> {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    @Transactional(readOnly = true)
    public List<Transaction> getAccountTransactionsBetweenDates(Integer accountId, Date startDate, Date endDate) {
        return this.transactionRepository.findAllByAccountIdAndTransactionDateBetween(accountId, startDate, endDate);
    }

    @Transactional(readOnly = true)
    public List<Transaction> getAccountTransactionsToday(Integer accountID){
        return this.transactionRepository.findAllByAccountIdAndTransactionDate(accountID, new Date());
    }

    @Transactional(readOnly = true)
    public List<Transaction> getAllAccountTransactions(Integer accountID){
        return this.transactionRepository.findAllByAccountIdOrderById(accountID);
    }

    @Override
    protected BaseRepository<Transaction, Integer> getRepository() {
        return this.transactionRepository;
    }
}
