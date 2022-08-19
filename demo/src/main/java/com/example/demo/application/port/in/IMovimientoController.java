package com.example.demo.application.port.in;

import com.example.demo.application.dto.in.GeneralResponse;
import com.example.demo.application.dto.in.TransactionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface IMovimientoController {

    ResponseEntity<GeneralResponse<TransactionDto>> createTransaction(@RequestBody TransactionDto transactionDto);

    ResponseEntity<GeneralResponse<TransactionDto>> updateTransaction(@RequestBody TransactionDto transactionDto);

    ResponseEntity<GeneralResponse<Boolean>> deleteTransaction(@PathVariable Integer idTransaction);

    ResponseEntity<GeneralResponse> generateReport(@RequestParam String dateInit,
                                                   @RequestParam String dateEnd);
}
