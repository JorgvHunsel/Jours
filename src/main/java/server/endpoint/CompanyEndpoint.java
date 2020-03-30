package server.endpoint;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import server.entity.Company;
import server.entity.DAOUser;
import server.repository.CompanyRepo;
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

    Gson gson = new Gson();

    @PostMapping("/company/create")
    public ResponseEntity<String> createCompany(@RequestBody Map<String, String> body){
        String companyName = body.get("companyName");
        String userId = body.get("userId");

        DAOUser currentuser = userRepo.findById(Integer.parseInt(userId));
        currentuser.setCompanies(null);
        currentuser.setTasks(null);

        //get user by id
        //stop user in company
        //save company

        List<DAOUser> usersInCompany = new ArrayList<>();
        usersInCompany.add(currentuser);

        Company newCompany = new Company(companyName, usersInCompany);


        companyRepo.save(newCompany);
        return new ResponseEntity<>(gson.toJson(newCompany), HttpStatus.OK);
    }
}