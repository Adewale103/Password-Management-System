package africa.semicolon.passwordManagementSystem.dtos.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UpdateUserRequest {
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
}
