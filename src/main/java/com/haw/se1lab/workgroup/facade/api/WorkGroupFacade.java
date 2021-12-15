package com.haw.se1lab.workgroup.facade.api;

import com.haw.se1lab.workgroup.dataaccess.api.entity.WorkGroup;
import com.haw.se1lab.user.dataaccess.api.entity.User;

import java.util.List;

public interface WorkGroupFacade {
    /**
     * Finds all groups
     *
     * @return returns all groups
     */
    //List<WorkGroup> getGroups();

    /**
     * Returns the group with the given ID.
     *
     * @param id the group's technical ID
     * @return the found group (returns null if no group was found)
     */
    //WorkGroup getGroup(long id);

    /**
     * Gets all groups a user has joined or created
     *
     * @param user logged in user
     * @return returns all groups from an user
     */
    List<WorkGroup> getAllGroupsFromUser(User user);

    /**
     * Creates a new Group
     *
     * @param user logged in user that creates the group
     * @param group new group
     * @return returns the new created group
     */
    WorkGroup createGroup(User user, WorkGroup group);

    /**
     * Edit a Group
     *
     * @param user admin user of the group
     * @param group edited group
     * @return return edited group
     */
    WorkGroup editGroup(User user, WorkGroup group);

    /**
     * Get generated invite link
     *
     * @param user logged in user
     * @param group group from where the invite link is from
     * @return returns the invite link
     */
    String getInviteLink(User user, WorkGroup group);
}
