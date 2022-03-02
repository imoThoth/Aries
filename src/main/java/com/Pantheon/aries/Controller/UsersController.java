package com.Pantheon.aries.Controller;

import com.Pantheon.aries.Constants;
import com.Pantheon.aries.Model.EndUser;
import com.Pantheon.aries.Service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UsersController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
     public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap){
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
      //  EndUser user = userService.validateUser(email, password);
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



    //Generate JWT Token
//    private Map<String, String> generateJWTToken(EndUser user){
//        long timeStamp = System.currentTimeMillis();
//        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
//                .setIssuedAt(new Date(timeStamp))
//                .setExpiration(new Date(timeStamp + Constants.TOKEN_VALIDITY))
//                .claim("userId", user.getUserId())
//                .claim("email", user.getEmail())
//                .claim("firstName", user.getFirstName())
//                .claim("lastName", user.getLastName())
//                .compact();
//        Map<String, String> map = new HashMap<>();
//        map.put("token", token);
//        return map;
//    }
}
