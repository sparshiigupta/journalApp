package net.engineeringdigest.journalApp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Tag(name = "public api")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("health-check")
    public String HealthCheck() {
        return "Ok";
    }

    @PostMapping("create-user")
    public void createUser(@RequestBody User user) {
        userService.saveNewUser(user);
    }

}
