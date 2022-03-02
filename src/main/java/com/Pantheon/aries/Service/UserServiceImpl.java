package com.Pantheon.aries.Service;

import com.Pantheon.aries.Exceptions.EtAuthException;
import com.Pantheon.aries.Model.EndUser;
import com.Pantheon.aries.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
//
//    @Override
//    public EndUser validateUser(String email, String password) throws EtAuthException {
//        if(email != null) email = email.toLowerCase();
//        userRepository.
//        return userRepository.findByEmailAndPassword(email, password);
//    }

    @Override
    public Optional<EndUser> registerUser(String firstName, String lastName, String email, String password) throws EtAuthException {

        Pattern pattern = Pattern.compile("^(.+)@(.+)$"); //pattern for email
        if(email != null) email = email.toLowerCase(); //change email to lower case
        if(!pattern.matcher(email).matches()) throw new EtAuthException("Invalid Email Format"); //check if email matches pattern
       // Integer count = userRepository.getCountByEmail(email); //call method to check emails existence
       // if(count > 0) throw new EtAuthException("Email is already in use"); //throw exception if email exist
        Integer userId = userRepository.create(firstName, lastName, email, password); //creates the user
        return userRepository.findById(userId); //return newly created user id
    }

//    @Override
//    public List<EndUser> getAllInfo(){
//        return userRepository.
//    }
}
