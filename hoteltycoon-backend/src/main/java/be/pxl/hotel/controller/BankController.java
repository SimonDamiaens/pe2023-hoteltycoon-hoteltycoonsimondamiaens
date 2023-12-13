package be.pxl.hotel.controller;

import be.pxl.hotel.api.request.CreateTransactionRequest;
import be.pxl.hotel.api.response.TransactionsOverviewDTO;
import be.pxl.hotel.api.response.WalletDTO;
import be.pxl.hotel.domain.UnsufficientMoneyException;
import be.pxl.hotel.service.BankService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping()
    public WalletDTO getWallet() {
        return bankService.getWallet();
    }

    @PostMapping("/transactions")
    public ResponseEntity<String> createTransaction(@RequestBody CreateTransactionRequest createTransactionRequest) {
        try {
            bankService.createTransaction(createTransactionRequest.getAmount(),createTransactionRequest.getTransactionType(),createTransactionRequest.getDescription());
            return ResponseEntity.status(HttpStatus.CREATED).body("Transaction created successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid transaction type");
        } catch (UnsufficientMoneyException e) {
            return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body("Insufficient funds in the wallet");
        }
    }

    @GetMapping("/transactions/overview")
    public TransactionsOverviewDTO getTransactionsOverview() {
        return bankService.getTransactionsOverview();
    }
}
