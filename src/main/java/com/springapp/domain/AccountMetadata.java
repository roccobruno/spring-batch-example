package com.springapp.domain;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 01/10/2014
 * Time: 23:45
 * To change this template use File | Settings | File Templates.
 */
public class AccountMetadata {
    @Override
    public String toString() {
        return "AccountMetadata{" +
                "links=" + links +
                '}';
    }

    public AccountLink getLinks() {
        return links;
    }

    public void setLinks(AccountLink links) {
        this.links = links;
    }

    private AccountLink links;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountMetadata that = (AccountMetadata) o;

        if (links != null ? !links.equals(that.links) : that.links != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return links != null ? links.hashCode() : 0;
    }
}
