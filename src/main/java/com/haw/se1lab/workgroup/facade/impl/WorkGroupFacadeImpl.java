package com.haw.se1lab.workgroup.facade.impl;

import com.haw.se1lab.user.common.api.datatype.exception.UserNotFoundException;
import com.haw.se1lab.user.dataaccess.api.entity.User;
import com.haw.se1lab.workgroup.common.api.datatype.exception.IllegalGroupAccessException;
import com.haw.se1lab.workgroup.dataaccess.api.entity.WorkGroup;
import com.haw.se1lab.workgroup.facade.api.WorkGroupFacade;
import com.haw.se1lab.workgroup.logic.api.usecase.WorkGroupUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/workGroup")
public class WorkGroupFacadeImpl implements WorkGroupFacade {

    @Autowired
    private WorkGroupUseCase workGroupUseCase;

    @PostMapping("/getAllByUser")
    @Override
    public List<WorkGroup> getAllGroupsFromUser(@RequestBody User user) {
        return workGroupUseCase.findAllGroupsFromUser(user);
    }

    @PostMapping()
    @Override
    public WorkGroup createGroup(User user, String groupName, boolean publicVisible, List<User> addedUsers) {
        return workGroupUseCase.createGroup(user, groupName, publicVisible, addedUsers);
    }

    @PutMapping
    @Override
    public WorkGroup editGroup(@RequestBody User user, @RequestBody WorkGroup group) throws IllegalGroupAccessException {
        return workGroupUseCase.editGroup(user, group);
    }

    @PostMapping("/getInviteLink")
    @Override
    public String getInviteLink(User user, WorkGroup group) throws IllegalGroupAccessException {
        return workGroupUseCase.createInviteLink(user, group);
    }

    @ExceptionHandler(IllegalGroupAccessException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Not authorized to access group.")
    private void handleIllegalGroupAccessException() {
        // nothing to do -> just set the HTTP response status as defined in @ResponseStatus
    }
}
