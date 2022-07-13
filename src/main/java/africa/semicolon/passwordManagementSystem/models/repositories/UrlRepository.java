package africa.semicolon.passwordManagementSystem.models.repositories;

import africa.semicolon.passwordManagementSystem.models.data.Url;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends MongoRepository<Url,String> {
    Optional<Url> findUrlByAddress(String address);
}
