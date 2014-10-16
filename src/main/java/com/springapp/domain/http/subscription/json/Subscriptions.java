package com.springapp.domain.http.subscription.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.springapp.domain.IToXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 02/10/2014
 * Time: 08:54
 * To change this template use File | Settings | File Templates.
 */

public class Subscriptions {
    static Logger log = LoggerFactory.getLogger(Subscriptions.class);

    public Subscriptions(){}

    private List<Subscription> subscriptions;



    public static Subscriptions fromJson(String json) {
        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            jsonMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            Subscriptions value = jsonMapper.readValue(json, Subscriptions.class);
            return value;
        } catch (IOException e) {
            log.error("Error in creating Account object from xmn :{}, error:{}", jsonMapper,e);
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscriptions that = (Subscriptions) o;

        if (subscriptions != null ? !subscriptions.equals(that.subscriptions) : that.subscriptions != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return subscriptions != null ? subscriptions.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Subscriptions{" +
                "subscriptions=" + subscriptions +
                '}';
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
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



}
