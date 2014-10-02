package com.springapp.domain.http.subscription;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 02/10/2014
 * Time: 09:03
 * To change this template use File | Settings | File Templates.
 */
/*
<account>https://vm-sts-coll01:444/api/v1.0/accounts/uMop01</account>
  <application>https://vm-sts-coll01:444/api/v1.0/applications/RoutingToMOP</application>
   <transferConfigurations>https://vm-sts-coll01:444/api/v1.0/subscriptions/8aa6c33c477cf5b00148ad4c770801d2/transferConfigurations</transferConfigurations>
    <schedules>https://vm-sts-coll01:444/api/v1.0/subscriptions/8aa6c33c477cf5b00148ad4c770801d2/schedules</schedules>
 */
public class SubscriptionLink {

    public SubscriptionLink(){}

   private String account;
    private String application;
    private String transferConfigurations;
    private String schedules;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscriptionLink that = (SubscriptionLink) o;

        if (account != null ? !account.equals(that.account) : that.account != null) return false;
        if (application != null ? !application.equals(that.application) : that.application != null) return false;
        if (schedules != null ? !schedules.equals(that.schedules) : that.schedules != null) return false;
        if (transferConfigurations != null ? !transferConfigurations.equals(that.transferConfigurations) : that.transferConfigurations != null)
            return false;

        return true;
    }

    public String getSubId() {
        Matcher m = Pattern.compile(
                Pattern.quote("subscriptions/")
                        + "(.*?)"
                        + Pattern.quote("/schedules")
        ).matcher(this.schedules);
        if(m.find())
            return m.group(1);
        else return "";

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

    public String getTransferConfigurations() {
        return transferConfigurations;
    }

    public void setTransferConfigurations(String transferConfigurations) {
        this.transferConfigurations = transferConfigurations;
    }

    public String getSchedules() {
        return schedules;
    }

    public void setSchedules(String schedules) {
        this.schedules = schedules;
    }

    @Override
    public String toString() {
        return "SubscriptionLink{" +
                "account='" + account + '\'' +
                ", application='" + application + '\'' +
                ", transferConfigurations='" + transferConfigurations + '\'' +
                ", schedules='" + schedules + '\'' +
                '}';
    }
}
