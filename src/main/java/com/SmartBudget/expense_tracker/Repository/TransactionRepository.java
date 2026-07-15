package com.SmartBudget.expense_tracker.Repository;

import com.SmartBudget.expense_tracker.Dto.TransactionDto;
import com.SmartBudget.expense_tracker.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<TransactionDto> findByUserId(Long userId);
}
