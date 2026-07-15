package com.SmartBudget.expense_tracker.Service;

import com.SmartBudget.expense_tracker.Dto.UserDto;
import com.SmartBudget.expense_tracker.Model.User;
import com.SmartBudget.expense_tracker.Repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private  final EmailServiceImpl emailService;

    private final PasswordEncoder passwordEncoder;

    public UserDto registerUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword())); // encode here
        User user = toEntity(userDto);
        user.setActivationToken(UUID.randomUUID().toString());
        user = userRepository.save(user);

        // sending activation email

        String activationLink = "http://localhost:8080/api/v1/user/activate?token=" + user.getActivationToken();
        String subject = "Activate your account";
        String body = "Click the link to activate your account: " + activationLink;

        emailService.sendEmail(user.getEmail(), subject, body);
        return toDTo(user);
    }

   public boolean activateUser(String token) {
        User user = userRepository.findByActivationToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid activation token"));

        user.setIsActive(true); // clear the token after activation
        userRepository.save(user);
        return true;
    }

    public UserDto toDTo(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
              //  .password(passwordEncoder.encode(user.getPassword()))
                //we can encrypt password here also.
                .isActive(user.getIsActive())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public boolean isAccountActivated(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getIsActive() != null && user.getIsActive();
    }
//
//    public User getCurrentUser(String email) {
//        return userRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//    }

//    public UserDto login(String email, String Password) {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
//        if ((user.getIsActive() == null || !user.getIsActive())) {
//            throw new RuntimeException("Account not activated. Please check your email.");
//        }
//        if (!passwordEncoder.matches(Password, user.getPassword())) {
//            throw new RuntimeException("Invalid email or password");
//        }
//        return toDTo(user);
//    }

    public User toEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .isActive(userDto.getIsActive())
                .createdAt(userDto.getCreatedAt())
                .updatedAt(userDto.getUpdatedAt())
                .build();
    }

}
