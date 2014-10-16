package com.springapp.domain.http.subscription.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 02/10/2014
 * Time: 08:56
 * To change this template use File | Settings | File Templates.
 */

public class Subscription {

    private String id;
    private String account;
    private String application;
    private String subscriberID;
    private String folder = "/Outbox";
    @JsonProperty(value = "pta.on.success.OUT.do.delete")
    private boolean ptaOnSuccessOutDoDelete = true;

    private SubscriptionMetadata metadata;

    public Subscription(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscription that = (Subscription) o;

        if (ptaOnSuccessOutDoDelete != that.ptaOnSuccessOutDoDelete) return false;
        if (account != null ? !account.equals(that.account) : that.account != null) return false;
        if (application != null ? !application.equals(that.application) : that.application != null) return false;
        if (folder != null ? !folder.equals(that.folder) : that.folder != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (metadata != null ? !metadata.equals(that.metadata) : that.metadata != null) return false;
        if (subscriberID != null ? !subscriberID.equals(that.subscriberID) : that.subscriberID != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (application != null ? application.hashCode() : 0);
        result = 31 * result + (subscriberID != null ? subscriberID.hashCode() : 0);
        result = 31 * result + (folder != null ? folder.hashCode() : 0);
        result = 31 * result + (ptaOnSuccessOutDoDelete ? 1 : 0);
        result = 31 * result + (metadata != null ? metadata.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", application='" + application + '\'' +
                ", subscriberID='" + subscriberID + '\'' +
                ", folder='" + folder + '\'' +
                ", ptaOnSuccessOutDoDelete=" + ptaOnSuccessOutDoDelete +
                ", metadata=" + metadata +
                '}';
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getSubscriberID() {
        return subscriberID;
    }

    public void setSubscriberID(String subscriberID) {
        this.subscriberID = subscriberID;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public boolean isPtaOnSuccessOutDoDelete() {
        return ptaOnSuccessOutDoDelete;
    }

    public void setPtaOnSuccessOutDoDelete(boolean ptaOnSuccessOutDoDelete) {
        this.ptaOnSuccessOutDoDelete = ptaOnSuccessOutDoDelete;
    }

    public SubscriptionMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(SubscriptionMetadata metadata) {
        this.metadata = metadata;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
