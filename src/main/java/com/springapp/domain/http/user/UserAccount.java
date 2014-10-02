package com.springapp.domain.http.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.springapp.domain.IToXML;
import com.springapp.domain.http.user.PasswordCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: r.bruno@london.net-a-porter.com
 * Date: 01/10/2014
 * Time: 23:24
 * To change this template use File | Settings | File Templates.
 */

/**
 * <user>
 <name>uMop01</name>
 <authExternal>false</authExternal>
 <locked>false</locked>
 <failedAuthAttempts>0</failedAuthAttempts>
 <passwordCredentials><username>uMop01</username>
 <password>Psw_uMop01</password>
 <forcePasswordChange>true</forcePasswordChange>
 </passwordCredentials>
 <metadata>
 <links>
 <account>https://vm-sts-coll01:444/api/v1.0/accounts/uMop01</account>
 <user>https://vm-sts-coll01:444/api/v1.0/accounts/uMop01/users/uMop01</user>
 </links>
 </metadata>
 </user>"
 */
@JacksonXmlRootElement(localName = "user")
public class UserAccount implements IToXML {
    static Logger log = LoggerFactory.getLogger(UserAccount.class);

    private String name;
    private boolean authExternal;
    private boolean locked;
    private int failedAuthAttempts;
    private PasswordCredentials passwordCredentials;
    private UserMetadata metadata;

    @Override
    public String
    toString() {
        return "UserAccount{" +
                "name='" + name + '\'' +
                ", authExternal='" + authExternal + '\'' +
                ", locked='" + locked + '\'' +
                ", failedAuthAttempts=" + failedAuthAttempts +
                ", passwordCredentials=" + passwordCredentials +
                ", metadata=" + metadata +
                '}';
    }

    public static UserAccount fromXML(String xml) {
        ObjectMapper xmlMapper = new XmlMapper();
        try {
            UserAccount value = xmlMapper.readValue(xml, UserAccount.class);
            return value;
        } catch (IOException e) {
            log.error("Error in creating Account object from xmn :{}, error:{}", xml,e);
        }
        return null;
    }

    public String toXML() {
        ObjectMapper xmlMapper = new XmlMapper();
        try {
            String xml = xmlMapper.writeValueAsString(this);
            return xml;
        } catch (JsonProcessingException e) {
            log.error("Error in creating xml from Account object:{}",this);
        }
        return "";
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getAuthExternal() {
        return authExternal;
    }

    public void setAuthExternal(boolean authExternal) {
        this.authExternal = authExternal;
    }

    public boolean getLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int getFailedAuthAttempts() {
        return failedAuthAttempts;
    }

    public void setFailedAuthAttempts(int failedAuthAttempts) {
        this.failedAuthAttempts = failedAuthAttempts;
    }

    public PasswordCredentials getPasswordCredentials() {
        return passwordCredentials;
    }

    public void setPasswordCredentials(PasswordCredentials passwordCredentials) {
        this.passwordCredentials = passwordCredentials;
    }

    public UserMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(UserMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAccount that = (UserAccount) o;

        if (authExternal != that.authExternal) return false;
        if (failedAuthAttempts != that.failedAuthAttempts) return false;
        if (locked != that.locked) return false;
        if (metadata != null ? !metadata.equals(that.metadata) : that.metadata != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (passwordCredentials != null ? !passwordCredentials.equals(that.passwordCredentials) : that.passwordCredentials != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (authExternal ? 1 : 0);
        result = 31 * result + (locked ? 1 : 0);
        result = 31 * result + failedAuthAttempts;
        result = 31 * result + (passwordCredentials != null ? passwordCredentials.hashCode() : 0);
        result = 31 * result + (metadata != null ? metadata.hashCode() : 0);
        return result;
    }
}
