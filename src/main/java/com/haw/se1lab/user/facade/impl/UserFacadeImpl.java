package com.haw.se1lab.user.facade.impl;

import com.haw.se1lab.user.common.api.datatype.exception.UserNotFoundException;
import com.haw.se1lab.user.common.api.datatype.exception.WrongPasswordException;
import com.haw.se1lab.user.dataaccess.api.entity.User;
import com.haw.se1lab.user.facade.api.UserFacade;
import com.haw.se1lab.user.logic.api.usecase.UserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserUseCase userUseCase;

    @PostMapping(path = "/login")
    @Override
    public User login(@RequestBody String email, @RequestBody String password) throws UserNotFoundException, WrongPasswordException {
        User u = userUseCase.findUserByEmail(email);
        if(userUseCase.checkPassword(u, password)){
            userUseCase.createSession(u);

            return u;
        }else{
            throw new WrongPasswordException(email);
        }
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User not found.")
    private void handleUserNotFoundException() {
        // nothing to do -> just set the HTTP response status as defined in @ResponseStatus
    }

    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Wrong password.")
    private void handleWrongPasswordException() {
        // nothing to do -> just set the HTTP response status as defined in @ResponseStatus
    }

}
