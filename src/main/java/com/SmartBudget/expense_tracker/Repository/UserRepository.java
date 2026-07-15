package com.SmartBudget.expense_tracker.Repository;

import com.SmartBudget.expense_tracker.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
Optional<User> findByEmail(String email);
Optional<User> findByActivationToken(String token);

//Optional<User> findByUsername(String username);
//boolean existsByEmail(String email);
//boolean existsByUsername(String username);
}
