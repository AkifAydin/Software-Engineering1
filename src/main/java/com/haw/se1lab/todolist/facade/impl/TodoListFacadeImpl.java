package com.haw.se1lab.todolist.facade.impl;

import com.haw.se1lab.todolist.dataaccess.api.entity.TodoList;
import com.haw.se1lab.todolist.facade.api.TodoListFacade;
import com.haw.se1lab.todolist.logic.api.usecase.TodoListUseCase;
import com.haw.se1lab.user.dataaccess.api.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TodoListFacadeImpl implements TodoListFacade {

    @Autowired
    private TodoListUseCase todoListUseCase;

    @Override
    public List<TodoList> getAllTodoListsFromUser(User user) {
        return todoListUseCase.findAllTodoListsFromUser(user);
    }
}
