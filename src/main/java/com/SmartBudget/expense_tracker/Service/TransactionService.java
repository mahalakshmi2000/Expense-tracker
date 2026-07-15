package com.SmartBudget.expense_tracker.Service;

import com.SmartBudget.expense_tracker.Dto.TransactionDto;
import com.SmartBudget.expense_tracker.Model.PaymentMode;
import com.SmartBudget.expense_tracker.Model.Transaction;
import com.SmartBudget.expense_tracker.Model.TransactionType;
import com.SmartBudget.expense_tracker.Model.User;
import com.SmartBudget.expense_tracker.Repository.TransactionRepository;
import com.SmartBudget.expense_tracker.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }
    public TransactionDto addTransaction(Long userId, TransactionDto transactionDto) {
//        if (transaction == null) {
//            logger.error("Transaction object is null");
//            throw new IllegalArgumentException("Transaction cannot be null");
//        }
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Transaction transaction = new Transaction();
            transaction.setUser(user);
            transaction.setType(transactionDto.getType());
            transaction.setCategory(transactionDto.getCategory());
            transaction.setAmount(transactionDto.getAmount());
            transaction.setDescription(transactionDto.getDescription());
            transaction.setPaymentMode(transactionDto.getPaymentMode());

            // if client didn't send date, use current time
            transaction.setTransactionDate(transactionDto.getTransactionDate() != null ?
                    transactionDto.getTransactionDate() : LocalDateTime.now());

            Transaction saved = transactionRepository.save(transaction);
            logger.info("Transaction added successfully for userId: {}", userId);
            return mapToDto(saved);
        } catch (Exception e) {
            // Handle exception (log, rethrow, or return null/custom response)
            throw new RuntimeException("Failed to add transaction: " + e.getMessage());
        }
    }

    public List<TransactionDto> getTransactions(Long userId) {
        List<TransactionDto> transactions = transactionRepository.findByUserId(userId);
        logger.info("Fetched {} transactions for userId: {}", transactions.size(), userId);
        return transactions;
    }

    public List<TransactionDto> getTransactionsByUser(Long userId) {
        return transactionRepository.findByUserId(userId);
    }
    public TransactionDto mapToDto(Transaction transaction) {
        TransactionDto dto = new TransactionDto();
        dto.setType(TransactionType.valueOf(transaction.getType().name()));
        dto.setCategory(transaction.getCategory());
        dto.setAmount(transaction.getAmount());
        dto.setDescription(transaction.getDescription());
        dto.setPaymentMode(PaymentMode.valueOf(transaction.getPaymentMode().name()));
        dto.setTransactionDate(transaction.getTransactionDate());
        dto.setUser(transaction.getUser());

        return dto;
    }
}
