package com.springapp.domain.http.transfer;

/**
 * Created by roccobruno on 18/05/2015.
 */
public class TransferEntry {

    private String status;
    private boolean secure;
    private String account;
    private String login;
    private String direction;

    public TransferEntry(){}

    @Override
    public String toString() {
        return "{" +
                "status=" + status +
                ", secure=" + secure +
                ", account=" + account +
                ", direction=" + direction +
                ", login=" + login +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransferEntry that = (TransferEntry) o;

        if (secure != that.secure) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (account != null ? !account.equals(that.account) : that.account != null) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        return !(direction != null ? !direction.equals(that.direction) : that.direction != null);

    }

    @Override
    public int hashCode() {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + (secure ? 1 : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSecure() {
        return secure;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
