package africa.semicolon.passwordManagementSystem.dtos.requests;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AddUrlRequest {
    private String username;
    private String userPassword;
    private String urlUserName;
    private String urlPassword;
    private String urlAddress;

}
