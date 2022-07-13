package africa.semicolon.passwordManagementSystem.dtos.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class DeleteUrlRequest {
    private String email;
    private String userPassword;
    private String urlAddress;
}
