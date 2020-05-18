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

@RestController
public class UserEndpoint {
    @Autowired
    UserRepo userRepository;

    Gson gson = new Gson();

    @GetMapping("/user/company")
    public ResponseEntity<String> getCompaniesFromUser(@RequestParam int userId){
        UserDTO user = userRepository.getUser(userId);
        return new ResponseEntity<>(gson.toJson(user.getCompanies()), HttpStatus.OK);
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public int currentUserName(Principal principal) {
        DAOUser user = userRepository.findByUsername(principal.getName());
        return user.getId();
    }

    @GetMapping("/user/tasks")
    public ResponseEntity<String> getTasksFromUser(Principal principal){
        int userId = userRepository.findByUsername(principal.getName()).getId();
        UserDTO userWithTasks = userRepository.getUser(userId);

        List<TaskDTO> filteredTasks = userWithTasks.getTasks().stream().filter(taskDTO -> !taskDTO.getStatus().equals("hidden")).collect(Collectors.toList());
        return new ResponseEntity<>(gson.toJson(filteredTasks), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<String> getUser(@RequestParam int userId){
        UserDTO user = userRepository.getUser(userId);
        return new ResponseEntity<>(gson.toJson(user), HttpStatus.OK);
    }
}
