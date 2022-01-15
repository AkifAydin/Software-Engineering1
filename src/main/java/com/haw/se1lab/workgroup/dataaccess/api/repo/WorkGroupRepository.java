package com.haw.se1lab.workgroup.dataaccess.api.repo;

import com.haw.se1lab.workgroup.common.api.datatype.WorkGroupIDTyp;
import com.haw.se1lab.workgroup.dataaccess.api.entity.WorkGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

/**
 * Represents a repository for the management of {@link WorkGroup} entities in a database.
 *
 * @author Janat Haref, Benedikt Weyer, Akif Aydin
 */
// important: no class "<repository name>Impl" implementing this interface and being annotated with @Component required
// -> Spring Data automatically creates a Spring bean for this repository which can then be used using @Autowired
//@Repository oder extends JpaRepository<...,...>
public interface WorkGroupRepository extends JpaRepository<WorkGroup, Long> {

    /* ---- Custom Query Methods ---- */

    // For documentation about how query methods work and how to declare them see "Spring Data - Query Methods":
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods

    /**
     * Returns the {@link WorkGroup} entity with the given group name.
     *
     * @param groupName the group name
     * @return an {@link Optional} containing the found group
     */
    // custom query method using JPQL query string
    @Query("select wg from WorkGroup wg where wg.name = :groupName")
    Optional<WorkGroup> findByName(@Param("groupName") String groupName);


    /**
     * Returns the {@link WorkGroup} entity with the given WorkGroupID.
     *
     * @param groupId the group id
     * @return an {@link Optional} containing the found group
     */
    @Query("select wg from WorkGroup wg where wg.id = :groupId")
    Optional<WorkGroup> findByWorkGroupIDTyp(@Param("groupId") WorkGroupIDTyp groupId);


    /**
     * Returns the {@link WorkGroup} entity with the given Date.
     *
     * @param createdAt date of creation
     * @return an {@link Optional} containing the found group
     */
    @Query("select wg from WorkGroup wg where wg.createdAt = :createdAt")
    Optional<WorkGroup> findByCreatedAt(@Param("createdAt") Date createdAt);

    /**
     * Deletes the {@link WorkGroup} entity with the given Group ID.
     *
     * @param deleteId the Group ID to be deleted
     */
    @Modifying
    @Transactional // causes the method to be executed in a database transaction (required for write operations)
    @Query("delete from WorkGroup wg where wg.id  = :deleteId")
    void deleteByWorkGroupIDTyp(@Param("deleteId") WorkGroupIDTyp deleteId);

}
