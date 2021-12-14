package com.haw.se1lab.todolist.dataaccess.api.repo;

import com.haw.se1lab.todolist.dataaccess.api.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Represents a repository for the management of {@link TodoList} entities in a database.
 *
 * @author Janat Haref, Benedikt Weyer, Akif Aydin
 */
public interface TodoListRepository extends JpaRepository<TodoList, Long> {
}
