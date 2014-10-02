package com.springapp.domain.http.subscription;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 02/10/2014
 * Time: 09:03
 * To change this template use File | Settings | File Templates.
 */
public class SubscriptionMetadata {

    private SubscriptionLink links;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscriptionMetadata that = (SubscriptionMetadata) o;

        if (links != null ? !links.equals(that.links) : that.links != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return links != null ? links.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "SubscriptionMetadata{" +
                "links=" + links +
                '}';
    }

    public SubscriptionLink getLinks() {
        return links;
    }

    public void setLinks(SubscriptionLink links) {
        this.links = links;
    }
}
