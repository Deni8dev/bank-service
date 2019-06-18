package com.slmndr.services;

import com.slmndr.entities.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> findAll(final Integer accountId);
}
