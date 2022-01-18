package com.haw.se1lab.todolist.facade.impl;

import com.haw.se1lab.todolist.dataaccess.api.entity.TodoList;
import com.haw.se1lab.todolist.facade.api.TodoListFacade;
import com.haw.se1lab.todolist.logic.api.usecase.TodoListUseCase;
import com.haw.se1lab.user.dataaccess.api.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Default implementation for {@link TodoListFacade}.
 *
 * @author Janat Haref, Benedikt Weyer, Akif Aydin
 */

@Component
@RestController
@RequestMapping(path = "/todolist")
public class TodoListFacadeImpl implements TodoListFacade {

    @Autowired
    private TodoListUseCase todoListUseCase;

    @PostMapping("/getByUser")
    @Override
    public List<TodoList> getAllTodoListsFromUser(@RequestBody User user) {
        return todoListUseCase.findAllTodoListsFromUser(user);
    }
}
