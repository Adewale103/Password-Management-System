package africa.semicolon.passwordManagementSystem.models.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Document
@Setter
@Getter
@NoArgsConstructor
public class Url {
    @Id
    private String urlId;
    private String urlPassword;
    private String userName;
    private String address;
    private String dateCreated = DateTimeFormatter.ofPattern("E, dd/MM/yyyy, hh:mm, a").format(LocalDateTime.now());

    @Override
    public String toString(){
        return String.format("""
                UrlId : %s
                UserName : %s
                Password : %s
                Address : %s
                Date Created : %s
                """,urlId,urlPassword,userName,address, dateCreated);
    }
}
