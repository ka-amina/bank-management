package org.bankManagment.Service;

import org.bankManagment.Domain.User;
import org.bankManagment.Repository.UserRepository;

import java.util.Optional;

public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean register(String fullName, String email, String password) {
        if (password.length() < 8) return false;

        User user = new User(fullName, email, password);
        userRepository.createUser(user);
        return true;
    }
}
