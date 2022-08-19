package com.example.demo.infraestructure.controller;

import com.example.demo.application.dto.in.GeneralResponse;
import com.example.demo.application.dto.in.TransactionDto;
import com.example.demo.application.port.in.IMovimientoController;
import com.example.demo.application.services.interfaces.ITransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController implements IMovimientoController {

    private final ITransactionService transactionService;

    public MovimientoController(ITransactionService transactionService) {

        this.transactionService = transactionService;
    }

    @Override
    @PostMapping(value = "/v1/crear")
    public ResponseEntity<GeneralResponse<TransactionDto>> createTransaction(@RequestBody TransactionDto transactionDto) {

        GeneralResponse<TransactionDto> response = new GeneralResponse<>();
        TransactionDto result = this.transactionService.createTransaction(transactionDto);

        response.setCode(HttpStatus.CREATED.value());
        response.setMessage("Movimiento creado correctamente");
        response.setData(result);


        return ResponseEntity
                .created(null)
                .body(response);
    }

    @Override
    @PutMapping(value = "/v1/actualizar")
    public ResponseEntity<GeneralResponse<TransactionDto>> updateTransaction(@RequestBody TransactionDto transactionDto) {

        GeneralResponse<TransactionDto> response = new GeneralResponse<>();
        TransactionDto result = this.transactionService.updateTransaction(transactionDto);

        response.setCode(HttpStatus.CREATED.value());
        response.setMessage("Movimiento actualizado correctamente");
        response.setData(result);

        return ResponseEntity.ok()
                .body(response);
    }

    @Override
    @DeleteMapping(value = "/v1/eliminar/{idTransaction}")
    public ResponseEntity<GeneralResponse<Boolean>> deleteTransaction(@PathVariable Integer idTransaction) {

        GeneralResponse<Boolean> response = new GeneralResponse<>();
        Boolean result = this.transactionService.deleteTransaction(idTransaction);

        response.setCode(HttpStatus.OK.value());
        response.setMessage("Movimiento eliminado correctamente");
        response.setData(result);

        return ResponseEntity.ok()
                .body(response);
    }

    @Override
    @GetMapping(value = "/v1/reportes")
    public ResponseEntity<GeneralResponse> generateReport(@RequestParam String dateInit,
                                                          @RequestParam String dateEnd) {

        GeneralResponse result = this.transactionService.generateReportByDates(dateInit, dateEnd);

        return ResponseEntity.ok()
                .body(result);
    }
}
