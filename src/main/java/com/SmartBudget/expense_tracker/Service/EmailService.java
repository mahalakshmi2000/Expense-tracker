package com.SmartBudget.expense_tracker.Service;

public interface EmailService {
    void sendEmail(String toEmail, String subject, String body);
}
