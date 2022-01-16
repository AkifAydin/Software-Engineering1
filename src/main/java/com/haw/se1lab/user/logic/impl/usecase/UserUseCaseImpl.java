package com.haw.se1lab.user.logic.impl.usecase;

import com.haw.se1lab.user.common.api.datatype.exception.UserNotFoundException;
import com.haw.se1lab.user.dataaccess.api.entity.User;
import com.haw.se1lab.user.dataaccess.api.repo.UserRepository;
import com.haw.se1lab.user.logic.api.usecase.UserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUseCaseImpl implements UserUseCase {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public boolean checkPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    @Override
    public String createSession(User user) {
        //TODO remove dummy session impl
        return "gsdfgGZZuzgu3uf4";
    }
}
