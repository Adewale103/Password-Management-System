package africa.semicolon.passwordManagementSystem.models.repositories;

import africa.semicolon.passwordManagementSystem.models.data.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findUserByEmail(String email);
    boolean existsUserByEmail(String email);
}
