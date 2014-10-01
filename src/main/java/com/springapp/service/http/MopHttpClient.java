package com.springapp.service.http;

import com.springapp.domain.Account;
import com.springapp.domain.UserAccount;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 01/10/2014
 * Time: 21:59
 * To change this template use File | Settings | File Templates.
 */
public interface MopHttpClient {

    public boolean createMopAccount(Account account);
    public boolean addUserToMopAccount(UserAccount userAccount);
    public String getHost();
    public int getPort();
}
