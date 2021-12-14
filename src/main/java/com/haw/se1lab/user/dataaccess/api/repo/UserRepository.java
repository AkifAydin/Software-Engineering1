package com.haw.se1lab.user.dataaccess.api.repo;

import com.haw.se1lab.user.common.api.datatype.UserIDTyp;
import com.haw.se1lab.user.dataaccess.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

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
     * @param userId the customer number
     */
    // custom query method with query automatically derived from method name (e.g. "<action>By<attribute name>")
    // equivalent SQL query: delete from USER where USER_ID_TYP = [UserIDTyp.userid]
    @Transactional // causes the method to be executed in a database transaction (required for write operations)
    void deleteByUserIDTyp(UserIDTyp userId);

}
