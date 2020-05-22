package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.dto.ProjectDTO;
import server.entity.Project;
import server.repository.ProjectRepo;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepo projectRepo;

    @Autowired
    public ProjectService(ProjectRepo projectRepo){this.projectRepo = projectRepo;}

    public ProjectDTO getProjectById(int projectId){return this.projectRepo.getProjectById(projectId);}
    public List<ProjectDTO> getProjectByCompany(int companyId){return this.projectRepo.getProjectByCompany(companyId);}

    public void save(Project project) {
        projectRepo.save(project);
    }
}
