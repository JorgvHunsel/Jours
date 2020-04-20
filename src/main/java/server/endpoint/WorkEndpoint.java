package server.endpoint;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.dto.UserDTO;
import server.entity.DAOUser;
import server.entity.Project;
import server.entity.Work;
import server.repository.UserRepo;
import server.repository.WorkRepo;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

@RestController
public class WorkEndpoint {

    @Autowired
    WorkRepo workRepo;

    @Autowired
    UserRepo userRepo;

    Gson gson = new Gson();

    @PostMapping("/work/add")
    public ResponseEntity createProject(@RequestBody Map<String, String> body, Principal principal){
        int userId = userRepo.findByUsername(principal.getName()).getId();
        int projectId = Integer.parseInt(body.get("projectId"));

        Date beginDate = new Date();
        Date endDate = new Date();
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

            beginDate = sdf.parse(body.get("beginDate"));
            endDate = sdf.parse(body.get("endDate"));
        }catch (Exception e){
            //ignore
        }

        Work newWork = new Work(beginDate, endDate, new DAOUser(userId), new Project(projectId));
        workRepo.save(newWork);

        return new ResponseEntity(gson.toJson(newWork), HttpStatus.OK);
    }

    @GetMapping("/work/all")
    public ResponseEntity getWorkFromUser(){

        return new ResponseEntity(gson.toJson(""), HttpStatus.OK);
    }
}
