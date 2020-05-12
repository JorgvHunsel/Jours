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
import server.entity.Task;
import server.repository.CompanyRepo;
import server.repository.ProjectRepo;
import server.repository.TaskRepo;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ProjectEndpoint {

    @Autowired
    ProjectRepo projectRepo;

    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    TaskRepo taskRepo;

    Gson gson = new Gson();

    @PostMapping("/project/create")
    public ResponseEntity<String> createProject(@RequestBody Map<String, String> body) throws ParseException {
        String projectName = body.get("projectName");
        String endDate = body.get("endDate");
        int companyId = Integer.parseInt(body.get("companyId"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = sdf.parse(endDate);

        CompanyDTO company = companyRepo.findCompanyById(companyId);

        Project newProject = new Project(projectName, date, new Company(company), true);
        projectRepo.save(newProject);

        return new ResponseEntity<>(gson.toJson(newProject), HttpStatus.OK);
    }

    @GetMapping("/project/all")
    public ResponseEntity getProjects(@RequestParam int companyId){
        List<ProjectDTO> projectDTOS = projectRepo.findByCompanyId(companyId);
        List<ProjectDTO> filteredProjects = projectDTOS.stream().filter(projectDTO -> projectDTO.isActive()).collect(Collectors.toList());

        return new ResponseEntity(gson.toJson(filteredProjects), HttpStatus.OK);
    }

    @GetMapping("/project")
    public ResponseEntity getProject(@RequestParam int projectId){
        ProjectDTO project = projectRepo.getProjectById(projectId);
        List<TaskDTO> filteredTasks = project.getTasks().stream().filter(taskDTO -> !taskDTO.getStatus().equals("hidden")).collect(Collectors.toList());
        project.setTasks(filteredTasks);

        return new ResponseEntity(gson.toJson(project), HttpStatus.OK);
    }

    @PutMapping("/project")
    public ResponseEntity updateProject(@RequestBody Map<String, String> body) throws ParseException {
        String projectName = body.get("projectName");
        String endDate = body.get("endDate");
        int companyId = Integer.parseInt(body.get("companyId"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = sdf.parse(endDate);

        CompanyDTO company = companyRepo.findCompanyById(companyId);

        Project updatedProject = new Project(projectName, date, new Company(company), true);
        updatedProject.setId(Integer.parseInt(body.get("projectId")));
        projectRepo.save(updatedProject);

        return new ResponseEntity<>(gson.toJson(updatedProject), HttpStatus.OK);
    }

    @PutMapping("/project/disable")
    public ResponseEntity disableProject(@RequestBody Map<String, String> body) {

        ProjectDTO projectDTO = projectRepo.getProjectById(Integer.parseInt(body.get("projectId")));


        Project projectToDisable = new Project(projectDTO.getName(), projectDTO.getEndDate(), new Company(projectDTO.getCompanyId()), false);
        projectToDisable.setId(Integer.parseInt(body.get("projectId")));
        projectRepo.save(projectToDisable);

        taskRepo.disableTask(Integer.parseInt(body.get("projectId")), "hidden");

        return new ResponseEntity<>(gson.toJson(projectToDisable), HttpStatus.OK);
    }
}
