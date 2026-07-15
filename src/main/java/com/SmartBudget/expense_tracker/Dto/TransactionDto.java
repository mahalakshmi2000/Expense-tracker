package com.SmartBudget.expense_tracker.Dto;

import com.SmartBudget.expense_tracker.Model.PaymentMode;
import com.SmartBudget.expense_tracker.Model.TransactionType;
import com.SmartBudget.expense_tracker.Model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TransactionDto {
    private TransactionType type;
    private String category;
    private Double amount;
    private String description;
    private PaymentMode paymentMode;
    private LocalDateTime transactionDate;
    private User user;



    public void setUser(User user) {
        this.user = user;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }


}
