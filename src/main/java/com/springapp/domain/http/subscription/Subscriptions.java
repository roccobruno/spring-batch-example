package com.springapp.domain.http.subscription;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.springapp.domain.IToXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 02/10/2014
 * Time: 08:54
 * To change this template use File | Settings | File Templates.
 */
@JacksonXmlRootElement(localName = "subscriptions")
public class Subscriptions implements IToXML{
    static Logger log = LoggerFactory.getLogger(Subscriptions.class);

    public Subscriptions(){}

    private Subscription subscription;

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public String toString() {
        return "Subscriptions{" +
                "subscription=" + subscription +
                '}';
    }

    public static Subscriptions fromXML(String xml) {
        ObjectMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            Subscriptions value = xmlMapper.readValue(xml, Subscriptions.class);
            return value;
        } catch (IOException e) {
            log.error("Error in creating Account object from xmn :{}, error:{}", xml,e);
        }
        return null;
    }

    public String toXML() {
        ObjectMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            String xml = xmlMapper.writeValueAsString(this);
            return xml;
        } catch (JsonProcessingException e) {
            log.error("Error in creating xml from Account object:{}",this);
        }
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscriptions that = (Subscriptions) o;

        if (subscription != null ? !subscription.equals(that.subscription) : that.subscription != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return subscription != null ? subscription.hashCode() : 0;
    }
}
