package server.endpoint;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import server.config.JwtTokenUtil;
import server.dto.UserDTO;
import server.entity.DAOUser;
import server.repository.UserRepo;
import server.service.JwtUserDetailsService;

import java.security.Principal;

@RestController
public class UserEndpoint {
    @Autowired
    UserRepo userRepository;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    Gson gson = new Gson();

    @GetMapping("/company/all")
    public ResponseEntity getCompaniesFromUser(@RequestParam int userId){
        UserDTO user = userRepository.findCompaniesByUser(userId);
        return new ResponseEntity(gson.toJson(user.getCompanies()), HttpStatus.OK);
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public int currentUserName(Principal principal) {
        DAOUser user = userRepository.findByUsername(principal.getName());
        return user.getId();
    }
}
