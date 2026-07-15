package com.SmartBudget.expense_tracker.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String password;
    private String firstName;
    private String lastName;

    @CreationTimestamp // automatically sets the timestamp when the entity is created
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @CreationTimestamp
    private LocalDateTime updatedAt;

    @Column(nullable = false, unique = true)
    private String email;
    private Boolean isActive;

    private String activationToken;

    @PrePersist
    public void prePersist() {
        if(this.isActive == null){
            boolean isActive = false;
        }
    }
}
