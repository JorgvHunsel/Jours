package server.endpoint;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.dto.UserDTO;
import server.dto.WorkDTO;
import server.entity.DAOUser;
import server.entity.Project;
import server.entity.Task;
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
        int taskId = Integer.parseInt(body.get("taskId"));

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

        if(body.get("endDate") == null){
            endDate = null;
        }

        Work newWork = new Work(beginDate, endDate, new DAOUser(userId), new Task(taskId));
        workRepo.save(newWork);

        return new ResponseEntity(gson.toJson(newWork), HttpStatus.OK);
    }

    @GetMapping("/work/clock")
    public ResponseEntity getWorkWithoutEndDate(Principal principal){
        int userId = userRepo.findByUsername(principal.getName()).getId();

        WorkDTO workWithout = workRepo.findWorkByUserWithoutEndDate(userId);
        return new ResponseEntity(gson.toJson(workWithout), HttpStatus.OK);
    }

    @PostMapping("work/update")
    public ResponseEntity updateWorkWithEndDate(@RequestBody Map<String, String> body){
        int workId = Integer.parseInt(body.get("workId"));
        workRepo.updateWork(workId, new Date());
        return new ResponseEntity(workId, HttpStatus.OK);
    }



    @GetMapping("/work/all")
    public ResponseEntity getWorkFromUser(){

        return new ResponseEntity(gson.toJson(""), HttpStatus.OK);
    }
}
