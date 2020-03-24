package server.endpoint;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import server.entity.DAOUser;
import server.repository.UserRepo;

import java.util.Map;

@RestController
public class UserEndpoint {
    @Autowired
    UserRepo userRepository;

    Gson gson = new Gson();

    @PostMapping("user/login")
    public ResponseEntity login(@RequestBody Map<String, String> body){
        String username = body.get("username");
        String password = body.get("password");
        DAOUser currentUser = userRepository.findByUsername(username);

        return new ResponseEntity(gson.toJson(currentUser), HttpStatus.OK);
    }

    public DAOUser getUserByName(String username){
        return userRepository.findByUsername(username);
    }
}
