package server.endpoint;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.dto.CompanyDTO;
import server.dto.ProjectDTO;
import server.dto.TaskDTO;
import server.entity.Company;
import server.entity.Project;
import server.logic.ProjectLogic;
import server.logic.TaskLogic;
import server.repository.CompanyRepo;
import server.repository.ProjectRepo;
import server.repository.TaskRepo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RequestMapping(value ="/project")
@RestController
public class ProjectEndpoint {

    @Autowired
    ProjectRepo projectRepo;

    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    TaskRepo taskRepo;

    ProjectLogic projectLogic = new ProjectLogic();
    TaskLogic taskLogic = new TaskLogic();

    Gson gson = new Gson();

    @PutMapping
    public ResponseEntity<String> saveProject(@RequestBody Map<String, String> body) throws ParseException {
        String projectName = body.get("projectName");
        String endDate = body.get("endDate");
        int companyId = Integer.parseInt(body.get("companyId"));

        CompanyDTO company = companyRepo.findCompanyById(companyId);
        Project project = projectLogic.createProject(companyId, projectName, endDate, company);
        if(body.get("projectId") != null){
            project.setId(Integer.parseInt(body.get("projectId")));
        }
        projectRepo.save(project);

        return new ResponseEntity<>(gson.toJson(project), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<String> getProjectsFromCompany(@RequestParam int companyId){
        List<ProjectDTO> projectDTOS = projectRepo.getProjectByCompany(companyId);
        List<ProjectDTO> filteredProjects =  projectLogic.filterActiveProjects(projectDTOS);

        return new ResponseEntity<>(gson.toJson(filteredProjects), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> getProject(@RequestParam int projectId){
        ProjectDTO project = projectRepo.getProjectById(projectId);

        List<TaskDTO> filteredTasks = taskLogic.filterActiveTasks(project.getTasks());
        project.setTasks(filteredTasks);

        return new ResponseEntity<>(gson.toJson(project), HttpStatus.OK);
    }

    @PutMapping("/disable")
    public ResponseEntity<String> disableProject(@RequestParam int projectId) {
        ProjectDTO projectDTO = projectRepo.getProjectById(projectId);

        Project projectToDisable = new Project(projectDTO.getName(), projectDTO.getEndDate(), new Company(projectDTO.getCompanyId()), false);
        projectToDisable.setId(projectId);
        projectRepo.save(projectToDisable);
        taskRepo.disableTask(projectId, "hidden");

        return new ResponseEntity<>(gson.toJson(projectToDisable), HttpStatus.OK);
    }
}
