package server.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.dto.CompanyDTO;
import server.dto.ProjectDTO;
import server.dto.TaskDTO;
import server.entity.Project;
import server.logic.CompanyLogic;
import server.logic.ProjectLogic;
import server.logic.TaskLogic;
import java.util.List;
import java.util.Map;

@RequestMapping(value ="/project")
@RestController
public class ProjectController {

    ProjectLogic projectLogic;
    TaskLogic taskLogic;
    CompanyLogic companyLogic;

    @Autowired
    public ProjectController(ProjectLogic projectLogic, TaskLogic taskLogic, CompanyLogic companyLogic){
        this.projectLogic = projectLogic;
        this.taskLogic = taskLogic;
        this.companyLogic = companyLogic;
    }

    Gson gson = new Gson();

    @PutMapping
    public ResponseEntity<String> saveProject(@RequestBody Map<String, String> body) {
        String projectName = body.get("projectName");
        String endDate = body.get("endDate");
        int companyId = Integer.parseInt(body.get("companyId"));
        int projectId = 0;
        if(body.get("projectId") != null){
            projectId = Integer.parseInt(body.get("projectId"));
        }

        Project project;
        try {
            CompanyDTO companyDTO = companyLogic.findCompanyById(companyId);
            project = projectLogic.saveProject(projectName, endDate, projectId, companyDTO);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(gson.toJson(project), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<String> getProjectsFromCompany(@RequestParam int companyId){

        List<ProjectDTO> filteredProjects;
        try{
            filteredProjects = projectLogic.getProjectsFromCompany(companyId);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(gson.toJson(filteredProjects), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> getProject(@RequestParam int projectId){
        ProjectDTO project;
        try {
            project = projectLogic.getProject(projectId);
            List<TaskDTO> filteredTasks = taskLogic.filterActiveTasks(project.getTasks());
            project.setTasks(filteredTasks);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(gson.toJson(project), HttpStatus.OK);
    }

    @PutMapping("/disable")
    public ResponseEntity<String> disableProject(@RequestParam int projectId) {

        Project projectToDisable;
        try {
            projectToDisable = projectLogic.disableProject(projectId);
            taskLogic.disableTask(projectId);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(gson.toJson(projectToDisable), HttpStatus.OK);
    }
}
