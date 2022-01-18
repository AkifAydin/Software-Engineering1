package com.haw.se1lab.todolist.logic.impl.usecase;

import com.haw.se1lab.todolist.dataaccess.api.entity.TodoList;
import com.haw.se1lab.todolist.dataaccess.api.repo.TodoListRepository;
import com.haw.se1lab.todolist.logic.api.usecase.TodoListUseCase;
import com.haw.se1lab.todolist.strategy.TodoListSortingStrategy;
import com.haw.se1lab.user.dataaccess.api.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Default implementation for {@link TodoListUseCase}.
 *
 * @author Janat Haref, Benedikt Weyer, Akif Aydin
 */

//Sorgt für Berücksichtigung beim Autowiring
//Component can also be used
@Service // causes Spring to automatically create a Spring bean for this class which can then be used using @Autowired
public class TodoListUseCaseImpl implements TodoListUseCase {

    //Field Injection durch Autowiring
    @Autowired // automatically initializes the field with a Spring bean of a matching type
    private TodoListRepository todoListRepository;


    @Override
    public List<TodoList> findAllTodoListsFromUser(User user) {
        return todoListRepository.findByOwner(user);
    }

    @Override
    public List<TodoList> findAllTodoListsFromUserSortedById(User user) {
        //list of unsorted users
        List<TodoList> list = findAllTodoListsFromUser(user);

        //initializierung der Sorting Strategy
        TodoListSortingStrategy sortingStrategy = TodoListSortingUtil.getSortingStrategy(list);

        //return sortierte Liste
        return sortingStrategy.sort(list);
    }
}