package com.slmndr.endpoints;


import com.slmndr.entities.Transaction;
import com.slmndr.services.TransactionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class TransactionsController {

    private final TransactionService transactionService;

    public TransactionsController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/v1/transactions/{account_id}")
    public List<Transaction> getAllTransactionsByUserId(@PathVariable("account_id") final Integer accountId) {
        return this.transactionService.findAll(accountId);
    }
}
