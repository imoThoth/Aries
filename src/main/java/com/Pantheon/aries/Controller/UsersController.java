package com.Pantheon.aries.Controller;

import com.Pantheon.aries.Model.EndUser;
import com.Pantheon.aries.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
     public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap){
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        EndUser user = userService.validateUser(email, password);
        Map<String, String> map = new HashMap<>();
        map.put("message", "login succesfully");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<String, Object> userMap){
        String firstName = (String) userMap.get("firstName");
        String lastName = (String) userMap.get("lastName");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        EndUser user = userService.registerUser(firstName, lastName, email, password);
        Map<String, String> map = new HashMap<>();
        map.put("message", "registered succesfully");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
