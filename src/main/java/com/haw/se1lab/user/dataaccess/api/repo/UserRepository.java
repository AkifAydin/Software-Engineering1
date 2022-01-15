package com.haw.se1lab.user.dataaccess.api.repo;

import com.haw.se1lab.user.common.api.datatype.UserIDTyp;
import com.haw.se1lab.user.dataaccess.api.entity.User;
import com.haw.se1lab.workgroup.dataaccess.api.entity.WorkGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * Represents a repository for the management of {@link User} entities in a database.
 *
 * @author Janat Haref, Benedikt Weyer, Akif Aydin
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Deletes the {@link User} entity with the given UserID.
     *
     * @param userId the customer number to be deleted
     */
    @Modifying
    @Transactional // causes the method to be executed in a database transaction (required for write operations)
    @Query("delete from User u where u.id = :userId")
    void deleteByUserIDTyp(@Param("userId") UserIDTyp userId);

}
