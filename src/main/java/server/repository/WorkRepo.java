package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.entity.Work;

@Repository
public interface WorkRepo extends JpaRepository<Work, Integer> {
}
