package com.haw.se1lab.user.logic.api.usecase;

import com.haw.se1lab.user.common.api.datatype.exception.UserNotFoundException;
import com.haw.se1lab.user.dataaccess.api.entity.User;

import java.util.List;

public interface UserUseCase {
    /**
     * Returns all available users
     *
     * @return the found users or an empty list if none were found
     */
    //List<User> findAllUsers();

    /**
     * Returns the user with the given ID.
     *
     * @param id the user's technical ID
     * @return the found user
     */
    //User findUserById(long id);

    /**
     * Returns the user with the given email.
     *
     * @param email the users email; must not be <code>null</code>
     * @return the found user
     */
    User findUserByEmail(String email) throws UserNotFoundException;


    /**
     * Checks for correct password
     *
     * @param user the user which password is checked
     * @param password users input password
     * @return returns true when password matches the password of the user
     */
    boolean checkPassword(User user, String password);

    /**
     * Creates a new Session for a user and returns the session token
     *
     * @param user the user for which the session is created; must not be <code>null</code>
     * @return the session token
     */
    String createSession(User user);


}
