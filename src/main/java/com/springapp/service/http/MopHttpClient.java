package com.springapp.service.http;

import com.springapp.domain.http.account.Account;
import com.springapp.domain.http.subscription.Subscriptions;
import com.springapp.domain.http.transferconfiguration.TransferConfigurations;
import com.springapp.domain.http.user.UserAccount;

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
    public boolean createSubscriptionForTheUser(Subscriptions subscriptions);
    public boolean createTransferConfigurationForTheUser(TransferConfigurations transferConfigurations,String subId);
    public String getSubscriptionIdForTheUser(String application,String accountName);
    public String getHost();
    public int getPort();
}
