package africa.semicolon.passwordManagementSystem.dtos.requests;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
}
