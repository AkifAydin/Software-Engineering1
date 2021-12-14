package com.haw.se1lab.tasks.dataaccess.api.repo;

import com.haw.se1lab.tasks.dataaccess.api.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

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
    Optional<Task> findByToDo(String todoName);

    List<Task> findByFinished(Boolean finished);

}
