package com.haw.se1lab.todolist.facade.api;

import com.haw.se1lab.todolist.dataaccess.api.entity.TodoList;
import com.haw.se1lab.user.dataaccess.api.entity.User;

import java.util.List;

public interface TodoListFacade {

    /**
     * Gets all todolists from an user
     *
     * @param user logged in user
     * @return returns all the lists from an user
     */
    List<TodoList> getAllTodoListsFromUser(User user);
}
