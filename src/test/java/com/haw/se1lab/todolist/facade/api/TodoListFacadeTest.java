package com.haw.se1lab.todolist.facade.api;

import com.haw.se1lab.Application;
import com.haw.se1lab.todolist.dataaccess.api.entity.TodoList;
import com.haw.se1lab.todolist.dataaccess.api.repo.TodoListRepository;
import com.haw.se1lab.user.common.api.datatype.UserIDTyp;
import com.haw.se1lab.user.dataaccess.api.entity.User;
import com.haw.se1lab.user.dataaccess.api.repo.UserRepository;
import com.haw.se1lab.workgroup.common.api.datatype.WorkGroupIDTyp;
import com.haw.se1lab.workgroup.dataaccess.api.entity.WorkGroup;
import com.haw.se1lab.workgroup.dataaccess.api.repo.WorkGroupRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //environment
@ExtendWith(SpringExtension.class) // required to use Spring TestContext Framework in JUnit 5
@ActiveProfiles("test") // causes exclusive creation of general and test-specific beans (marked by @Profile("test"))
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TodoListFacadeTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TodoListRepository todoListRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkGroupRepository workGroupRepository;

    private User user1;
    private User user2;

    private TodoList todoListEntry1, todoListEntry2, todoListEntry3;

    @BeforeEach
    public void setup(){

        //create instances
        user1 = new User(new UserIDTyp(1), "Peter", "Lustig", "peter.lustig@gmail.com", "ichmagkekse");
        user2 = new User(new UserIDTyp(2), "Helga", "Wiedenfeld", "helga.wiedenfeld@gmail.com", "ichmagkeinekekse");

        todoListEntry1 = new TodoList("Wohnwagen Todo", false, user1, null);
        todoListEntry2 = new TodoList("Einkaufliste", false, user1, null);
        todoListEntry3 = new TodoList("Hausaufgaben Liste", false, user2, null);

        //save to db
        userRepository.save(user1);
        userRepository.save(user2);

        todoListRepository.save(todoListEntry1);
        todoListRepository.save(todoListEntry2);
        todoListRepository.save(todoListEntry3);

        //set rest data
        RestAssured.port = port;
        RestAssured.basePath = "";
    }

    @AfterEach
    public void tearDown(){
        //delete from db
        todoListRepository.delete(todoListEntry1);
        todoListRepository.delete(todoListEntry2);
        todoListRepository.delete(todoListEntry3);

        userRepository.delete(user1);
        userRepository.delete(user2);
    }

    @Test
    public void findByOwner(){
        //given
        List<TodoList> outList1 = given()
        .contentType(ContentType.JSON)
        .body(user1)

        //when
        .when()
        .post("/todolist/getByUser")

        //then
        .then()
        .statusCode(HttpStatus.OK.value())
        .extract().body().jsonPath().getList(".", TodoList.class);

        //test listsOfUser1
        //test size
        assertThat(outList1).hasSize(2);
        //test content
        assertThat(outList1).anyMatch(tdl -> tdl.getId().equals(todoListEntry1.getId()));
        assertThat(outList1).anyMatch(tdl -> tdl.getId().equals(todoListEntry2.getId()));
        assertThat(outList1).noneMatch(tdl -> tdl.getId().equals(todoListEntry3.getId()));


        //given
        List<TodoList> outList2 = given()
        .contentType(ContentType.JSON)
        .body(user2)

        //when
        .when()
        .post("/todolist/getByUser")

        //then
        .then()
        .statusCode(HttpStatus.OK.value())
        .extract().body().jsonPath().getList(".", TodoList.class);

        //test listsOfUser2
        assertThat(outList2).hasSize(1);
        //test content
        assertThat(outList2).noneMatch(tdl -> tdl.getId().equals(todoListEntry1.getId()));
        assertThat(outList2).noneMatch(tdl -> tdl.getId().equals(todoListEntry2.getId()));
        assertThat(outList2).anyMatch(tdl -> tdl.getId().equals(todoListEntry3.getId()));
    }
}
