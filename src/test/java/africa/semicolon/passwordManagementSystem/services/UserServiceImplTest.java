package africa.semicolon.passwordManagementSystem.services;

import africa.semicolon.passwordManagementSystem.dtos.requests.AddUserRequest;
import africa.semicolon.passwordManagementSystem.dtos.requests.DeleteUserRequest;
import africa.semicolon.passwordManagementSystem.dtos.requests.UpdateUserRequest;
import africa.semicolon.passwordManagementSystem.dtos.response.UserResponse;
import africa.semicolon.passwordManagementSystem.expections.UserAlreadyExistException;
import africa.semicolon.passwordManagementSystem.models.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    private AddUserRequest addUserRequest;


    @BeforeEach
    void setUp() {
        addUserRequest = AddUserRequest.builder()
                .username("adeyinkawale13@gmail.com")
                .password("Adewale@111")
                .email("adeyinkawale13@gmail.com")
                .phoneNumber("09021806462")
                .build();
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void createAccountTest(){
        userService.createAccount(addUserRequest);
        assertThat(userRepository.count(),is(1L));
    }
    @Test
    public void DuplicateAccountCannotBeCreatedTest(){
        userService.createAccount(addUserRequest);
        AddUserRequest addUserRequest2 = AddUserRequest.builder()
                .username("adeyinkawale13@gmail.com")
                .password("Adewale@111")
                .email("adeyinkawale13@gmail.com")
                .phoneNumber("09021806462")
                .build();
        assertThrows(UserAlreadyExistException.class,
                ()-> userService.createAccount(addUserRequest2));
    }
    @Test
    public void userCanBeFoundTest(){
        userService.createAccount(addUserRequest);
        UserResponse foundUser = userService.findUserByEmail("adeyinkawale13@gmail.com");
        assertThat(addUserRequest.getEmail(),equalTo(foundUser.getEmail()));

    }
    @Test
    public void userCanBeUpdatedTest(){
        userService.createAccount(addUserRequest);
        UpdateUserRequest request = UpdateUserRequest.builder()
                .username("adeyinkawale13@gmail.com")
                .password("Adewale@111")
                .email("adeyinkawale13@gmail.com")
                .phoneNumber("08101851250")
                .build();
        UserResponse response = userService.updateUser("adeyinkawale13@gmail.com",request);
        assertThat(response.getPhoneNumber(),is("08101851250"));
    }
    @Test
    public void userCanBeDeleted(){
        userService.createAccount(addUserRequest);
        DeleteUserRequest request = DeleteUserRequest.builder()
                .username("adeyinkawale13@gmail.com")
                .password("Adewale@111")
                .email("adeyinkawale13@gmail.com")
                .build();
        userService.deleteUser(request);
        assertThat(userRepository.count(),is(0L));
    }
}