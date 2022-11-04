package tech.dock.paymentapi.services;

import org.springframework.stereotype.Service;
import tech.dock.paymentapi.core.base.BaseRepository;
import tech.dock.paymentapi.core.base.BaseService;
import tech.dock.paymentapi.core.exception.BusinessException;
import tech.dock.paymentapi.models.Account;
import tech.dock.paymentapi.models.Transaction;
import tech.dock.paymentapi.repository.AccountRepository;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Account Service Class
 *
 * @author Vinnicius Santos - vinnicius.santos@dcx.ufpb.br
 */
@Service
public class AccountService extends BaseService<Account, Integer> {

    private static final String BALANCE = "balance";
    private static final String ENTITY_NOT_FOUND = "entity.not.found";

    private final AccountRepository accountRepository;

    private final TransactionService transactionService;

    public AccountService(AccountRepository accountRepository,  TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

    @Override
    protected Account preInsert(Account model) throws BusinessException {
        model.setCreationDate(new Date());
        return super.preInsert(model);
    }

    public Map<String, BigDecimal> deposit(Integer id, BigDecimal amount) throws BusinessException {
        Account account = accountRepository.findById(id).orElseThrow(() -> new BusinessException("entity not found"));
        if (Boolean.FALSE.equals(account.getActive())){
            throw new BusinessException("account.not.active");
        }
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(amount)
                .transactionDate(new Date())
                .build();
        transactionService.insert(transaction);

        return Map.of(BALANCE, account.getBalance());
    }

    public Map<String, BigDecimal> getBalance(Integer id) throws BusinessException {
        Account account = accountRepository.findById(id).orElseThrow(() -> new BusinessException(ENTITY_NOT_FOUND));
        return Map.of(BALANCE, account.getBalance());
    }

    public Map<String, BigDecimal> withdraw(Integer id, BigDecimal amount) throws BusinessException {
        Account account = accountRepository.findById(id).orElseThrow(() -> new BusinessException(ENTITY_NOT_FOUND));

        if (Boolean.FALSE.equals(account.getActive())){
            throw new BusinessException("account.not.active");
        }

        var withdrawSum = transactionService.getAccountTransactionsToday(id)
                .stream()
                .filter(t -> t.getAmount().compareTo(BigDecimal.ZERO) < 0)
                .map(t -> t.getAmount().abs())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if(withdrawSum.add(amount).compareTo(account.getWithdrawLimit()) > 0){
            throw new BusinessException("account.withdrawn.limit.reached");
        }

        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(amount.negate())
                .transactionDate(new Date())
                .build();
        transactionService.insert(transaction);

        return Map.of(BALANCE, account.getBalance());
    }

    public Map<String, Boolean> deactivate(Integer id) throws BusinessException{
        Account account = accountRepository.findById(id).orElseThrow(() -> new BusinessException(ENTITY_NOT_FOUND));

        if (Boolean.FALSE.equals(account.getActive())){
            throw new BusinessException("account.already.deactivated");
        }

        account.setActive(false);
        accountRepository.save(account);
        return Map.of("result", true);
    }

    public List<Transaction> getTransactions(Integer id, String startDate, String endDate) throws BusinessException, ParseException {
        Account account = accountRepository.findById(id).orElseThrow(() -> new BusinessException(ENTITY_NOT_FOUND));
        if (Boolean.FALSE.equals(account.getActive())){
            throw new BusinessException("account.deactivated");
        }

        if(startDate != null && endDate != null) {
            return this.transactionService.getAccountTransactionsBetweenDates(id,
                    new SimpleDateFormat("yyyy-MM-dd").parse(startDate),
                    new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
        }else{
            return this.transactionService.getAllAccountTransactions(id);
        }
    }

    @Override
    protected BaseRepository<Account, Integer> getRepository() {
        return this.accountRepository;
    }
}
