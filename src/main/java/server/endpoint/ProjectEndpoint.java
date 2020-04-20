package server.endpoint;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.dto.CompanyDTO;
import server.dto.ProjectDTO;
import server.entity.Company;
import server.entity.Project;
import server.repository.CompanyRepo;
import server.repository.ProjectRepo;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
public class ProjectEndpoint {

    @Autowired
    ProjectRepo projectRepo;

    @Autowired
    CompanyRepo companyRepo;

    Gson gson = new Gson();

    @PostMapping("/project/create")
    public ResponseEntity<String> createProject(@RequestBody Map<String, String> body) throws ParseException {
        String projectName = body.get("projectName");
        String endDate = body.get("endDate").substring(0,10);
        int companyId = Integer.parseInt(body.get("companyId"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = sdf.parse(endDate);

        CompanyDTO company = companyRepo.findCompanyById(companyId);

        Project newProject = new Project(projectName, date, new Company(company));
        projectRepo.save(newProject);

        return new ResponseEntity<>(gson.toJson(newProject), HttpStatus.OK);
    }

    @GetMapping("/project/all")
    public ResponseEntity getProjects(@RequestParam int companyId){
        List<ProjectDTO> projectDTOS = projectRepo.findByCompanyId(companyId);
        return new ResponseEntity(gson.toJson(projectDTOS), HttpStatus.OK);
    }
}
