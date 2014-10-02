package com.springapp.domain.http.account;

import com.springapp.domain.ILink;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 01/10/2014
 * Time: 21:26
 * To change this template use File | Settings | File Templates.
 */
public class AccountLink implements ILink {

    /*
      <users>https://vm-sts-coll01:444/api/v1.0/accounts/uMop01/users</users>
                                   <sites>https://vm-sts-coll01:444/api/v1.0/sites?account=uMop01</sites>
                                   <subscriptions>https://vm-sts-coll01:444/api/v1.0/subscriptions?account=uMop01</subscriptions>
                                   <transferProfiles>https:///vm-sts-coll01:444/api/v1.0/transferProfiles?account=uMop01</transferProfiles>

     */

    public AccountLink(){}

    public AccountLink(String host, int port, String username){
       this.users = getBaseUrl(host,port).append("accounts/").append(username).append("/users").toString();
       this.sites = getBaseUrl(host,port).append("sites?account=").append(username).toString();
       this.subscriptions = getBaseUrl(host,port).append("subscriptions?account=").append(username).toString();
       this.transferProfiles = getBaseUrl(host,port).append("transferProfiles?account=").append(username).toString();
    }

    private StringBuilder getBaseUrl(String host,int port) {
        StringBuilder builder = new StringBuilder("https://");
        builder.append(host).append(":").append(port).append("/api/v1.0/");
        return builder;
    }


    private String users;
    private String sites;
    private String subscriptions;
    private String transferProfiles;

    @Override
    public String toString() {
        return "AccountLink{" +
                "users='" + users + '\'' +
                ", sites='" + sites + '\'' +
                ", subscriptions='" + subscriptions + '\'' +
                ", transferProfiles='" + transferProfiles + '\'' +
                '}';
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getSites() {
        return sites;
    }

    public void setSites(String sites) {
        this.sites = sites;
    }

    public String getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(String subscriptions) {
        this.subscriptions = subscriptions;
    }

    public String getTransferProfiles() {
        return transferProfiles;
    }

    public void setTransferProfiles(String transferProfiles) {
        this.transferProfiles = transferProfiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountLink that = (AccountLink) o;

        if (sites != null ? !sites.equals(that.sites) : that.sites != null) return false;
        if (subscriptions != null ? !subscriptions.equals(that.subscriptions) : that.subscriptions != null)
            return false;
        if (transferProfiles != null ? !transferProfiles.equals(that.transferProfiles) : that.transferProfiles != null)
            return false;
        if (users != null ? !users.equals(that.users) : that.users != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = users != null ? users.hashCode() : 0;
        result = 31 * result + (sites != null ? sites.hashCode() : 0);
        result = 31 * result + (subscriptions != null ? subscriptions.hashCode() : 0);
        result = 31 * result + (transferProfiles != null ? transferProfiles.hashCode() : 0);
        return result;
    }
}
