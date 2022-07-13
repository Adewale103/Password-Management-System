package africa.semicolon.passwordManagementSystem.services;

import africa.semicolon.passwordManagementSystem.dtos.requests.AddUrlRequest;
import africa.semicolon.passwordManagementSystem.dtos.requests.AddUserRequest;
import africa.semicolon.passwordManagementSystem.dtos.requests.DeleteUrlRequest;
import africa.semicolon.passwordManagementSystem.dtos.requests.UpdateUrlRequest;
import africa.semicolon.passwordManagementSystem.dtos.response.AddUrlResponse;
import africa.semicolon.passwordManagementSystem.dtos.response.DeleteResponse;
import africa.semicolon.passwordManagementSystem.dtos.response.FindUrlResponse;
import africa.semicolon.passwordManagementSystem.dtos.response.UpdateUrlResponse;
import africa.semicolon.passwordManagementSystem.models.repositories.UrlRepository;
import africa.semicolon.passwordManagementSystem.models.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@SpringBootTest
class UrlServiceImplTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UrlRepository urlRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UrlService urlService;

    private AddUrlRequest addUrlRequest;
    private AddUserRequest addUserRequest;

    @BeforeEach
    public void setUp(){
        addUrlRequest = AddUrlRequest.builder()
                .urlUserName("adeyinkawale11@semicolon.africa")
                .urlPassword("Adewale111")
                .urlAddress("www.semicolon.africa")
                .username("adeyinkawale11@gmail.com")
                .userPassword("Ade13@1234")
                .build();

        addUserRequest = AddUserRequest.builder()
                .email("adeyinkawale11@gmail.com")
                .password("Ade13@1234")
                .phoneNumber("08101851250")
                .username("adeyinkawale11@gmail.com")
                .build();
    }
    @AfterEach
    public void tearDown(){
        userRepository.deleteAll();
        urlRepository.deleteAll();
    }

    @Test
    public void urlCanBeAddedTest(){
        userService.createAccount(addUserRequest);
        AddUrlResponse addUrlResponse = urlService.addUrl(addUrlRequest);
        assertThat(urlRepository.count(), is(1L));
        assertThat(addUrlResponse.getUrls(), is(notNullValue()));
    }
    @Test
    public void urlCanBeFoundTest(){
        userService.createAccount(addUserRequest);
        urlService.addUrl(addUrlRequest);
        FindUrlResponse response =  urlService.findUrl("www.semicolon.africa");
        assertThat(response.getAddress(),equalTo(addUrlRequest.getUrlAddress()));
    }
    @Test
    public void urlDetailsCanBeUpdated(){
        userService.createAccount(addUserRequest);
        urlService.addUrl(addUrlRequest);

        AddUrlRequest addUrlRequest1 = AddUrlRequest.builder()
                .urlUserName("adeyinkawale11@semicolon.africa")
                .urlPassword("Adewale111")
                .urlAddress("www.google.com")
                .username("adeyinkawale11@gmail.com")
                .userPassword("Ade13@1234")
                .build();
        AddUrlResponse result = urlService.addUrl(addUrlRequest1);

        UpdateUrlRequest urlRequest = new UpdateUrlRequest();

        urlRequest.setUrlAddress("www.google.com");
        urlRequest.setUrlPassword("Gabriel13");
        urlRequest.setUrlUserName("adeyinkawale12@semicolon.africa");

        UpdateUrlResponse response = urlService.updateUrl("adeyinkawale11@gmail.com", urlRequest);
        assertThat(response.getUrlPassword(),is("Gabriel13"));
    }
    @Test
    public void urlCanBeDeletedTest(){
        userService.createAccount(addUserRequest);
        urlService.addUrl(addUrlRequest);
        DeleteUrlRequest request = DeleteUrlRequest.builder()
                .email("adeyinkawale11@gmail.com")
                .userPassword("Ade13@1234")
                .urlAddress(addUrlRequest.getUrlAddress())
                .build();
        DeleteResponse response = urlService.deleteUrl(request);
        assertThat(addUrlRequest.getUrlAddress()+ " has been successfully deleted!",is(response.getMessage()));
        assertThat(urlRepository.count(),is(0L));
    }






}