package org.bankManagment.Service;

import org.bankManagment.Domain.User;
import org.bankManagment.Repository.UserRepository;

import java.util.Optional;

public class AuthService {
    private final UserRepository userRepository;
    private User loggedUser;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean register(String fullName, String email, String password) {
        if (password.length() < 8) return false;
        if (userRepository.findByEmail(email).isPresent()) return false;

        User user = new User(fullName, email, password);
        userRepository.createUser(user);
        return true;
    }

    public boolean login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            loggedUser = user.get();
            return true;
        }
        return false;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void logout() {
        loggedUser = null;
    }

    public boolean updateProfil(String userName, String email) {
        userRepository.updateProfile(loggedUser, userName, email);
        return true;
    }

    public boolean changePassword(String newPassword) {
        User user = getLoggedUser();
        if (user != null) {
            user.setPassword(newPassword);
            userRepository.changePassword(user, newPassword);
            return true;
        }
        return false;
    }

}
