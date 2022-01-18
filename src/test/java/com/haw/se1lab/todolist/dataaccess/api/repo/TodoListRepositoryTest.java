package com.haw.se1lab.todolist.dataaccess.api.repo;

import com.haw.se1lab.Application;
import com.haw.se1lab.todolist.dataaccess.api.entity.TodoList;
import com.haw.se1lab.user.common.api.datatype.UserIDTyp;
import com.haw.se1lab.user.dataaccess.api.entity.User;
import com.haw.se1lab.user.dataaccess.api.repo.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for {@link TodoListRepository}.
 *
 * @author Janat Haref, Benedikt Weyer, Akif Aydin
 */

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE) //environment
@ExtendWith(SpringExtension.class) // required to use Spring TestContext Framework in JUnit 5
@ActiveProfiles("test") // causes exclusive creation of general and test-specific beans (marked by @Profile("test"))
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TodoListRepositoryTest {

    @Autowired
    private TodoListRepository todoListRepository;

    @Autowired
    private UserRepository userRepository;

    private User user1;
    private User user2;

    private TodoList todoList1, todoList2, todoList3;

    @BeforeEach
    public void setup(){

        //create instances
        user1 = new User(new UserIDTyp(1), "Peter", "Lustig", "peter.lustig@gmail.com", "ichmagkekse");
        user2 = new User(new UserIDTyp(2), "Helga", "Wiedenfeld", "helga.wiedenfeld@gmail.com", "ichmagkeinekekse");

        todoList1 = new TodoList("Wohnwagen Todo", false, user1, null);
        todoList2 = new TodoList("Einkaufliste", false, user1, null);
        todoList3 = new TodoList("Hausaufgaben Liste", false, user2, null);

        //save to db
        userRepository.save(user1);
        userRepository.save(user2);

        todoListRepository.save(todoList1);
        todoListRepository.save(todoList2);
        todoListRepository.save(todoList3);
    }

    @AfterEach
    public void tearDown(){
        //delete from db
        todoListRepository.delete(todoList1);
        todoListRepository.delete(todoList2);
        todoListRepository.delete(todoList3);

        userRepository.delete(user1);
        userRepository.delete(user2);
    }

    @Test
    public void findByOwner(){
        List<TodoList> listsOfUser1 = todoListRepository.findByOwner(user1);

        //test listsOfUser1
        //test size
        assertThat(listsOfUser1).hasSize(2);
        //test content
        assertThat(listsOfUser1).anyMatch(tdl -> tdl.getId().equals(todoList1.getId()));
        assertThat(listsOfUser1).anyMatch(tdl -> tdl.getId().equals(todoList2.getId()));
        assertThat(listsOfUser1).noneMatch(tdl -> tdl.getId().equals(todoList3.getId()));


        List<TodoList> listsOfUser2 = todoListRepository.findByOwner(user2);

        //test listsOfUser2
        assertThat(listsOfUser2).hasSize(1);
        //test content
        assertThat(listsOfUser2).noneMatch(tdl -> tdl.getId().equals(todoList1.getId()));
        assertThat(listsOfUser2).noneMatch(tdl -> tdl.getId().equals(todoList2.getId()));
        assertThat(listsOfUser2).anyMatch(tdl -> tdl.getId().equals(todoList3.getId()));
    }
}
