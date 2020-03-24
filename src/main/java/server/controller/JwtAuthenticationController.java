package server.controller;

import java.util.Map;
import java.util.Objects;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import server.config.JwtTokenUtil;
import server.dto.UserDTO;
import server.endpoint.UserEndpoint;
import server.entity.DAOUser;
import server.repository.UserRepo;
import server.service.JwtUserDetailsService;
import server.model.JwtRequest;
import server.model.JwtResponse;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    UserRepo userRepository;

    Gson gson = new Gson();

    UserEndpoint userEndpoint = new UserEndpoint();

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        DAOUser currentUser = userRepository.findByUsername(authenticationRequest.getUsername());
        //hier moet een userid in komen
        final String token = jwtTokenUtil.generateToken(userDetails, currentUser);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @RequestMapping(value = "/isLegit", method = RequestMethod.POST)
    public Boolean validateToken(@RequestBody Map<String, String> body){
        String token = body.get("token");
        String username = body.get("username");
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        return jwtTokenUtil.validateToken(token, userDetails);
    }
}
