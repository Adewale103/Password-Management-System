package africa.semicolon.passwordManagementSystem.dtos.response;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String id;
    private String username;
    private String email;
    private String phoneNumber;

}
