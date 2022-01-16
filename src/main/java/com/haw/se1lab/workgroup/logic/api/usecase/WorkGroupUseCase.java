package com.haw.se1lab.workgroup.logic.api.usecase;

import com.haw.se1lab.workgroup.common.api.datatype.exception.IllegalGroupAccessException;
import com.haw.se1lab.workgroup.dataaccess.api.entity.WorkGroup;
import com.haw.se1lab.user.dataaccess.api.entity.User;

import java.util.List;

public interface WorkGroupUseCase {
    /**
     * Finds all groups
     *
     * @return returns all groups
     */
    //List<WorkGroup> findAllGroups();

    /**
     * Returns the group with the given ID.
     *
     * @param id the group's technical ID
     * @return the found group (returns null if no group was found)
     */
    //WorkGroup findGroupById(long id);

    /**
     * Finds all groups a user has joined or created
     *
     * @param user logged in user
     * @return returns all groups from an user
     */
    List<WorkGroup> findAllGroupsFromUser(User user);

    /**
     * Creates a new Group
     *
     * @param user logged in user
     * @param groupName name of the new group
     * @param publicVisible if group is visible to the public
     * @param addedUsers list of users that are added to the group (excluding the user that created the group)
     * @return returns the new created group
     */
    WorkGroup createGroup(User user, String groupName, boolean publicVisible, List<User> addedUsers);

    /**
     * Edit a Group
     *
     * @param user logged in user
     * @param group edited group
     * @return returns the edited group
     */
    WorkGroup editGroup(User user, WorkGroup group) throws IllegalGroupAccessException;

    /**
     * Generate invite link to a group
     *
     * @param group group from where the invite link is from
     * @return returns the invite link as a string
     */
    String createInviteLink(User user, WorkGroup group) throws IllegalGroupAccessException;

    /**
     * Checks if User is in Group
     *
     * @param group group to check
     * @param user logged in user
     * @return returns the invite link
     */
    boolean isUserInGroup(WorkGroup group, User user);
}
