package com.haw.se1lab.user.logic.api.usecase;

import com.haw.se1lab.user.dataaccess.api.entity.User;

public interface UserUseCase {

    /**
     * Trys to login User
     *
     * @param email users input email
     * @param password users input password
     * @return returns the logged in user
     */
    User login(String email, String password);



}
