package com.haw.se1lab.todolist.logic.api.usecase;

import com.haw.se1lab.todolist.dataaccess.api.entity.TodoList;
import com.haw.se1lab.user.dataaccess.api.entity.User;

import java.util.List;

public interface TodoListUseCase {

    /**
     * Finds all todolists from an user
     *
     * @param user logged in user
     * @return returns all the lists from an user
     */
    List<TodoList> findAllTodoListsFromUser(User user);

    /**
     * Finds all todolists from an user and sorts the todolists by their id (ascending order)
     *
     * @param user logged in user
     * @return returns all the lists from an user
     */
    List<TodoList> findAllTodoListsFromUserSortedById(User user);

}
