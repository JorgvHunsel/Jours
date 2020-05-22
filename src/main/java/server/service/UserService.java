package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.dto.UserDTO;
import server.entity.DAOUser;
import server.repository.UserRepo;

@Service
public class UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo){this.userRepo = userRepo;}

    public DAOUser findByUsername(String username){return this.userRepo.findByUsername(username);}
    public UserDTO getUser(int userId){return this.userRepo.getUser(userId);}
}
