package com.springapp.service.http;


import com.springapp.domain.Account;
import com.springapp.domain.IToXML;
import com.springapp.domain.UserAccount;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Component
public class MopHttpClientImpl implements MopHttpClient {

    private Logger logger = LoggerFactory.getLogger(MopHttpClientImpl.class);

    private CloseableHttpClient httpClient;
    private final int CONNECTION_TIMEOUT;
    private String host;//connection host
    private int port;//connection port
    private String username;//connection username
    private String password;//connection password

    private boolean useSecureConnection = true;





    @Autowired
    public MopHttpClientImpl(@Value("${mop.connection.pool.max}") int connectionPoolMax,
                             @Value("${mop.connection.pool.maxPerRoute}") int connectionPoolMaxPerRoute,
                             @Value("${mop.host}")String host,@Value("${mop.port}") int port,
                             @Value("${mop.username}")String username,
                             @Value("${mop.password}")String password,
                             HttpClientFactory httpClientFactory,
                             @Value("${mop.connection.timeout}") int connection_timeout) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.CONNECTION_TIMEOUT = connection_timeout;
        this.httpClient = httpClientFactory.createPooledConnectionHttpClient(connectionPoolMax, connectionPoolMaxPerRoute);

    }

    @Override
    public boolean createMopAccount(Account account) {
        String content = getPostBody(account);
        String url = getCompleteUrlToCreateAccount(host, port, username, password, account.getName());

       boolean docSent = doPut(url, content);


        return docSent;
    }

    @Override
    public boolean addUserToMopAccount(UserAccount userAccount) {
        String content = getPostBody(userAccount);
        String url = getCompleteUrlToAddUserToAccount(host, port, username, password, userAccount.getName());
        boolean docSent = doPut(url, content);
        return docSent;
    }


    private String getCompleteUrlToAddUserToAccount(String host, int port, String username, String password, String accountUsername) {
        StringBuilder builder = new StringBuilder(getBaseUrl(host,port,username,password));
         builder.append(accountUsername).append("/users/").append(accountUsername);
        return builder.toString();
    }

    private String getCompleteUrlToCreateAccount(String host, int port, String username, String password, String accountUsername) {
        // https://mopda:mopdacol14@vm-sts-coll01:444/api/v1.0/accounts/uMop01

        StringBuilder builder = new StringBuilder(getBaseUrl(host,port,username,password));
        builder .append(accountUsername);
        return builder.toString();
    }

    private String getBaseUrl(String host,int port,String username,String password) {
        String typeConnection = useSecureConnection ? "https://" : "http://";

        StringBuilder builder = new StringBuilder(typeConnection);
        if(useSecureConnection) {
            builder.append(username)
                    .append(":").append(password).append("@");
        }
        builder.append(host).append(":").append(port).append("/api/v1.0/accounts/");
       return builder.toString();
    }


    private String getPostBody(IToXML account) {
        StringBuilder builder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append(account.toXML());

        return builder.toString();
    }

    private boolean doPut(String url, String content) {
        try {
            HttpPut httpPost = new HttpPut(url);
            httpPost.setHeader("Content-Type","application/xml");

            httpPost.setEntity(new StringEntity(content));
            httpPost.setConfig( RequestConfig.custom().setConnectTimeout(CONNECTION_TIMEOUT).setSocketTimeout(CONNECTION_TIMEOUT).build());
            logger.info("PostSent {}", url);
            HttpActionResponse response = submitHttpRequest(httpPost);

            int statusCode = response.getStatusCode();
            if (statusCode != HttpStatus.SC_CREATED && statusCode != HttpStatus.SC_OK) {
                logger.warn("Operation Failed {} Status {} Reason {} Content {}", url, statusCode, response.getReason(), content);
                return false;
            }

            return true;

        } catch (UnsupportedEncodingException e) {
            logger.error("Cause {} Content {}", e.getMessage(), content);

        } catch (IOException e) {
            logger.error("Unexpected IO Error", e);
        }

        return false;
    }



    private HttpActionResponse submitHttpRequest(HttpUriRequest httpUriRequest) throws IOException {
        CloseableHttpResponse response = httpClient.execute(httpUriRequest);
        int statusCode = response.getStatusLine().getStatusCode();
        String reason = response.getStatusLine().getReasonPhrase();
        String body = getResponseBody(response);
        response.close();
        return new HttpActionResponse(statusCode, reason, body);
    }

    private String getResponseBody(CloseableHttpResponse response) throws IOException {
        String body = "";
        if (response.getEntity() != null) {
            body = EntityUtils.toString(response.getEntity());
        }

        return body;
    }

    public String getHost() {
        return host;
    }



    public int getPort() {
        return port;
    }




    public void setUseSecureConnection(boolean useSecureConnection) {
        this.useSecureConnection = useSecureConnection;
    }
}

class HttpActionResponse {

    private final int statusCode;
    private final String reason;
    private final String body;

    HttpActionResponse(int statusCode, String reason, String body) {
        this.statusCode = statusCode;
        this.reason = reason;
        this.body = body;
    }

    int getStatusCode() {
        return statusCode;
    }

    String getReason() {
        return reason;
    }

    public String getBody() {
        return body;
    }
}
