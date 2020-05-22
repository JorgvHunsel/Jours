package server.endpoint;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.dto.TaskDTO;
import server.dto.UserDTO;
import server.entity.DAOUser;
import server.repository.UserRepo;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(value = "/user")
@RestController
public class UserEndpoint {
    @Autowired
    UserRepo userRepository;

    Gson gson = new Gson();

    @GetMapping
    public ResponseEntity<String> getUser(Principal principal){
        int userId = userRepository.findByUsername(principal.getName()).getId();
        UserDTO user = userRepository.getUser(userId);
        return new ResponseEntity<>(gson.toJson(user), HttpStatus.OK);
    }

    @GetMapping("/company")
    public ResponseEntity<String> getCompaniesFromUser(Principal principal){
        int userId = userRepository.findByUsername(principal.getName()).getId();
        UserDTO user = userRepository.getUser(userId);
        return new ResponseEntity<>(gson.toJson(user.getCompanies()), HttpStatus.OK);
    }

    @GetMapping("/tasks")
    public ResponseEntity<String> getTasksFromUser(Principal principal){
        int userId = userRepository.findByUsername(principal.getName()).getId();
        UserDTO userWithTasks = userRepository.getUser(userId);

        List<TaskDTO> filteredTasks = userWithTasks.getTasks().stream().filter(taskDTO -> !taskDTO.getStatus().equals("hidden")).collect(Collectors.toList());
        return new ResponseEntity<>(gson.toJson(filteredTasks), HttpStatus.OK);
    }


}
