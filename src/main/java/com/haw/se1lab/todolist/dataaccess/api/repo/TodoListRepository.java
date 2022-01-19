package com.haw.se1lab.todolist.dataaccess.api.repo;

import com.haw.se1lab.todolist.dataaccess.api.entity.TodoList;
import com.haw.se1lab.user.common.api.datatype.UserIDTyp;
import com.haw.se1lab.user.dataaccess.api.entity.User;
import com.haw.se1lab.workgroup.dataaccess.api.entity.WorkGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Represents a repository for the management of {@link TodoList} entities in a database.
 *
 * @author Janat Haref, Benedikt Weyer, Akif Aydin
 */
public interface TodoListRepository extends JpaRepository<TodoList, Long> {

    /**
     * Returns the {@link TodoList} entitys with the given user.
     *
     * @param user the user
     * @return an {@link List} containing todo list entitys
     */
    @Query("select t from TodoList t where t.owner = :user")
    List<TodoList> findByOwner(@Param("user") User user);

    List<TodoList> findByOwner_Id(long userId);

}
