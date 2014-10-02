package com.springapp.domain.http.user;

import com.springapp.domain.ILink;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 01/10/2014
 * Time: 23:34
 * To change this template use File | Settings | File Templates.
 */
public class UserLink implements ILink {

    private String account;
    private String user;

    public UserLink(){}

    public UserLink(String host,int port, String username) {
         this.account =  getBaseUrl(host,port).append("accounts/")
                 .append(username).toString();

        this.user = getBaseUrl(host,port).append("accounts/").append(username)
                .append("/users/").append(username).toString();
    }

    private StringBuilder getBaseUrl(String host,int port) {
        StringBuilder builder = new StringBuilder("https://");
        builder.append(host).append(":").append(port).append("/api/v1.0/");
        return builder;
    }


    @Override
    public String toString() {
        return "UserLink{" +
                "account='" + account + '\'' +
                ", user='" + user + '\'' +
                '}';
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLink userLink = (UserLink) o;

        if (account != null ? !account.equals(userLink.account) : userLink.account != null) return false;
        if (user != null ? !user.equals(userLink.user) : userLink.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = account != null ? account.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
