package com.Pantheon.aries.Repository;

import com.Pantheon.aries.Exceptions.EtAuthException;
import com.Pantheon.aries.Model.EndUser;


public interface UserRepository {

    Integer create(String firstName, String lastName, String email, String password) throws EtAuthException;

    EndUser findByEmailAndPassword (String email, String password) throws EtAuthException;

//    Integer getCountByEmail(String email);

    EndUser findById(Integer id);

//    String getAllNames();
}
