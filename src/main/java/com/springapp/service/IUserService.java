package com.springapp.service;

import com.springapp.domain.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 01/10/2014
 * Time: 18:31
 * To change this template use File | Settings | File Templates.
 */
public interface IUserService {

    List<User> findAllTheUsersWithNoAccount();
    void updateUser(User user);
    User findById(String id);


}
