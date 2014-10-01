package com.springapp.domain.dao;

import com.springapp.domain.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 01/10/2014
 * Time: 18:37
 * To change this template use File | Settings | File Templates.
 */
public interface UserDAO {


    public static final String CF = "CODICE_FISCALE";

    public User findByCF(String cf); //since cf is primary key
    public User findByEmail(String email);
    public List<User> findAllWithoutAccount();
    public List<User> findAllWithAccount();
    public boolean updateUser(User user);

}
