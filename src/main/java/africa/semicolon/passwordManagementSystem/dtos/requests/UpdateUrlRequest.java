package africa.semicolon.passwordManagementSystem.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class UpdateUrlRequest {
    private String username;
    private String userPassword;
    private String urlUserName;
    private String urlPassword;
    private String urlAddress;
}
