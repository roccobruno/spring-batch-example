package com.springapp.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 01/10/2014
 * Time: 19:24
 * To change this template use File | Settings | File Templates.
 */
/*
<account>
            <name>uMop01</name>
            <uid>7006</uid>
            <gid>540</gid>
            <contact>
                        <email>uMop01@mopstsrgs.it</email>
            </contact>
            <disabled>false</disabled>
            <businessUnit>MOP</businessUnit>
            <notes>Psw_uMop01</notes>
            <type>user</type>
            <licensed>true</licensed>
            <deliveryMethod>Disabled</deliveryMethod>
            <routingMode>reject</routingMode>
            <transferType>E</transferType>
            <metadata>
                        <links>
                                   <users>https://vm-sts-coll01:444/api/v1.0/accounts/uMop01/users</users>
                                   <sites>https://vm-sts-coll01:444/api/v1.0/sites?account=uMop01</sites>
                                   <subscriptions>https://vm-sts-coll01:444/api/v1.0/subscriptions?account=uMop01</subscriptions>
                                   <transferProfiles>https:///vm-sts-coll01:444/api/v1.0/transferProfiles?account=uMop01</transferProfiles>
                        </links>
</metadata>
</account>
 */

@JacksonXmlRootElement(localName = "account")
public class Account implements IToXML{
    static Logger log = LoggerFactory.getLogger(Account.class);

    private String name;
    private String uid;
    private int gid = 540;
    private Contact contact;
    private boolean disabled;
    private String businessUnit = "MOP";
    private String notes;
    private String type = "user";
    private boolean licensed = true;
    private String deliveryMethod = "Disabled";
    private String routingMode = "reject";
    private String transferType = "E";
    @JacksonXmlProperty(localName = "metadata")
    private AccountMetadata metadata;

    public Account(){}


    public static Account fromXML(String xml) {
        ObjectMapper xmlMapper = new XmlMapper();
        try {
            Account value = xmlMapper.readValue(xml, Account.class);
            return value;
        } catch (IOException e) {

            log.error("Error in creating Account object from xml :{} , error : {}", xml,e);
        }
        return null;
    }

    public String toXML() {
        ObjectMapper xmlMapper = new XmlMapper();
        try {
            String xml = xmlMapper.writeValueAsString(this);
            return xml;
        } catch (JsonProcessingException e) {
           log.error("Error in creating xml from Account object:{}",this);
        }
        return "";
    }


    public Account(String id) {
        this.name = id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", uid='" + uid + '\'' +
                ", gid=" + gid +
                ", contact=" + contact +
                ", disabled=" + disabled +
                ", businessUnit='" + businessUnit + '\'' +
                ", notes='" + notes + '\'' +
                ", type='" + type + '\'' +
                ", licensed=" + licensed +
                ", routingMode='" + routingMode + '\'' +
                ", transferType='" + transferType + '\'' +
                ", deliveryMethod='" + deliveryMethod + '\'' +
                ", metadata=" + metadata +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isLicensed() {
        return licensed;
    }

    public void setLicensed(boolean licensed) {
        this.licensed = licensed;
    }

    public String getRoutingMode() {
        return routingMode;
    }

    public void setRoutingMode(String routingMode) {
        this.routingMode = routingMode;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public AccountMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(AccountMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (disabled != account.disabled) return false;
        if (gid != account.gid) return false;
        if (licensed != account.licensed) return false;
        if (businessUnit != null ? !businessUnit.equals(account.businessUnit) : account.businessUnit != null)
            return false;
        if (contact != null ? !contact.equals(account.contact) : account.contact != null) return false;
        if (deliveryMethod != null ? !deliveryMethod.equals(account.deliveryMethod) : account.deliveryMethod != null)
            return false;
        if (metadata != null ? !metadata.equals(account.metadata) : account.metadata != null) return false;
        if (name != null ? !name.equals(account.name) : account.name != null) return false;
        if (notes != null ? !notes.equals(account.notes) : account.notes != null) return false;
        if (routingMode != null ? !routingMode.equals(account.routingMode) : account.routingMode != null) return false;
        if (transferType != null ? !transferType.equals(account.transferType) : account.transferType != null)
            return false;
        if (type != null ? !type.equals(account.type) : account.type != null) return false;
        if (uid != null ? !uid.equals(account.uid) : account.uid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + gid;
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        result = 31 * result + (disabled ? 1 : 0);
        result = 31 * result + (businessUnit != null ? businessUnit.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (licensed ? 1 : 0);
        result = 31 * result + (deliveryMethod != null ? deliveryMethod.hashCode() : 0);
        result = 31 * result + (routingMode != null ? routingMode.hashCode() : 0);
        result = 31 * result + (transferType != null ? transferType.hashCode() : 0);
        result = 31 * result + (metadata != null ? metadata.hashCode() : 0);
        return result;
    }
}
