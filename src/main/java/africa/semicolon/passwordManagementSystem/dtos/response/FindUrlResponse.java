package africa.semicolon.passwordManagementSystem.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindUrlResponse {
    private String urlPassword;
    private String userName;
    private String address;
    private String dateCreated;
}
