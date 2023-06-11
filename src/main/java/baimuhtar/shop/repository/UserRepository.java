package baimuhtar.shop.repository;


import baimuhtar.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

    boolean existsByLogin(String login);
}
