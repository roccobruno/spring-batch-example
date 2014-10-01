package com.springapp.domain.dao;

import com.springapp.domain.ACCOUNT_FLAG_VALUES;
import com.springapp.domain.User;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 01/10/2014
 * Time: 18:38
 * To change this template use File | Settings | File Templates.
 */
public class UserDaoImpl extends JdbcDaoSupport implements UserDAO {
    @Override
    public User findByCF(String id) {

        String sql = getFindQuery(USER_TABLE_CONSTANTS.CF);

        List<User> user = getJdbcTemplate().query(
                sql, new Object[]{id}, new UserRowMapper());

        if(user.isEmpty())
            return null;

        return user.get(0);
    }

    @Override
    public User findByEmail(String email) {
        String sql = getFindQuery(USER_TABLE_CONSTANTS.EMAIL);
        User user = (User)getJdbcTemplate().query(
                sql, new Object[] { email }, new UserRowMapper());
        return user;
    }

    @Override
    public List<User> findAllWithoutAccount() {
      return findAllUser(ACCOUNT_FLAG_VALUES.WITHOUT_ACCOUNT);
    }

    @Override
    public List<User> findAllWithAccount() {
        return findAllUser(ACCOUNT_FLAG_VALUES.WITH_ACCOUNT);
    }


    private List<User> findAllUser(ACCOUNT_FLAG_VALUES account_flag_values) {
        String sql = getFindQuery(USER_TABLE_CONSTANTS.FLAG_INOLTRO_RICHIESTA);
        List<User> results =
                getJdbcTemplate().query(
                        sql, new Object[] {account_flag_values.getId()}, new UserRowMapper());

        return results;
    }

    @Override
    public boolean updateUser(User user) {
        StringBuilder builder = new StringBuilder("UPDATE ").append(USER_TABLE_CONSTANTS.TABLE_NAME)
                .append(" SET ").append(USER_TABLE_CONSTANTS.FLAG_INOLTRO_RICHIESTA).append("=? ")
                .append("WHERE ").append(USER_TABLE_CONSTANTS.CF).append("=?");
        String sql = builder.toString();
        int numOfRecordsUpdated = getJdbcTemplate().update(sql,user.getFlagRichiestaInoltrata(),user.getCf());
        if (numOfRecordsUpdated ==1)
            return true;
        else return false;
    }


    private String getFindQuery(String fieldName) {
        StringBuilder builder = new StringBuilder("SELECT * FROM ").append(USER_TABLE_CONSTANTS.TABLE_NAME)
                .append(" WHERE ").append(fieldName).append("=?");
        return builder.toString();
    }
}
