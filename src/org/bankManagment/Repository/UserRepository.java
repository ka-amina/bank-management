package org.bankManagment.Repository;

import org.bankManagment.Domain.User;

import java.util.Optional;

public interface UserRepository {
    void createUser(User user);

    Optional<User> findByEmail(String email);

    boolean updateProfile(User user, String userName, String email);
}
