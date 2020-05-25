package server.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import server.dto.TaskDTO;
import server.dto.UserDTO;
import server.entity.Company;
import server.entity.DAOUser;
import server.service.CompanyService;
import server.service.CompanyUserService;
import server.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserLogic {

    private final UserService userService;

    @Autowired
    public UserLogic(UserService userService){
        this.userService = userService;
    }

    public DAOUser findByUsername(String name) {
        return userService.findByUsername(name);
    }

    public UserDTO getUser(int userId) {
        return userService.getUser(userId);
    }

    public List<TaskDTO> getTasksFromUser(int userId) {
        UserDTO userWithTasks = getUser(userId);
        return userWithTasks.getTasks().stream().filter(taskDTO -> !taskDTO.getStatus().equals("hidden")).collect(Collectors.toList());
    }
}
