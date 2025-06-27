package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    private static final Logger logger  = LoggerFactory.getLogger(UserService.class);

    public void saveEntry(User user) {
        userRepository.save(user);
    }
    public void saveNewUser(User user) {
        try {
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public List<User> getAll()
    {
        return userRepository.findAll();
    }
    
    public Optional<User> findById(ObjectId userId){
        return userRepository.findById(userId);
    }

    public void deleteById(ObjectId userId){
        userRepository.deleteById(userId);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveAdmin(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }
}
