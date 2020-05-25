package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import server.dto.ProjectDTO;
import server.entity.Project;
import server.entity.Work;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Integer> {
    @Query("SELECT new server.dto.ProjectDTO(p.id, p.name, p.endDate, p.active) FROM Project p WHERE p.company.id = ?1")
    List<ProjectDTO> getProjectByCompany(int companyId);

    @Query("SELECT new server.dto.ProjectDTO(p) FROM Project p WHERE p.id = ?1")
    ProjectDTO getProjectById(int projectId);



}
