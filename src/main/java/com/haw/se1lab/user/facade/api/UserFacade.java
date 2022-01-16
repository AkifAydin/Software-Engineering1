package com.haw.se1lab.user.facade.api;

import com.haw.se1lab.user.common.api.datatype.exception.UserNotFoundException;
import com.haw.se1lab.user.common.api.datatype.exception.WrongPasswordException;
import com.haw.se1lab.user.dataaccess.api.entity.User;

import java.util.List;

public interface UserFacade {
    /**
     * Returns all available users.
     *
     * @return the found user or an empty list if none were found
     */
    //List<User> getUser();

    /**
     * Returns the user with the given ID.
     *
     * @param id the user's technical ID
     * @return the found user
     */
    //User getUser(long id);

    /**
     * Creates a user with the given data.
     *
     * @param user the user to be created; must not be <code>null</code>
     * @return the created user
     */
    //User createUser(User user);

    /**
     * Updates a user with the given data.
     *
     * @param user the user to be updated; must not be <code>null</code>
     * @return the updated user
     */
    //User updateUser(User user);

    /**
     * Deletes the user with the given ID.
     *
     * @param id the user's technical ID
     */
    //void deleteUser(long id);


    /**
     * Trys to login User
     *
     * @param email users input email
     * @param password users input password
     * @return returns the logged in user
     */
    User login(String email, String password) throws UserNotFoundException, WrongPasswordException;
}
