package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
}
