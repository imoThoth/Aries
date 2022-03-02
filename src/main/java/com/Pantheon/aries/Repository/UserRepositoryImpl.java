package com.Pantheon.aries.Repository;

import com.Pantheon.aries.Exceptions.EtAuthException;
import com.Pantheon.aries.Model.EndUser;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String SQL_CREATE = "INSERT INTO dfp4f6l1hjchmf (USER_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD) VALUES (NEXTVAL('et_users_seq'), ?, ?, ?, ?)";

    private static final String SQL_COUNT_BY_EMAIL = "SELECT * FROM dfp4f6l1hjchmf WHERE EMAIL =?";

    private static final String SQL_FIND_BY_ID = "SELECT USER_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD FROM dfp4f6l1hjchmf WHERE USER_ID = ?";

    private static final String SQL_FIND_BY_EMAIL = "SELECT USER_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD FROM dfp4f6l1hjchmf WHERE EMAIL =?";

//    private static final String SQL_GET_ALL_USERNAMES = "SELECT FIRST_NAME FROM ET_USERS";

    @Autowired
    JdbcTemplate jdbcTemplate;

    BasicPasswordEncryptor encryptPassword = new BasicPasswordEncryptor();

    @Override
    public Integer create(String firstName, String lastName, String email, String password) throws EtAuthException {
        String hashedPassword = encryptPassword.encryptPassword(password);
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setString(3, email);
                ps.setString(4, hashedPassword);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("USER_ID");
        }catch (Exception e) {
            throw new EtAuthException("Invalid details. Failed to create account");
        }
    }

    @Override
    public EndUser findByEmailAndPassword(String email, String password) throws EtAuthException {
        try{
            EndUser user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, userRowMapper, email);
            if(user !=null && !encryptPassword.checkPassword(password, user.getPassword())) {throw new EtAuthException("Invalid email/ password");}

            return user;
        } catch(EmptyResultDataAccessException e){
            throw new EtAuthException("Password/Email Does Not Exist");
        }
    }

    @Override
    public EndUser getCountByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, userRowMapper);
                //queryForObject(SQL_COUNT_BY_EMAIL, new Object[]{email}, Integer.class);
    }

    @Override
    public EndUser findById(Integer id) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, userRowMapper);
    }


    private final RowMapper<EndUser> userRowMapper = ((rs, rowNum) -> new EndUser(rs.getInt("USER_ID"),
                        rs.getString("FIRST_NAME"),
                        rs.getString("LAST_NAME"),
                        rs.getString("EMAIL"),
                    rs.getString("PASSWORD")));
}
