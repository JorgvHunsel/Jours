package server.endpoint;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.dto.UserDTO;
import server.repository.UserRepo;

@RestController
public class UserEndpoint {
    @Autowired
    UserRepo userRepository;

    Gson gson = new Gson();

    @GetMapping("/company/all")
    public ResponseEntity getCompaniesFromUser(@RequestParam int userId){
        UserDTO user = userRepository.findCompaniesByUser(userId);
        return new ResponseEntity(gson.toJson(user.getCompanies()), HttpStatus.OK);
    }
}
