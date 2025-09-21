package org.bankManagment.RepositoryMemory;

import org.bankManagment.Domain.User;
import org.bankManagment.Repository.UserRepository;

import java.util.*;

public class inMemoryUserRepository implements UserRepository {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public void createUser(User user) {
        users.put(user.getEmail(), user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(users.get(email));
    }

    @Override
    public boolean updateProfile(User user, String userName, String email) {
        if (!users.containsKey(user.getEmail())) {
            return false;
        }
        users.remove(user.getEmail());
        user.setFullName(userName);
        user.setEmail(email);
        users.put(user.getEmail(), user);

        return true;
    }

    @Override
    public boolean changePassword(User user, String newPassword) {
        if (user != null && users.containsKey(user.getEmail())) {
            user.setPassword(newPassword);
            users.put(user.getEmail(), user);
            return true;
        }
        return false;
    }

}
