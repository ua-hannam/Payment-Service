package com.uahannam.java.service;

import com.uahannam.java.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PaymentTransactionService {

    private final TransactionRepository transactionRepository;

}
