package com.springapp.service;

import com.springapp.domain.*;
import com.springapp.service.http.MopHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 01/10/2014
 * Time: 19:13
 * To change this template use File | Settings | File Templates.
 */
@Service
public class AccountServiceImpl implements IAccountService{

    @Autowired
    private MopHttpClient mopHttpClient;


    @Override
    public Account createAccount(User user) throws AccountCreationException {
        String password = createPassword();
        Account account = buildAccount(user,password);

        boolean success = mopHttpClient.createMopAccount(account);
        if(!success)
            throw new AccountCreationException("Account creation step failed for account:"+account);
        else {
            // I ll add the user to the account
            UserAccount userAccount = buildUserAccount(user,password);
            success = mopHttpClient.addUserToMopAccount(userAccount);
        }

        //TODO add calls for RoutingToMOP and for RoutingFromMOP

        return account;
    }

    private UserAccount buildUserAccount(User user,String password) {
        UserAccount userAccount = new UserAccount();
        userAccount.setName(user.getCf());
        PasswordCredentials passwordCredentials = new PasswordCredentials();
        passwordCredentials.setPassword(password);
        passwordCredentials.setUsername(user.getCf());
        userAccount.setPasswordCredentials(passwordCredentials);
        UserLink link = new UserLink(mopHttpClient.getHost(),mopHttpClient.getPort(),user.getCf());
        UserMetadata metadata = new UserMetadata();
        metadata.setLinks(link);
        userAccount.setMetadata(metadata);
        return userAccount;
    }


    private Account buildAccount(User user, String password) {
        Account account = new Account(user.getCf());
        account.setNotes(password);
        Contact contact = new Contact();
        contact.setEmail(user.getEmail());
        account.setContact(contact);
        account.setUid(createPID());
        AccountLink link = new AccountLink(mopHttpClient.getHost(),mopHttpClient.getPort(),user.getCf());
        AccountMetadata metadata = new AccountMetadata();
        metadata.setLinks(link);
        account.setMetadata(metadata);
        return account;
    }

    private String createPassword(){
        //TODO
        return "password";
    }

    private String createPID() {
        //TODO must be between 7006 a 7998
        return "7777";
    }


    public void setMopHttpClient(MopHttpClient mopHttpClient) {
        this.mopHttpClient = mopHttpClient;
    }
}
