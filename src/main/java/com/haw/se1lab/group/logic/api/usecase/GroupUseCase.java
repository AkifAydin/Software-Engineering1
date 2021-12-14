package com.haw.se1lab.group.logic.api.usecase;

import com.haw.se1lab.group.dataaccess.api.entity.WorkGroup;
import com.haw.se1lab.user.dataaccess.api.entity.User;

import java.util.List;

public interface GroupUseCase {

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
     * @param group group to edit
     * @param groupName new name of the group
     * @param publicVisible new public visibility of the group
     * @param addedUsers new user list of the group (excluding the user that created the group)
     */
    void editGroup(User user, WorkGroup group, String groupName, boolean publicVisible, List<User> addedUsers);

    /**
     * Generate invite link to a group
     *
     * @param user logged in user
     * @param group group from where the invite link is from
     * @return returns the invite link
     */
    String createInviteLink(User user, WorkGroup group);
}
