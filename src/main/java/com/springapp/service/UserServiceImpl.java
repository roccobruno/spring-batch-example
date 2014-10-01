package com.springapp.service;

import com.springapp.domain.User;
import com.springapp.domain.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 01/10/2014
 * Time: 18:31
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> findAllTheUsersWithNoAccount() {
        return userDAO.findAllWithoutAccount();
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public User findById(String id) {
        return userDAO.findByCF(id);
    }
}
