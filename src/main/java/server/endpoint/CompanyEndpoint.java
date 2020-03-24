package server.endpoint;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import server.entity.Company;
import server.repository.CompanyRepo;

import java.util.Map;

@RestController
public class CompanyEndpoint {

    @Autowired
    CompanyRepo companyRepo;

    Gson gson = new Gson();

    @PostMapping("/company/create")
    public ResponseEntity createCompany(@RequestBody Map<String, String> body){
        String companyName = body.get("companyName");
        String userId = body.get("userId");

        //get user by id
        //stop user in company
        //save company

        Company newCompany = new Company();
        return new ResponseEntity(gson.toJson(newCompany), HttpStatus.OK);
    }
}
