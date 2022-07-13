package africa.semicolon.passwordManagementSystem.models.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Document
@Setter
@Getter
public class User {
    @Id
    private String id;
    private String username;
    @Email
    private String email;
    private String password;
    private String phoneNumber;
    private boolean isLoggedIn;
    @DBRef
    private Set<Url> urls = new HashSet<>();

    @Override
    public String toString(){
        return String.format("""
                Id : %s
                Username : %s
                Email : %s
                Password : %s
                PhoneNumber : %s
                Urls : %s""", id,username,email, password, phoneNumber,getUrls().toString());
    }

}
