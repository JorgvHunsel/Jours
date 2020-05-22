package server.endpoint;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.dto.CompanyDTO;
import server.entity.Company;
import server.logic.CompanyLogic;
import server.repository.CompanyRepo;
import server.repository.CompanyUserRepo;
import server.repository.UserRepo;
import server.service.CodeGenerator;

import java.security.Principal;
import java.util.Map;

@RequestMapping(value="/company")
@RestController
public class CompanyEndpoint {

    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    CompanyUserRepo companyUserRepo;

    CompanyLogic companyLogic = new CompanyLogic();

    Gson gson = new Gson();

    @PostMapping("/create")
    public ResponseEntity<String> createCompany(@RequestBody Map<String, String> body, Principal principal) {
        int userId = userRepo.findByUsername(principal.getName()).getId();
        String companyName = body.get("companyName");

        Company newCompany = companyLogic.createCompany(userId, companyName);

        int companyId = companyRepo.save(newCompany).getId();
        companyUserRepo.setRole("admin", companyId, userId);

        return new ResponseEntity<>(gson.toJson(newCompany), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<String> editCompany(@RequestBody Map<String, String> body) {
        int companyId = Integer.parseInt(body.get("companyId"));
        String companyName = body.get("companyName");
        companyRepo.updateCompany(companyName, companyId);

        return new ResponseEntity<>(gson.toJson(companyId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> getCompany(@RequestParam int companyId, Principal principal) {
        int userId = userRepo.findByUsername(principal.getName()).getId();
        CompanyDTO company = companyRepo.findUsersFromCompany(companyId);
        company.setCurrentUserRole(userId);

        return new ResponseEntity<>(gson.toJson(company), HttpStatus.OK);
    }

    @GetMapping("/code")
    public ResponseEntity<String> getNewCode(@RequestParam int companyId) {
        String newCode = CodeGenerator.getRandomNumberString();
        companyRepo.setCompanyCode(companyId, newCode);

        return new ResponseEntity<>(gson.toJson(newCode), HttpStatus.OK);
    }

    @PutMapping("/join")
    public ResponseEntity<String> joinCompany(@RequestBody Map<String, String> body, Principal principal) {
        int userId = userRepo.findByUsername(principal.getName()).getId();
        String code = body.get("code");

        CompanyDTO currentCompany = companyRepo.findCompanyByCode(code);
        companyUserRepo.addUserToCompany(currentCompany.getId(), userId, "employee");

        return new ResponseEntity<>(gson.toJson(currentCompany), HttpStatus.OK);
    }
}