package server.endpoint;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.dto.UserDTO;
import server.entity.Company;
import server.entity.DAOUser;
import server.repository.CompanyRepo;
import server.repository.CompanyUserRepo;
import server.repository.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class CompanyEndpoint {

    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    CompanyUserRepo companyUserRepo;

    Gson gson = new Gson();

    @PostMapping("/company/create")
    public ResponseEntity<String> createCompany(@RequestBody Map<String, String> body) {
        String companyName = body.get("companyName");
        int userId = Integer.parseInt(body.get("userId"));

        UserDTO userDTO = userRepo.findById(userId);
        DAOUser user = new DAOUser(userDTO.getId(), userDTO.getUsername(), userDTO.getPassword());

        List<DAOUser> usersInCompany = new ArrayList<>();
        usersInCompany.add(user);

        Company newCompany = new Company(companyName, usersInCompany);


        int companyId = companyRepo.save(newCompany).getId();
        companyUserRepo.setRole("admin", companyId, userId);


        return new ResponseEntity<>(gson.toJson(newCompany), HttpStatus.OK);
    }

    @GetMapping("/company/users")
    public ResponseEntity<String> getUsersFromCompany(@RequestParam int companyId) {
        List<UserDTO> usersInCompany = companyRepo.findUsersFromCompany(companyId).getUsersInCompany();
        return new ResponseEntity<>(gson.toJson(usersInCompany), HttpStatus.OK);
    }
}
