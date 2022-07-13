package africa.semicolon.passwordManagementSystem.dtos.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class DeleteUserRequest {
    private String username;
    private String password;
    private String email;
}
