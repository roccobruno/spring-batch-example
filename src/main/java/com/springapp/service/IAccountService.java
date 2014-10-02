package com.springapp.service;

import com.springapp.domain.http.account.Account;
import com.springapp.domain.User;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 01/10/2014
 * Time: 19:13
 * To change this template use File | Settings | File Templates.
 */
public interface IAccountService {

    public Account createAccount(User user) throws AccountCreationException;

}
