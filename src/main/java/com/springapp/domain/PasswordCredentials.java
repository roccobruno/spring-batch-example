package com.springapp.domain;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 01/10/2014
 * Time: 23:26
 * To change this template use File | Settings | File Templates.
 */
public class PasswordCredentials {

    private String username;
    private String password;
    private boolean forcePasswordChange = true;

    public PasswordCredentials() {

    }

    @Override
    public String toString() {
        return "PasswordCredentials{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", forcePasswordChange='" + forcePasswordChange + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getForcePasswordChange() {
        return forcePasswordChange;
    }

    public void setForcePasswordChange(boolean forcePasswordChange) {
        this.forcePasswordChange = forcePasswordChange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PasswordCredentials that = (PasswordCredentials) o;

        if (forcePasswordChange != that.forcePasswordChange) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (forcePasswordChange ? 1 : 0);
        return result;
    }
}
