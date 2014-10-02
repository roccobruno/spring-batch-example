package com.springapp.domain.http.user;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 01/10/2014
 * Time: 21:26
 * To change this template use File | Settings | File Templates.
 */
public class UserMetadata {

    @Override
    public String toString() {
        return "UserMetadata{" +
                "links=" + links +
                '}';
    }

    public UserLink getLinks() {
        return links;
    }

    public void setLinks(UserLink links) {
        this.links = links;
    }

    private UserLink links;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserMetadata that = (UserMetadata) o;

        if (links != null ? !links.equals(that.links) : that.links != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return links != null ? links.hashCode() : 0;
    }
}
