package com.springapp.domain;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 01/10/2014
 * Time: 21:35
 * To change this template use File | Settings | File Templates.
 */
public class AccountTest {
    @Test
    public void testFromXML() throws Exception {

    }

    @Test
    public void testToXML() throws Exception {
        Account account = new Account("uMop01");
        account.setNotes("Psw_uMop01");
        Contact contact = new Contact();
        contact.setEmail("uMop01@mopstsrgs.it");
        account.setContact(contact);
        account.setUid("7006");
        AccountLink link = new AccountLink("vm-sts-coll01",444,"uMop01");
        AccountMetadata metadata = new AccountMetadata();
        metadata.setLinks(link);
        account.setMetadata(metadata);

        String xml = account.toXML();

        String xmlExpected= "<account><name>uMop01</name><uid>7006</uid><gid>540</gid><contact><email>uMop01@mopstsrgs.it</email></contact>" +
                "<disabled>false</disabled>" +
                "<businessUnit>MOP</businessUnit>" +
                "<notes>Psw_uMop01</notes>" +
                "<type>user</type>" +
                "<licensed>true</licensed>" +
                "<deliveryMethod>Disabled</deliveryMethod>" +
                "<routingMode>reject</routingMode>" +
                "<transferType>E</transferType>" +
                "<metadata>" +
                "<links>" +
                "<users>https://vm-sts-coll01:444/api/v1.0/accounts/uMop01/users</users>" +
                "<sites>https://vm-sts-coll01:444/api/v1.0/sites?account=uMop01</sites>" +
                "<subscriptions>https://vm-sts-coll01:444/api/v1.0/subscriptions?account=uMop01</subscriptions>" +
                "<transferProfiles>https://vm-sts-coll01:444/api/v1.0/transferProfiles?account=uMop01</transferProfiles>" +
                "</links>" +
                "</metadata>" +
                "</account>";

        Assert.assertEquals(xmlExpected.trim(),xml);

    }

    @Test
    public void testFromxml() throws Exception {
        Account account = new Account("uMop01");
        account.setNotes("Psw_uMop01");
        Contact contact = new Contact();
        contact.setEmail("uMop01@mopstsrgs.it");
        account.setContact(contact);
        account.setUid("7006");
        AccountLink link = new AccountLink("vm-sts-coll01",444,"uMop01");
        AccountMetadata metadata = new AccountMetadata();
        metadata.setLinks(link);
        account.setMetadata(metadata);

        String xml = account.toXML();

        String xmlExpected= "<account><name>uMop01</name><uid>7006</uid><gid>540</gid><contact><email>uMop01@mopstsrgs.it</email></contact>" +
                "<disabled>false</disabled>" +
                "<businessUnit>MOP</businessUnit>" +
                "<notes>Psw_uMop01</notes>" +
                "<type>user</type>" +
                "<licensed>true</licensed>" +
                "<deliveryMethod>Disabled</deliveryMethod>" +
                "<routingMode>reject</routingMode>" +
                "<transferType>E</transferType>" +
                "<metadata>" +
                "<links>" +
                "<users>https://vm-sts-coll01:444/api/v1.0/accounts/uMop01/users</users>" +
                "<sites>https://vm-sts-coll01:444/api/v1.0/sites?account=uMop01</sites>" +
                "<subscriptions>https://vm-sts-coll01:444/api/v1.0/subscriptions?account=uMop01</subscriptions>" +
                "<transferProfiles>https://vm-sts-coll01:444/api/v1.0/transferProfiles?account=uMop01</transferProfiles>" +
                "</links>" +
                "</metadata>" +
                "</account>";

        Assert.assertEquals(Account.fromXML(xmlExpected),account);

    }

}
