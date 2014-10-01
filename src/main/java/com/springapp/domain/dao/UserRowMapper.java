package com.springapp.domain.dao;

import com.springapp.domain.User;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 01/10/2014
 * Time: 18:44
 * To change this template use File | Settings | File Templates.
 */
public class UserRowMapper implements ParameterizedRowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setCf(resultSet.getString(USER_TABLE_CONSTANTS.CF));
        user.setCognome(resultSet.getString(USER_TABLE_CONSTANTS.COGNOME));
        user.setNome(resultSet.getString(USER_TABLE_CONSTANTS.NAME));
        user.setDataInoltroRichiesta(resultSet.getDate(USER_TABLE_CONSTANTS.DATA_INOLTRO_RICHIESTA));
        user.setDataRichiesta(resultSet.getDate(USER_TABLE_CONSTANTS.DATA_RICHIESTA));
        user.setEmail(resultSet.getString(USER_TABLE_CONSTANTS.EMAIL));
        user.setFlagRichiestaInoltrata(resultSet.getString(USER_TABLE_CONSTANTS.FLAG_INOLTRO_RICHIESTA));
        return user;
    }
}
