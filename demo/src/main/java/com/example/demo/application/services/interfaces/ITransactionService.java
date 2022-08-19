package com.example.demo.application.services.interfaces;

import com.example.demo.application.dto.in.GeneralResponse;
import com.example.demo.application.dto.in.TransactionDto;

public interface ITransactionService {

    TransactionDto createTransaction(TransactionDto transactionDto);

    TransactionDto updateTransaction(TransactionDto transactionDto);

    Boolean deleteTransaction(Integer idTransaction);

    GeneralResponse generateReportByDates(String dateInit, String dateEnd);
}
