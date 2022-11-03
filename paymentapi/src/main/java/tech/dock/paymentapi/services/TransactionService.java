package tech.dock.paymentapi.services;

import org.springframework.stereotype.Service;
import tech.dock.paymentapi.core.base.BaseRepository;
import tech.dock.paymentapi.core.base.BaseService;
import tech.dock.paymentapi.models.Transaction;
import tech.dock.paymentapi.repository.TransactionRepository;

@Service
public class TransactionService extends BaseService<Transaction, Integer> {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    @Override
    protected BaseRepository<Transaction, Integer> getRepository() {
        return this.transactionRepository;
    }
}
