package com.springapp.service.http;

import com.springapp.config.ConfigTest;
import com.springapp.domain.*;
import com.springapp.domain.http.account.Account;
import com.springapp.domain.http.account.AccountLink;
import com.springapp.domain.http.account.AccountMetadata;
import com.springapp.domain.http.account.Contact;
import com.springapp.domain.http.subscription.Subscription;
import com.springapp.domain.http.subscription.Subscriptions;
import com.springapp.domain.http.transferconfiguration.TransferConfiguration;
import com.springapp.domain.http.transferconfiguration.TransferConfigurations;
import com.springapp.domain.http.user.PasswordCredentials;
import com.springapp.domain.http.user.UserAccount;
import com.springapp.domain.http.user.UserLink;
import com.springapp.domain.http.user.UserMetadata;
import org.apache.http.HttpStatus;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.client.proxy.Times;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 01/10/2014
 * Time: 22:29
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConfigTest.class})
public class MopHttpClientImplTest {

    @Autowired
    MopHttpClientImpl mopHttpClient;

    private static ClientAndServer server;

    @BeforeClass
    public static void startProxy() {
        server = ClientAndServer.startClientAndServer(9111,9999);

    }

    @AfterClass
    public static void stopProxy() {
        server.stop();
    }

    @Before
    public void setUp() throws Exception {
        server.reset();
    }

    @Test
    public void testAdd() throws Exception {
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

        String xmlExpected= "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><account><name>uMop01</name><uid>7006</uid><gid>540</gid><contact><email>uMop01@mopstsrgs.it</email></contact>" +
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


        HttpRequest request = HttpRequest.request().withMethod("PUT").withPath("/api/v1.0/accounts/uMop01").withBody(xmlExpected);
//        mopHttpClient.setUseSecureConnection(false);


        server.when(request).respond(new HttpResponse().withStatusCode(HttpStatus.SC_CREATED).withBody("ok"));

        boolean res = mopHttpClient.createMopAccount(account);
        assertTrue(res);
        server.verify(HttpRequest.request().withPath("/api/v1.0/accounts/uMop01").withBody(xmlExpected), Times.exactly(1));
    }


    @Test
    public void getSubscriptionIdForTheUser() throws Exception {
        HttpRequest request = HttpRequest.request().withMethod("GET").withPath("/api/v1.0/subscriptions").withQueryStringParameter(
                new Parameter("limit","1")).withQueryStringParameter(new Parameter("application","RoutingToMOP"))
                .withQueryStringParameter(new Parameter("account","uMop01")
        );
//        mopHttpClient.setUseSecureConnection(false);
        server.when(request).respond(new HttpResponse().withStatusCode(HttpStatus.SC_OK).withBody("{\"subscriptions\":[{" +
                " \"application\":\"RoutingToMOP\"," +
                " \"subscriberID\":\"uMop01\",\"folder\":\"/Outbox\",\"pta.on.success.OUT.do.delete\":\"true\"," +
                " \"account\":\"uMop01\"," +
                " \"id\":\"8aa6c33c477cf5b00148ad4c770801d2\"," +
                "\"metadata\":{" +
                "} }] }"));

        String id = mopHttpClient.getSubscriptionIdForTheUser("RoutingToMOP","uMop01");
        assertEquals("8aa6c33c477cf5b00148ad4c770801d2",id);
    }

    @Test
    public void createSubscriptionForTheUser()throws Exception {
        Subscriptions subscriptions = new Subscriptions();
        Subscription subscription = new Subscription();
        subscriptions.setSubscription(subscription);
        subscription.setAccount("uMop01");
        subscription.setApplication("RoutingToMOP");
        subscription.setSubscriberID("uMop01");
        subscription.setFolder("/Outbox");

        String xmlTomatch = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><subscriptions>" +
                "<subscription><account>uMop01</account>" +
                "<application>RoutingToMOP</application>" +
                "<subscriberID>uMop01</subscriberID>" +
                "<folder>/Outbox</folder>" +
                "<pta.on.success.OUT.do.delete>true</pta.on.success.OUT.do.delete>" +
                "</subscription>" +
                "</subscriptions>";

        HttpRequest request = HttpRequest.request().withMethod("POST").withPath("/api/v1.0/subscriptions").withBody(xmlTomatch);
//        mopHttpClient.setUseSecureConnection(false);


        server.when(request).respond(new HttpResponse().withStatusCode(HttpStatus.SC_CREATED).withBody("ok"));

        boolean res = mopHttpClient.createSubscriptionForTheUser(subscriptions);
        assertTrue(res);
        server.verify(HttpRequest.request().withPath("/api/v1.0/subscriptions").withBody(xmlTomatch), Times.exactly(1));

    }

    @Test
    public void createTransferConfigurationForTheUser() throws Exception  {
        TransferConfigurations transferConfigurations = new TransferConfigurations();
        transferConfigurations.setTransferConfiguration(new TransferConfiguration());

        String xmlToMatch = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><transferConfigurations><transferConfiguration>" +
                "<tag>PARTNER-IN</tag>" +
                "<direction>0</direction>" +
                "<site>TS_FTP_RoutingMOP</site>" +
                "</transferConfiguration>" +
                "</transferConfigurations>";

        HttpRequest request = HttpRequest.request().withMethod("POST").withPath("/api/v1.1/subscriptions/8aa6c33c477cf5b00148ad4c770801d2/transferConfigurations").withBody(xmlToMatch);
//        mopHttpClient.setUseSecureConnection(false);


        server.when(request).respond(new HttpResponse().withStatusCode(HttpStatus.SC_CREATED).withBody("ok"));

        boolean res = mopHttpClient.createTransferConfigurationForTheUser(transferConfigurations,"8aa6c33c477cf5b00148ad4c770801d2");
        assertTrue(res);
        server.verify(HttpRequest.request().withPath("/api/v1.1/subscriptions/8aa6c33c477cf5b00148ad4c770801d2/transferConfigurations").withBody(xmlToMatch), Times.exactly(1));
    }

    @Test
    public void testAddUserToAccount() throws Exception {
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


        String xmlTomatch = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><user>" +
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

        HttpRequest request = HttpRequest.request().withMethod("PUT").withPath("/api/v1.0/accounts/uMop01/users/uMop01").withBody(xmlTomatch);
//        mopHttpClient.setUseSecureConnection(false);


        server.when(request).respond(new HttpResponse().withStatusCode(HttpStatus.SC_CREATED).withBody("ok"));

        boolean res = mopHttpClient.addUserToMopAccount(userAccount);
        assertTrue(res);
        server.verify(HttpRequest.request().withPath("/api/v1.0/accounts/uMop01/users/uMop01").withBody(xmlTomatch), Times.exactly(1));
    }
}
