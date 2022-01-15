package com.haw.se1lab.tasks.dataaccess.api.repo;

import com.haw.se1lab.tasks.dataaccess.api.entity.Task;
import com.haw.se1lab.workgroup.dataaccess.api.entity.WorkGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;
import java.util.List;

/**
 * Represents a repository for the management of {@link Task} entities in a database.
 *
 * @author Janat Haref, Benedikt Weyer, Akif Aydin
 */
// important: no class "<repository name>Impl" implementing this interface and being annotated with @Component required
// -> Spring Data automatically creates a Spring bean for this repository which can then be used using @Autowired
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Returns the {@link Task} entity with the given todoName.
     *
     * @param todoName the todoName
     * @return an {@link Optional} containing the Task
     */
    @Query("select tk from Task tk where tk.toDo = :todoName")
    Optional<Task> findByToDo(@Param("todoName") String todoName);

    /**
     * Returns the {@link Task} entity with the given finished Tasks.
     *
     * @param finished boolean
     * @return the found Tasks
     */
    @Query("select tk from Task tk where tk.finished = :finished")
    List<Task> findByFinished(@Param("finished") Boolean finished);

}
