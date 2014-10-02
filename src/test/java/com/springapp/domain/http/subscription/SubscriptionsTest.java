package com.springapp.domain.http.subscription;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 02/10/2014
 * Time: 09:09
 * To change this template use File | Settings | File Templates.
 */
public class SubscriptionsTest {

    @Test
    public void testGetSubId() {
       Subscriptions mocked = getMockedSub();
       Assert.assertEquals("8aa6c33c477cf5b00148ad4c770801d2",mocked.getSubscription().getMetadata().getLinks().getSubId());
    }


    private Subscriptions getMockedSub() {
        Subscriptions subscriptions = new Subscriptions();
        Subscription subscription = new Subscription();
        subscriptions.setSubscription(subscription);
        subscription.setAccount("uMop01");
        subscription.setApplication("RoutingToMOP");
        subscription.setSubscriberID("uMop01");
        subscription.setFolder("/Outbox");
        subscription.setId("8aa6c33c477cf5b00148ad4c770801d2");

        SubscriptionMetadata metadata = new SubscriptionMetadata();
        subscription.setMetadata(metadata);
        SubscriptionLink link = new SubscriptionLink();
        metadata.setLinks(link);
        link.setAccount("https://vm-sts-coll01:444/api/v1.0/accounts/uMop01");
        link.setTransferConfigurations("https://vm-sts-coll01:444/api/v1.0/subscriptions/8aa6c33c477cf5b00148ad4c770801d2/transferConfigurations");
        link.setApplication("https://vm-sts-coll01:444/api/v1.0/applications/RoutingToMOP");
        link.setSchedules("https://vm-sts-coll01:444/api/v1.0/subscriptions/8aa6c33c477cf5b00148ad4c770801d2/schedules");


        return subscriptions;
    }


    @Test
    public void testFromXML() throws Exception {

        String xmlTomatch = "<subscriptions>" +
                "<subscription>" +
                "<id>8aa6c33c477cf5b00148ad4c770801d2</id><account>uMop01</account>" +
                "<application>RoutingToMOP</application>" +
                "<subscriberID>uMop01</subscriberID>" +
                "<folder>/Outbox</folder>" +
                "<pta.on.success.OUT.do.delete>true</pta.on.success.OUT.do.delete>" +
                "<metadata>" +
                "<links>" +
                "<account>https://vm-sts-coll01:444/api/v1.0/accounts/uMop01</account>" +
                "<application>https://vm-sts-coll01:444/api/v1.0/applications/RoutingToMOP</application>" +
                "<transferConfigurations>https://vm-sts-coll01:444/api/v1.0/subscriptions/8aa6c33c477cf5b00148ad4c770801d2/transferConfigurations</transferConfigurations>" +
                "<schedules>https://vm-sts-coll01:444/api/v1.0/subscriptions/8aa6c33c477cf5b00148ad4c770801d2/schedules</schedules>" +
                "</links>" +
                "</metadata>" +
                "</subscription>" +
                "</subscriptions>";


        Assert.assertEquals(Subscriptions.fromXML(xmlTomatch),getMockedSub());
    }

    @Test
    public void testToXML() throws Exception {
       Subscriptions subscriptions = new Subscriptions();
        Subscription subscription = new Subscription();
        subscriptions.setSubscription(subscription);
        subscription.setAccount("uMop01");
        subscription.setApplication("RoutingToMOP");
        subscription.setSubscriberID("uMop01");
        subscription.setFolder("/Outbox");

                String xmlTomatch = "<subscriptions>" +
                        "<subscription><account>uMop01</account>" +
                        "<application>RoutingToMOP</application>" +
                        "<subscriberID>uMop01</subscriberID>" +
                        "<folder>/Outbox</folder>" +
                        "<pta.on.success.OUT.do.delete>true</pta.on.success.OUT.do.delete>" +
                        "</subscription>" +
                        "</subscriptions>";


        Assert.assertEquals(xmlTomatch,subscriptions.toXML());
    }
}
