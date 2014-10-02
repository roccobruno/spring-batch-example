package com.springapp.domain;

import com.springapp.domain.http.user.PasswordCredentials;
import com.springapp.domain.http.user.UserAccount;
import com.springapp.domain.http.user.UserLink;
import com.springapp.domain.http.user.UserMetadata;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 01/10/2014
 * Time: 23:41
 * To change this template use File | Settings | File Templates.
 */
public class UserAccountTest {
    @Test
    public void testFromXML() throws Exception {
        UserAccount userAccount = new UserAccount();
        userAccount.setName("uMop01");
        PasswordCredentials passwordCredentials = new PasswordCredentials();
        passwordCredentials.setPassword("Psw_uMop01");
        passwordCredentials.setUsername("uMop01");
        userAccount.setPasswordCredentials(passwordCredentials);
        UserLink link = new UserLink("vm-sts-coll01",444,"uMop01");
        UserMetadata metadata = new UserMetadata();
        metadata.setLinks(link);
        userAccount.setMetadata(metadata);


        String xmlTomatch = "<user>" +
                "<name>uMop01</name>" +
                "<authExternal>false</authExternal>" +
                "<locked>false</locked>" +
                "<failedAuthAttempts>0</failedAuthAttempts>" +
                "<passwordCredentials><username>uMop01</username>" +
                "<password>Psw_uMop01</password>" +
                "<forcePasswordChange>true</forcePasswordChange>" +
                "</passwordCredentials>" +
                "<metadata>" +
                "<links>" +
                "<account>https://vm-sts-coll01:444/api/v1.0/accounts/uMop01</account>" +
                "<user>https://vm-sts-coll01:444/api/v1.0/accounts/uMop01/users/uMop01</user>" +
                "</links>" +
                "</metadata>" +
                "</user>";

        Assert.assertEquals(UserAccount.fromXML(xmlTomatch),userAccount);
    }

    @Test
    public void testToXML() throws Exception {
        UserAccount userAccount = new UserAccount();
        userAccount.setName("uMop01");
        PasswordCredentials passwordCredentials = new PasswordCredentials();
        passwordCredentials.setPassword("Psw_uMop01");
        passwordCredentials.setUsername("uMop01");
        userAccount.setPasswordCredentials(passwordCredentials);
        UserLink link = new UserLink("vm-sts-coll01",444,"uMop01");
        UserMetadata metadata = new UserMetadata();
        metadata.setLinks(link);
        userAccount.setMetadata(metadata);
        
        
        String xmlTomatch = "<user>" +
                "<name>uMop01</name>" +
                "<authExternal>false</authExternal>" +
                "<locked>false</locked>" +
                "<failedAuthAttempts>0</failedAuthAttempts>" +
                "<passwordCredentials><username>uMop01</username>" +
                "<password>Psw_uMop01</password>" +
                "<forcePasswordChange>true</forcePasswordChange>" +
                "</passwordCredentials>" +
                "<metadata>" +
                "<links>" +
                "<account>https://vm-sts-coll01:444/api/v1.0/accounts/uMop01</account>" +
                "<user>https://vm-sts-coll01:444/api/v1.0/accounts/uMop01/users/uMop01</user>" +
                "</links>" +
                "</metadata>" +
                "</user>";

        Assert.assertEquals(xmlTomatch,userAccount.toXML());
    }
}
