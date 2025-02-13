package Salesforelikeapp.Salesforelikeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Salesforelikeapp.Salesforelikeapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    User findByUsername(String username);
    
}
