package com.Pantheon.aries.Service;

import com.Pantheon.aries.Exceptions.EtAuthException;
import com.Pantheon.aries.Model.EndUser;
import java.util.*;

public interface UserService {

    EndUser validateUser(String email, String password) throws EtAuthException;

    EndUser registerUser(String firstName, String lastName, String email, String password) throws EtAuthException;

//    List<EndUser> getAllInfo() throws EtAuthException;
}
