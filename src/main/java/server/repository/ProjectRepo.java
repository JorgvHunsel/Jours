package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.entity.Project;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Integer> {
}
