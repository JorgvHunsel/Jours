package server.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.dto.TaskDTO;
import server.dto.UserDTO;
import server.logic.UserLogic;
import java.security.Principal;
import java.util.List;

@RequestMapping(value = "/user")
@RestController
public class UserController {

    UserLogic userLogic;

    @Autowired
    public UserController(UserLogic userLogic){
        this.userLogic = userLogic;
    }

    Gson gson = new Gson();

    @GetMapping
    public ResponseEntity<String> getUser(Principal principal){
        int userId = userLogic.findByUsername(principal.getName()).getId();

        UserDTO user;
        try{
            user = userLogic.getUser(userId);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(gson.toJson(user), HttpStatus.OK);
    }

    @GetMapping("/company")
    public ResponseEntity<String> getCompaniesFromUser(Principal principal){
        int userId = userLogic.findByUsername(principal.getName()).getId();

        UserDTO user;
        try{
            user = userLogic.getUser(userId);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(gson.toJson(user.getCompanies()), HttpStatus.OK);
    }

    @GetMapping("/tasks")
    public ResponseEntity<String> getTasksFromUser(Principal principal){
        int userId = userLogic.findByUsername(principal.getName()).getId();

        List<TaskDTO> filteredTasks;
        try{
            filteredTasks = userLogic.getTasksFromUser(userId);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(gson.toJson(filteredTasks), HttpStatus.OK);
    }


}
