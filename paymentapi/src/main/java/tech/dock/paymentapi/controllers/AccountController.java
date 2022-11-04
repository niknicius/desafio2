package tech.dock.paymentapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.dock.paymentapi.core.base.BaseController;
import tech.dock.paymentapi.core.base.BaseService;
import tech.dock.paymentapi.core.exception.BusinessException;
import tech.dock.paymentapi.dto.AccountDTO;
import tech.dock.paymentapi.dto.TransactionDTO;
import tech.dock.paymentapi.models.Account;
import tech.dock.paymentapi.services.AccountService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountController extends BaseController<Account, Integer, AccountDTO> {

    private final AccountService service;

    public AccountController(final AccountService accountService) {
        this.service = accountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getById(@PathVariable("id") Integer id) throws BusinessException {
        return new ResponseEntity<>(mapTo(this.service.getOne(id), AccountDTO.class), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<AccountDTO> create(@RequestBody @Valid AccountDTO dto) throws BusinessException {
        Account account = this.service.insert(mapTo(dto, Account.class));
        return new ResponseEntity<>(mapTo(account, AccountDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> update(@PathVariable("id") Integer id,
                                            @RequestBody @Valid AccountDTO dto) {
        Account account = this.service.update(id, mapTo(dto, Account.class));
        return new ResponseEntity<>(mapTo(account, AccountDTO.class), HttpStatus.OK);
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<Map<String, BigDecimal>> deposit(@PathVariable("id") Integer id,
                                                           @RequestBody Map<String, BigDecimal> body)
            throws BusinessException {
        return new ResponseEntity<>(this.service.deposit(id, body.get("amount")), HttpStatus.OK);
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<Map<String, BigDecimal>> getBalance(@PathVariable("id") Integer id) throws BusinessException {
        return new ResponseEntity<>(this.service.getBalance(id), HttpStatus.OK);
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<Map<String, BigDecimal>> withdraw(@PathVariable("id") Integer id,
                                                            @RequestBody Map<String, BigDecimal> body)
            throws BusinessException {
        return new ResponseEntity<>(this.service.withdraw(id, body.get("amount")), HttpStatus.OK);

    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<Map<String, Boolean>> deactivate(@PathVariable("id") Integer id) throws BusinessException{
        return new ResponseEntity<>(this.service.deactivate(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionDTO>> getTransactions(@PathVariable("id") Integer id,
                                                                @RequestParam(required = false) String startDate,
                                                                @RequestParam(required = false) String endDate)
            throws BusinessException, ParseException {
        return new ResponseEntity<>(toList(this.service.getTransactions(id, startDate, endDate), TransactionDTO.class), HttpStatus.OK);

    }

    @Override
    protected BaseService<Account, Integer> getService() {
        return service;
    }
}
