package net.service.Implementations;

import net.exception.UserException;
import net.model.User;
import net.repository.UsersRepository;
import net.service.UsersService;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepo;

    public UsersServiceImpl(UsersRepository usersRepo) {
        this.usersRepo = usersRepo;
    }

    @Override
    public User save(User user) throws UserException {
        for (User u : usersRepo.findAll()) {
            if (user.getId() == u.getId())
                throw new UserException("User with id \'" + user.getId() + "\' already exists");
            if (user.getUsername() == u.getUsername())
                throw new UserException("User with username \'" + user.getUsername() + "\' already exists");
            if (user.getEmail() == u.getEmail())
                throw new UserException("User with email \'" + user.getEmail() + "\' already exists");
        }

        return usersRepo.save(user);
    }

    @Override
    public User findUserById(Long userId) throws UserException {
        return usersRepo.findById(userId)
                .orElseThrow(() -> new UserException("Can not find user with user id \'" + userId + "\'"));
    }

    @Override
    public User findUserByUsername(String username) throws UserException {
        return usersRepo.findByUsername(username)
                .orElseThrow(() -> new UserException("Can not find user with username \'" + username + "\'"));
    }

    @Override
    public User findUserByEmail(String email) throws UserException {
        return usersRepo.findByEmail(email)
                .orElseThrow(() -> new UserException("Can not find user with email \'" + email + "\'"));
    }

    @Override
    public void deleteUser(Long userId) {
        usersRepo.deleteById(userId);
    }
}