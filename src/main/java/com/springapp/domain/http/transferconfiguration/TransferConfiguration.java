package com.springapp.domain.http.transferconfiguration;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 02/10/2014
 * Time: 10:48
 * To change this template use File | Settings | File Templates.
 */
/*
<transferConfiguration>
<tag>PARTNER-IN</tag>
<direction>0</direction>
<site>TS_FTP_RoutingMOP</site>
</transferConfiguration>
</transferConfigurations>"
 */
public class TransferConfiguration {

    public TransferConfiguration(){}
    private String tag = "PARTNER-IN";
    private int direction  = 0;
    private String site = "TS_FTP_RoutingMOP";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransferConfiguration that = (TransferConfiguration) o;

        if (direction != that.direction) return false;
        if (site != null ? !site.equals(that.site) : that.site != null) return false;
        if (tag != null ? !tag.equals(that.tag) : that.tag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tag != null ? tag.hashCode() : 0;
        result = 31 * result + direction;
        result = 31 * result + (site != null ? site.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TransferConfiguration{" +
                "tag='" + tag + '\'' +
                ", direction=" + direction +
                ", site='" + site + '\'' +
                '}';
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
