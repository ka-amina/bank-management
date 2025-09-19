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
}
