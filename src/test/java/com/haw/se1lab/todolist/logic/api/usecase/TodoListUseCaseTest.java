package com.haw.se1lab.todolist.logic.api.usecase;

import com.haw.se1lab.Application;
import com.haw.se1lab.todolist.dataaccess.api.entity.TodoList;
import com.haw.se1lab.todolist.dataaccess.api.repo.TodoListRepository;
import com.haw.se1lab.todolist.facade.api.TodoListFacade;
import com.haw.se1lab.user.common.api.datatype.UserIDTyp;
import com.haw.se1lab.user.dataaccess.api.entity.User;
import com.haw.se1lab.user.dataaccess.api.repo.UserRepository;
import com.haw.se1lab.workgroup.common.api.datatype.WorkGroupIDTyp;
import com.haw.se1lab.workgroup.dataaccess.api.entity.WorkGroup;
import com.haw.se1lab.workgroup.dataaccess.api.repo.WorkGroupRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for {@link TodoListUseCase}.
 *
 * @author Janat Haref, Benedikt Weyer, Akif Aydin
 */

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE) //environment
@ExtendWith(SpringExtension.class) // required to use Spring TestContext Framework in JUnit 5
@ActiveProfiles("test") // causes exclusive creation of general and test-specific beans (marked by @Profile("test"))
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TodoListUseCaseTest {
    @Autowired
    private TodoListUseCase todoListUseCase;

    @Autowired
    private TodoListRepository todoListRepository;

    @Autowired
    private UserRepository userRepository;

    private User user1;
    private User user2;

    private TodoList todoListEntry1, todoListEntry2, todoListEntry3, todoListEntry4, todoListEntry5;

    @BeforeEach
    public void setup(){

        //create instances
        user1 = new User(new UserIDTyp(1), "Peter", "Lustig", "peter.lustig@gmail.com", "ichmagkekse");
        user2 = new User(new UserIDTyp(2), "Helga", "Wiedenfeld", "helga.wiedenfeld@gmail.com", "ichmagkeinekekse");

        todoListEntry1 = new TodoList("Wohnwagen Todo", false, user1, null);
        todoListEntry2 = new TodoList("Einkaufliste", false, user1, null);
        todoListEntry3 = new TodoList("Hausaufgaben Liste", false, user2, null);
        todoListEntry4 = new TodoList("Film Liste", false, user2, null);
        todoListEntry5 = new TodoList("Serien Liste", false, user2, null);

        //save to db
        userRepository.save(user1);
        userRepository.save(user2);

        todoListRepository.save(todoListEntry1);
        todoListRepository.save(todoListEntry2);
        todoListRepository.save(todoListEntry3);
        todoListRepository.save(todoListEntry4);
        todoListRepository.save(todoListEntry5);
    }

    @AfterEach
    public void tearDown(){
        //delete from db
        todoListRepository.delete(todoListEntry1);
        todoListRepository.delete(todoListEntry2);
        todoListRepository.delete(todoListEntry3);
        todoListRepository.delete(todoListEntry4);
        todoListRepository.delete(todoListEntry5);

        userRepository.delete(user1);
        userRepository.delete(user2);
    }

    @Test
    public void findAllTodoListsFromUserSortedById(){
        List<TodoList> listsOfUser1 = todoListUseCase.findAllTodoListsFromUserSortedById(user1);

        //test listsOfUser1
        //test size
        assertThat(listsOfUser1).hasSize(2);
        //test sorted
        assertThat(listsOfUser1).isSortedAccordingTo(Comparator.comparing(TodoList::getId));
        //test entries
        assertThat(listsOfUser1).anyMatch(tdl -> tdl.getId().equals(todoListEntry1.getId()));
        assertThat(listsOfUser1).anyMatch(tdl -> tdl.getId().equals(todoListEntry2.getId()));
        assertThat(listsOfUser1).noneMatch(tdl -> tdl.getId().equals(todoListEntry3.getId()));
        assertThat(listsOfUser1).noneMatch(tdl -> tdl.getId().equals(todoListEntry4.getId()));
        assertThat(listsOfUser1).noneMatch(tdl -> tdl.getId().equals(todoListEntry5.getId()));


        List<TodoList> listsOfUser2 = todoListUseCase.findAllTodoListsFromUserSortedById(user2);

        //test listsOfUser2
        //test size
        assertThat(listsOfUser2).hasSize(3);
        //test sorted
        assertThat(listsOfUser2).isSortedAccordingTo(Comparator.comparing(TodoList::getId));
        //test entries
        assertThat(listsOfUser2).noneMatch(tdl -> tdl.getId().equals(todoListEntry1.getId()));
        assertThat(listsOfUser2).noneMatch(tdl -> tdl.getId().equals(todoListEntry2.getId()));
        assertThat(listsOfUser2).anyMatch(tdl -> tdl.getId().equals(todoListEntry3.getId()));
        assertThat(listsOfUser2).anyMatch(tdl -> tdl.getId().equals(todoListEntry4.getId()));
        assertThat(listsOfUser2).anyMatch(tdl -> tdl.getId().equals(todoListEntry5.getId()));
    }
}
