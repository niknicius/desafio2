package tech.dock.paymentapi.services;

import org.springframework.stereotype.Service;
import tech.dock.paymentapi.core.base.BaseRepository;
import tech.dock.paymentapi.core.base.BaseService;
import tech.dock.paymentapi.models.Account;
import tech.dock.paymentapi.repository.AccountRepository;

@Service
public class AccountService extends BaseService<Account, Integer> {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    protected BaseRepository<Account, Integer> getRepository() {
        return this.accountRepository;
    }
}
