package server.endpoint;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import server.entity.DAOUser;
import server.entity.Project;
import server.entity.Task;
import server.repository.TaskRepo;
import server.repository.UserRepo;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TaskEndpoint {

    @Autowired
    TaskRepo taskRepo;

    @Autowired
    UserRepo userRepository;

    Gson gson = new Gson();

    @PostMapping("/task/create")
    public ResponseEntity createTask(@RequestBody Map<String, String> body, Principal principal){
        String name = body.get("name");
        String description = body.get("description");
        int projectId = Integer.parseInt(body.get("projectId"));

        List<DAOUser> usersFromTask = new ArrayList<>();
        int userId = userRepository.findByUsername(principal.getName()).getId();
        usersFromTask.add(new DAOUser(userId));


        Task newTask = new Task(name, description, "to do", new Project(projectId), usersFromTask);
        taskRepo.save(newTask);
        return new ResponseEntity(gson.toJson(newTask), HttpStatus.OK);
    }

    @PostMapping("/task/status")
    public ResponseEntity changeTaskStatus(@RequestBody Map<String, String> body){
        int taskId = Integer.parseInt(body.get("taskId"));
        String currentStatus = body.get("status");
        boolean direction = Boolean.parseBoolean(body.get("direction"));

        String newStatus;
        switch(currentStatus){
            case "to do":
                newStatus = "doing";
                break;
            case "doing":
                if(direction){
                    newStatus = "done";
                }
                else{
                    newStatus = "to do";
                }
                break;
            case "done":
                if(direction){
                    newStatus = "hidden";
                }
                else{
                    newStatus = "doing";
                }
                break;
            default:
                newStatus = "error";
                break;
        }
        taskRepo.updateTaskStatus(taskId, newStatus);

        return new ResponseEntity(newStatus, HttpStatus.OK);
    }
}
