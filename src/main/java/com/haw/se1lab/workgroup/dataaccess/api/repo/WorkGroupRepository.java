package com.haw.se1lab.workgroup.dataaccess.api.repo;

import com.haw.se1lab.workgroup.common.api.datatype.WorkGroupIDTyp;
import com.haw.se1lab.workgroup.dataaccess.api.entity.WorkGroup;
import org.springframework.data.jpa.repository.JpaRepository;

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
public interface WorkGroupRepository extends JpaRepository<WorkGroup, Long> {

    /**
     * Returns the {@link WorkGroup} entity with the given group name.
     *
     * @param groupName the group name
     * @return an {@link Optional} containing the found group
     */
    Optional<WorkGroup> findByName(String groupName);

    Optional<WorkGroup> findByWorkGroupIDTyp(WorkGroupIDTyp workGroupIDTyp);

    Optional<WorkGroup> findByCreatedAt(Date groupDate);

    /**
     * Deletes the {@link WorkGroup} entity with the given Group ID.
     *
     * @param workGroupIDTyp the Group ID
     */
    // equivalent SQL query: delete from WORKGROUP where GROUP_ID_TYP = [GroupIDTyp.groupID]
    @Transactional // causes the method to be executed in a database transaction (required for write operations)
    void deleteByWorkGroupIDTyp(WorkGroupIDTyp workGroupIDTyp);

}
