package server.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import server.dto.CompanyDTO;
import server.dto.ProjectDTO;
import server.dto.TaskDTO;
import server.dto.WorkDTO;
import server.entity.Company;
import server.entity.Project;
import server.service.ProjectService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class ProjectLogic {

    private final ProjectService projectService;


    @Autowired
    public ProjectLogic(ProjectService projectService){
        this.projectService = projectService;
    }

    public Project createProjectInstance(String projectName, String endDate, CompanyDTO company) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = sdf.parse(endDate);

        return new Project(projectName, new Date(), date, new Company(company), true);
    }

    public List<ProjectDTO> filterActiveProjects(List<ProjectDTO> projectDTOS) {
        return projectDTOS.stream().filter(ProjectDTO::isActive).collect(Collectors.toList());
    }

    public Project saveProject(String projectName, String endDate, int projectId, CompanyDTO companyDTO) throws ParseException {
        Project project = createProjectInstance(projectName, endDate, companyDTO);
        if(projectId != 0){
            project.setId(projectId);
        }
        projectService.save(project);

        return project;
    }

    public List<ProjectDTO> getProjectsFromCompany(int companyId) {
        List<ProjectDTO> projectDTOS = projectService.getProjectByCompany(companyId);
        List<ProjectDTO> filteredProjects =  filterActiveProjects(projectDTOS);

        return filteredProjects;
    }

    public ProjectDTO getProject(int projectId) {
        return projectService.getProjectById(projectId);
    }

    public Project disableProject(int projectId) {
        ProjectDTO projectDTO = projectService.getProjectById(projectId);

        Project projectToDisable = new Project(projectDTO.getName(),projectDTO.getStartDate(), projectDTO.getEndDate(), new Company(projectDTO.getCompanyId()), false);
        projectToDisable.setId(projectId);
        projectService.save(projectToDisable);
        return projectToDisable;
    }

    public boolean overBudget(ProjectDTO project) {
        int totalHoursWorkedOnProject = 0;
        for(WorkDTO workDTO: project.getWorkList()){
            long hoursWorkedInMillies = Math.abs(workDTO.getEndDate().getTime() - workDTO.getBeginDate().getTime());
            int diff = (int) TimeUnit.HOURS.convert(hoursWorkedInMillies, TimeUnit.MILLISECONDS);
            totalHoursWorkedOnProject = totalHoursWorkedOnProject + diff;
        }

        long projectDurationInMillies = Math.abs(project.getEndDate().getTime() - project.getStartDate().getTime());
        int projectDuration = (int) TimeUnit.DAYS.convert(projectDurationInMillies, TimeUnit.MILLISECONDS);

        int budget = projectDuration * 9;

        return budget < totalHoursWorkedOnProject;
    }
}
