package server.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import server.entity.Company;
import server.entity.DAOUser;
import server.service.CompanyService;
import server.service.CompanyUserService;
import server.service.UserService;

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
}
