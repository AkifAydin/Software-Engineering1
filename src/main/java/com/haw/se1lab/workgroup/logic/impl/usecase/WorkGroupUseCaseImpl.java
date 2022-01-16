package com.haw.se1lab.workgroup.logic.impl.usecase;

import com.haw.se1lab.user.dataaccess.api.entity.User;
import com.haw.se1lab.workgroup.common.api.datatype.WorkGroupIDTyp;
import com.haw.se1lab.workgroup.common.api.datatype.exception.IllegalGroupAccessException;
import com.haw.se1lab.workgroup.dataaccess.api.entity.WorkGroup;
import com.haw.se1lab.workgroup.dataaccess.api.repo.WorkGroupRepository;
import com.haw.se1lab.workgroup.logic.api.usecase.WorkGroupUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class WorkGroupUseCaseImpl implements WorkGroupUseCase {

    @Autowired
    private WorkGroupRepository workGroupRepository;

    @Override
    public List<WorkGroup> findAllGroupsFromUser(User user) {
        return workGroupRepository.findAll().stream().filter(w -> w.getMembers().contains(user)).collect(Collectors.toList());
    }

    @Override
    public WorkGroup createGroup(User user, String groupName, boolean publicVisible, List<User> addedUsers) {
        //generate random id
        int workGroupId = ThreadLocalRandom.current().nextInt(1000000);

        //create work group
        WorkGroup newWorkGroup = new WorkGroup(new WorkGroupIDTyp(workGroupId), groupName, publicVisible);
        if(!addedUsers.contains(user)){
            addedUsers.add(user);
        }
        newWorkGroup.getMembers().addAll(addedUsers);

        //return and save in db
        return workGroupRepository.save(newWorkGroup);
    }

    @Override
    public WorkGroup editGroup(User user, WorkGroup group) throws IllegalGroupAccessException {
        //check for access rights
        if(!isUserInGroup(group, user)){
            throw new IllegalGroupAccessException(user, group);
        }

        //save  edited wok group
        return workGroupRepository.save(group);
    }

    @Override
    public String createInviteLink(User user, WorkGroup group) throws IllegalGroupAccessException {
        if(!isUserInGroup(group, user)){
            throw new IllegalGroupAccessException(user, group);
        }

        return "http://localhost:8080/workGroup/invite?key=hgGHgZ8f&6zgs&groupId="+group.getId();
    }

    @Override
    public boolean isUserInGroup(WorkGroup group, User user) {
        return group.getMembers().contains(user);
    }
}
