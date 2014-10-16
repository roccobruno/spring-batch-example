package com.springapp.domain.http.subscription.json;

import junit.framework.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class SubscriptionsTest {



    @Test
    public void testFromJson() throws Exception {

        String json = "{\"subscriptions\":[{" +
                " \"application\":\"RoutingToMOP\"," +
                " \"subscriberID\":\"uMop01\",\"folder\":\"/Outbox\",\"pta.on.success.OUT.do.delete\":\"true\"," +
                " \"account\":\"uMop01\"," +
                " \"id\":\"id\"," +
                "\"metadata\":{" +
                "} }] }";


        Subscriptions subscriptions = new Subscriptions();
        Subscription subscription = new Subscription();
        subscription.setAccount("uMop01");
        subscription.setApplication("RoutingToMOP");
        subscription.setSubscriberID("uMop01");
        subscription.setFolder("/Outbox");
        subscription.setPtaOnSuccessOutDoDelete(true);
        subscription.setMetadata(new SubscriptionMetadata());
        subscription.setId("id");
        ArrayList<Subscription> l = new ArrayList<Subscription>();
        l.add(subscription);
        subscriptions.setSubscriptions(l);
        Assert.assertEquals(subscriptions,Subscriptions.fromJson(json));







    }
}