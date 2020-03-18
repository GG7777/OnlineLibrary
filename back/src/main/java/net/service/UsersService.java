package net.service;

import net.exception.UserException;
import net.model.User;

public interface UsersService {
    User save(User user) throws UserException;
    User findUserById(Long userId) throws UserException;
    User findUserByUsername(String username) throws UserException;
    User findUserByEmail(String email) throws UserException;
    void deleteUser(Long userId);
}
