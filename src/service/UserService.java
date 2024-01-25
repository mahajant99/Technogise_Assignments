package service;

import model.User;
import repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(String username, String password) {
        userRepository.addUser(username, password);
    }
    

    public boolean signIn(String username, String password) {
        return userRepository.isUserExists(username, password);
    }
}
