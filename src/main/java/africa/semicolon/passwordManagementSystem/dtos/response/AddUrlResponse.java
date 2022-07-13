package africa.semicolon.passwordManagementSystem.dtos.response;

import africa.semicolon.passwordManagementSystem.models.data.Url;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class AddUrlResponse {
    private String message;
    private String username;
    @DBRef
    private Set<Url> urls = new HashSet<>();
}
