package com.Pantheon.aries.Repository;

import com.Pantheon.aries.Exceptions.EtAuthException;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public abstract class UserRepositoryImpl implements UserRepository {

    private static final String SQL_CREATE = "INSERT INTO ET_USERS (USER_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD) VALUES (NEXTVAL('et_users_seq'), ?, ?, ?, ?);";

  //  private static final String SQL_COUNT_BY_EMAIL = "SELECT * FROM ET_USERS WHERE EMAIL = $1;";

   // private static final String SQL_FIND_BY_ID = "SELECT * FROM ET_USERS WHERE USER_ID =$1;";

  //  private static final String SQL_FIND_BY_EMAIL = "SELECT * FROM ET_USERS WHERE EMAIL = $1;";

//    private static final String SQL_GET_ALL_USERNAMES = "SELECT FIRST_NAME FROM ET_USERS";

    @Autowired
    JdbcTemplate jdbcTemplate;

    BasicPasswordEncryptor encryptPassword = new BasicPasswordEncryptor();

    @Override
    public Integer create(String firstName, String lastName, String email, String password) throws EtAuthException {
        String hashedPassword = encryptPassword.encryptPassword(password);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setString(3, email);
                ps.setString(4, hashedPassword);
                return ps;
            }, keyHolder);
            return (Integer) Objects.requireNonNull(keyHolder.getKeys()).get("USER_ID");
        }catch (EmptyResultDataAccessException e) {
            throw new EtAuthException("Invalid details. Failed to create account");
        }
//        return (Integer) Objects.requireNonNull(keyHolder.getKeys()).get("USER_ID");

    }

//    @Override
//    public EndUser findByEmailAndPassword(String email, String password) throws EtAuthException {
//        try{
//            EndUser user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, userRowMapper, email);
//            if(user !=null && !encryptPassword.checkPassword(password, user.getPassword())) {throw new EtAuthException("Invalid email/ password");}
//
//            return user;
//        } catch(EmptyResultDataAccessException e){
//            throw new EtAuthException("Password/Email Does Not Exist");
//        }
//    }

//    @Override
//    public Integer getCountByEmail(String email) {
//        try{
//            return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, new Object[] {email}, (rs, rowNum) -> Optional.of(userRowMapper(rs)));
//        }
//
//
//
//        //return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, new Object[]{email}, Integer.class);
////                .queryForObject(SQL_COUNT_BY_EMAIL, userRowMapper);
//                //queryForObject(SQL_COUNT_BY_EMAIL, new Object[]{email}, Integer.class);
//    }

//    @Override
//    public EndUser findById(Integer id) {
//        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{id} ,userRowMapper);
//    }

//
//    private final RowMapper<EndUser> userRowMapper = ((rs, rowNum) -> new EndUser(rs.getInt("USER_ID"),
//                        rs.getString("FIRST_NAME"),
//                        rs.getString("LAST_NAME"),
//                        rs.getString("EMAIL"),
//                    rs.getString("PASSWORD")));


}
