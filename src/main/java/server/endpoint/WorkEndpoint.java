package server.endpoint;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.dto.WorkDTO;
import server.entity.Work;
import server.logic.WorkLogic;
import server.repository.UserRepo;
import server.repository.WorkRepo;

import java.security.Principal;
import java.util.Date;
import java.util.Map;

@RequestMapping(value = "/work")
@RestController
public class WorkEndpoint {

    @Autowired
    WorkRepo workRepo;

    @Autowired
    UserRepo userRepo;

    Gson gson = new Gson();

    WorkLogic workLogic = new WorkLogic();

    @PostMapping
    public ResponseEntity<String> addWork(@RequestBody Map<String, String> body, Principal principal){
        int userId = userRepo.findByUsername(principal.getName()).getId();
        int taskId = Integer.parseInt(body.get("taskId"));
        String beginDate = body.get("beginDate");
        String endDate = "";
        if(body.get("endDate") != null){
            endDate = body.get("endDate");
        }

        Work newWork = workLogic.addWork(userId, taskId, beginDate, endDate);

        workRepo.save(newWork);

        return new ResponseEntity<>(gson.toJson(newWork), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> getWorkWithoutEndDate(Principal principal){
        int userId = userRepo.findByUsername(principal.getName()).getId();

        WorkDTO workWithout = workRepo.findWorkByUserWithoutEndDate(userId);
        return new ResponseEntity<>(gson.toJson(workWithout), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Integer> updateWorkWithEndDate(@RequestBody Map<String, String> body){
        int workId = Integer.parseInt(body.get("workId"));
        workRepo.updateWork(workId, new Date());
        return new ResponseEntity<>(workId, HttpStatus.OK);
    }
}
