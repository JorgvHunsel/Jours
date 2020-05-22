package server.endpoint;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.entity.DAOUser;
import server.entity.Project;
import server.entity.Task;
import server.logic.TaskLogic;
import server.repository.TaskRepo;
import server.repository.UserRepo;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RequestMapping(value="/task")
@RestController
public class TaskEndpoint {

    @Autowired
    TaskRepo taskRepo;
    @Autowired
    UserRepo userRepository;

    Gson gson = new Gson();

    TaskLogic taskLogic = new TaskLogic();

    @PostMapping("/create")
    public ResponseEntity<String> createTask(@RequestBody Map<String, String> body){
        String name = body.get("name");
        String description = body.get("description");
        int projectId = Integer.parseInt(body.get("projectId"));
        List<DAOUser> usersFromTask = gson.fromJson(body.get("users"), new TypeToken<List<DAOUser>>(){}.getType());

        Task newTask = new Task(name, description, "to do", new Project(projectId), usersFromTask);
        taskRepo.save(newTask);
        return new ResponseEntity<>(gson.toJson(newTask), HttpStatus.OK);
    }

    @PostMapping("/status")
    public ResponseEntity<String> changeTaskStatus(@RequestBody Map<String, String> body){
        int taskId = Integer.parseInt(body.get("taskId"));
        String currentStatus = body.get("status");
        boolean direction = Boolean.parseBoolean(body.get("direction"));

        String newStatus = taskLogic.determineNewStatus(currentStatus, direction);
        taskRepo.updateTaskStatus(taskId, newStatus);

        return new ResponseEntity<>(newStatus, HttpStatus.OK);
    }
}
