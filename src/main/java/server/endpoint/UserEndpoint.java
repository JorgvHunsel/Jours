package server.endpoint;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import server.entity.User;
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
        User currentUser = userRepository.findByUsername(username);

        return new ResponseEntity(gson.toJson(currentUser), HttpStatus.OK);
    }
}
