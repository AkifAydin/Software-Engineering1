package com.haw.se1lab.smoker;

import com.haw.se1lab.Application;
import com.haw.se1lab.todolist.dataaccess.api.entity.TodoList;
import com.haw.se1lab.todolist.dataaccess.api.repo.TodoListRepository;
import com.haw.se1lab.todolist.facade.api.TodoListFacade;
import com.haw.se1lab.user.common.api.datatype.UserIDTyp;
import com.haw.se1lab.user.common.api.datatype.exception.UserNotFoundException;
import com.haw.se1lab.user.common.api.datatype.exception.WrongPasswordException;
import com.haw.se1lab.user.dataaccess.api.entity.User;
import com.haw.se1lab.user.dataaccess.api.repo.UserRepository;
import com.haw.se1lab.user.facade.api.UserFacade;
import com.haw.se1lab.workgroup.common.api.datatype.WorkGroupIDTyp;
import com.haw.se1lab.workgroup.common.api.datatype.exception.IllegalGroupAccessException;
import com.haw.se1lab.workgroup.dataaccess.api.entity.WorkGroup;
import com.haw.se1lab.workgroup.dataaccess.api.repo.WorkGroupRepository;
import com.haw.se1lab.workgroup.facade.api.WorkGroupFacade;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Assert;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class with smoke tests for use case scenarios in the components. The test cases here include complex
 * interactions with the application core and are supposed to detect when fundamental functionality needed for use case
 * execution is broken. Moreover, the tests in this class perform facade calls in a way which is similar to how these
 * calls are made from a client application when a user goes through a use case scenario - except that facade calls are
 * performed directly and not via the REST API. Therefore, these tests can be viewed as a kind of simulation of a user
 * test. Consequentially, a user test for the use case scenarios handled here should not be performed as long as tests
 * in this class fail.
 *
 * @author Janat Haref, Benedikt Weyer, Akif Aydin
 */

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE) //environment
@ExtendWith(SpringExtension.class) // required to use Spring TestContext Framework in JUnit 5
@ActiveProfiles("test") // causes exclusive creation of general and test-specific beans (marked by @Profile("test"))
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GroupInviteSmokeTest {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private TodoListFacade todoListFacade;

    @Autowired
    private WorkGroupFacade workGroupFacade;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkGroupRepository workGroupRepository;

    @Autowired
    private TodoListRepository todoListRepository;

    private User admin;

    private TodoList todoListEntry1, todoListEntry2;

    private WorkGroup newGroup;

    @BeforeEach
    public void setup(){
        admin = new User(new UserIDTyp(1), "Peter", "Lustig", "peter.lustig@gmail.com", "ichmagkekse");

        todoListEntry1 = new TodoList("Wohnwagen Todo", false, admin, null);
        todoListEntry2 = new TodoList("Einkaufliste", false, admin, null);

        //write to db
        userRepository.save(admin);

        todoListRepository.save(todoListEntry1);
        todoListRepository.save(todoListEntry2);
    }

    @AfterEach
    public void tearDown(){
        todoListRepository.delete(todoListEntry1);
        todoListRepository.delete(todoListEntry2);

        if(newGroup != null){
            workGroupRepository.delete(newGroup);
        }

        userRepository.delete(admin);
    }

    @Test
    public void groupInviteScenario_Success(){
        System.out.println("");
        System.out.println(
                "####################################################################################################");
        System.out.println("Smoke Test - UC-1: Gruppen Einladung und Login");
        System.out.println(
                "####################################################################################################");
        System.out.println("");

        // [GIVEN]
        // Vorbedingungen:
        // - Der Admin hat einen Account


        // [WHEN]
        // Erfolgsszenario:
        // 1. Der Gruppenadmin ruft die App auf seinem Handy/Webbrowser auf.
        // 2. Das System zeigt ihm die Anmeldeseite an.
        System.out.println("Login:");
        System.out.println("[Email] [Passwort]");
        System.out.println("");


        // 3. Der Admin gibt Nutzername und Passwort ein und drückt den Button “Log In”.
        String email = admin.getEmail();
        String password = admin.getPassword();
        System.out.println("<Benutzereingabe: '"+email+"', '"+password+"'>");
        System.out.println("<Klick auf Login-Button>");
        System.out.println("");

        // 4. Das System prüft auf Korrektheit der Eingaben
        // 5. Das System legt 2 Std. gültige Session für den Kunden an.
        try {
            User loggedInUser = userFacade.login(email, password);

            // 6. Information über eine erfolgreiche Anmeldung. (z.b pop-up).
            System.out.println("Erfolgreiche Anmeldung!");
            System.out.println("Hallo "+loggedInUser.getFirstName()+" "+loggedInUser.getLastName());
            System.out.println("");

            // 7. Das System zeigt den DoDo Binder an
            List<TodoList> todoListsFromUser = todoListFacade.getAllTodoListsFromUser(loggedInUser);
            System.out.println("Todo Listen von "+loggedInUser.getFirstName()+":");
            System.out.println(todoListsFromUser.stream().map(t -> t.getName()).collect(Collectors.joining(", ")));
            System.out.println("");

            // 8. Der Gruppenadmin wählt den Tab “Gruppen” aus
            System.out.println("<Klick auf Tab Gruppen>");
            System.out.println("");

            // 9. Das System zeigt alle Beigetretenen Gruppen an, als auch eine Funktion eine Gruppen neu zu erstellen.
            List<WorkGroup> groupsFromUser = workGroupFacade.getAllGroupsFromUser(loggedInUser);
            System.out.println("Gruppen von "+loggedInUser.getFirstName()+":");
            System.out.println(groupsFromUser.stream().map(g -> g.getName()).collect(Collectors.joining(", ")));
            System.out.println("[neue Gruppe erstellen]");
            System.out.println("");

            // 10. Der Admin klickt auf die Funktion “Gruppe erstellen”.
            System.out.println("<Klick auf Gruppe erstellen>");
            System.out.println("");

            newGroup = workGroupFacade.createGroup(loggedInUser, "Neue Gruppe", false, new ArrayList<>());

            //11. System zeigt den Dialog zur Gruppenbearbeitung an
            System.out.println("Gruppe bearbeiten:");
            System.out.println("[name] [öffentlich?] [Einladungslink verschicken]");
            System.out.println("");

            //12. Admin bearbeitet die Gruppe (Einladungslink verschicken).
            System.out.println("<Klick auf Einladungslink verschicken>");
            System.out.println("");

            //13. Das System erstellt einen Einladungslink.
            try {
                String inviteLink = workGroupFacade.getInviteLink(loggedInUser, newGroup);

                // 14. Das System zeigt OS-funktion zum Teilen des Einladungslinks an.
                System.out.println("OS-Funktion: ["+inviteLink+" teilen]");
                System.out.println("");

                // 15. Der Gruppenadmin wählt die Funktion zum Teilen des Links
                System.out.println("<Klick auf Teilen>");
                System.out.println("");


                // [THEN]
                // Nachbedingungen:
                // - Der erstellte Link ermöglicht es anderen Nutzern der Gruppe beizutreten
                assertThat(inviteLink).startsWith("http://");


            } catch (IllegalGroupAccessException e) {
                Assert.fail(e.getMessage());
            }


        } catch (UserNotFoundException e) {
            Assert.fail(e.getMessage());
        } catch (WrongPasswordException e) {
            Assert.fail(e.getMessage());
        }

    }
}
