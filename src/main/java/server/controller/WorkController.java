package server.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.dto.WorkDTO;
import server.entity.Work;
import server.logic.TaskLogic;
import server.logic.UserLogic;
import server.logic.WorkLogic;

import java.security.Principal;
import java.util.Date;
import java.util.Map;

@RequestMapping(value = "/work")
@RestController
public class WorkController {

    WorkLogic workLogic;
    UserLogic userLogic;
    TaskLogic taskLogic;

    @Autowired
    public WorkController(WorkLogic workLogic, UserLogic userLogic, TaskLogic taskLogic) {
        this.workLogic = workLogic;
        this.userLogic = userLogic;
        this.taskLogic = taskLogic;
    }

    Gson gson = new Gson();

    @PostMapping
    public ResponseEntity<String> addWork(@RequestBody Map<String, String> body, Principal principal) {
        int userId = userLogic.findByUsername(principal.getName()).getId();
        int taskId = Integer.parseInt(body.get("taskId"));
        String beginDate = body.get("beginDate");
        String endDate = "";
        if (body.get("endDate") != null) {
            endDate = body.get("endDate");
        }

        Work newWork;
        try {
            newWork = workLogic.addWork(userId, taskId, beginDate, endDate);
            taskLogic.updateTaskStatus(taskId);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(gson.toJson(newWork), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> getWorkWithoutEndDate(Principal principal) {
        int userId = userLogic.findByUsername(principal.getName()).getId();

        WorkDTO workWithoutEndDate;
        try {
            workWithoutEndDate = workLogic.findWorkByUserWithoutEndDate(userId);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(gson.toJson(workWithoutEndDate), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Integer> updateWorkWithEndDate(@RequestBody Map<String, String> body) {
        int workId = Integer.parseInt(body.get("workId"));

        try {
            workLogic.updateWork(workId, new Date());
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(workId, HttpStatus.OK);
    }
}
