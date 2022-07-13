package africa.semicolon.passwordManagementSystem.dtos.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UpdateUrlResponse {
    private String username;
    private String urlUserName;
    private String urlPassword;
    private String urlAddress;
}
