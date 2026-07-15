package com.SmartBudget.expense_tracker.Controller;

import com.SmartBudget.expense_tracker.Dto.TransactionDto;
import com.SmartBudget.expense_tracker.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private  TransactionService transactionService;

    @PostMapping("/add-transaction")
    public TransactionDto addTransaction(@RequestBody TransactionDto transaction,
                                      @RequestParam("userId") Long userId) {
        TransactionDto transactionSaved =  transactionService.addTransaction(userId, transaction);
        return ResponseEntity.ok(transactionSaved).getBody();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TransactionDto>> getTransactionsByUser(@PathVariable Long userId) {
        List<TransactionDto> transactions = transactionService.getTransactionsByUser(userId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/All-transactions")
    public String getTransactions(@RequestParam("userId") Long userId) {
        transactionService.getTransactions(userId);
        return "Transactions";
    }

}
