package server.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.dto.CompanyDTO;
import server.entity.Company;
import server.logic.CompanyLogic;
import server.logic.UserLogic;

import java.security.Principal;
import java.util.Map;

@RequestMapping(value="/company")
@RestController
public class CompanyController {
    CompanyLogic companyLogic;
    UserLogic userLogic;

    @Autowired
    public CompanyController(CompanyLogic companyLogic, UserLogic userlogic){
        this.companyLogic = companyLogic;
        this.userLogic = userlogic;
    }

    Gson gson = new Gson();

    @PostMapping("/create")
    public ResponseEntity<String> createCompany(@RequestBody Map<String, String> body, Principal principal) {
        int userId = userLogic.findByUsername(principal.getName()).getId();
        String companyName = body.get("companyName");

        Company newCompany;
        try {
            newCompany = companyLogic.createCompany(userId, companyName);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(gson.toJson(newCompany), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<String> editCompany(@RequestBody Map<String, String> body) {
        int companyId = Integer.parseInt(body.get("companyId"));
        String companyName = body.get("companyName");

        try{
            companyLogic.updateCompany(companyName, companyId);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(gson.toJson(companyId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> getCompany(@RequestParam int companyId, Principal principal) {
        int userId = userLogic.findByUsername(principal.getName()).getId();

       CompanyDTO companyDTO;
        try {
            companyDTO = companyLogic.getCompany(userId, companyId);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(gson.toJson(companyDTO), HttpStatus.OK);
    }

    @GetMapping("/code")
    public ResponseEntity<String> getNewCode(@RequestParam int companyId) {
        String newCode;
        try{
            newCode = companyLogic.getNewCode(companyId);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(gson.toJson(newCode), HttpStatus.OK);
    }

    @PutMapping("/join")
    public ResponseEntity<String> joinCompany(@RequestBody Map<String, String> body, Principal principal) {
        int userId = userLogic.findByUsername(principal.getName()).getId();
        String code = body.get("code");

        CompanyDTO companyDTO;
        try{
            companyDTO = companyLogic.joinCompany(userId, code);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(gson.toJson(companyDTO), HttpStatus.OK);
    }
}