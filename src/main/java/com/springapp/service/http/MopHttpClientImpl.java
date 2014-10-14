package com.springapp.service.http;


import com.springapp.domain.http.account.Account;
import com.springapp.domain.IToXML;
import com.springapp.domain.http.subscription.Subscriptions;
import com.springapp.domain.http.transferconfiguration.TransferConfigurations;
import com.springapp.domain.http.user.UserAccount;
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
import org.springframework.util.StringUtils;

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
                             @Value("${mop.connection.timeout}") int connection_timeout,@Value("${mop.useHttps}") boolean useHttps) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.CONNECTION_TIMEOUT = connection_timeout;
        this.httpClient = httpClientFactory.createPooledConnectionHttpClient(connectionPoolMax, connectionPoolMaxPerRoute);
        this.useSecureConnection = useHttps;

    }

    @Override
    public boolean createMopAccount(Account account) {
        String content = getPostBody(account);
        String url = getCompleteUrlToCreateAccount(account.getName());
        boolean docSent = doPut(url, content);
        return docSent;
    }



    @Override
    public boolean addUserToMopAccount(UserAccount userAccount) {
        String content = getPostBody(userAccount);
        String url = getCompleteUrlToAddUserToAccount( userAccount.getName());
        boolean docSent = doPut(url, content);
        return docSent;
    }

    @Override
    public boolean createSubscriptionForTheUser(Subscriptions subscriptions) {
        String content = getPostBody(subscriptions);
        String url = getCompleteUrlToSubscriptions();
        return doPost(url,content);
    }

    @Override
    public boolean createTransferConfigurationForTheUser(TransferConfigurations transferConfigurations,String subId) {
        String content = getPostBody(transferConfigurations);
        String url = getCompleteUrlToTransferConfiguration(subId);
        boolean result = doPost(url,content);
        return result;
    }

    private String getCompleteUrlToTransferConfiguration(String subId) {

        StringBuilder builder = new StringBuilder(getBaseUrl(host,port,username,password));
        builder.append("/api/v1.1/subscriptions/")
                .append(subId).append("/transferConfigurations");
        return builder.toString();
    }


    @Override
    public String getSubscriptionIdForTheUser(String application, String accountName) {
        String url = getCompleteUrlToSubscriptions();
        StringBuilder builder = new StringBuilder(url);
        builder.append("?limit=1&application=").append(application)
                .append("&account=").append(accountName);
        String bodyResponse = doGet(builder.toString());
        Subscriptions subscriptions = Subscriptions.fromXML(bodyResponse);
        return subscriptions.getSubscription().getId();
    }


    private String getCompleteUrlToSubscriptions() {
        StringBuilder builder = new StringBuilder(getBaseUrl(host,port,username,password));
        builder.append("/api/v1.0/subscriptions");
        return builder.toString();
    }


    private String getCompleteUrlToAddUserToAccount( String accountUsername) {
        StringBuilder builder = new StringBuilder(getBaseUrlForAccounts(host, port, username, password));
         builder.append(accountUsername).append("/users/").append(accountUsername);
        return builder.toString();
    }

    private String getCompleteUrlToCreateAccount(String accountUsername) {
        StringBuilder builder = new StringBuilder(getBaseUrlForAccounts(host, port, username, password));
        builder .append(accountUsername);
        return builder.toString();
    }

    private String getBaseUrlForAccounts(String host, int port, String username, String password) {
        StringBuilder builder = new StringBuilder(getBaseUrl(host,port,username,password));
        builder.append("/api/v1.0/accounts/");
       return builder.toString();
    }

    private String getBaseUrl(String host,int port, String username, String password) {
        String typeConnection = useSecureConnection ? "https://" : "http://";

        StringBuilder builder = new StringBuilder(typeConnection);
        if(useSecureConnection) {
            builder.append(username)
                    .append(":").append(password).append("@");
        }
        builder.append(host).append(":").append(port);
        return builder.toString();
    }


    private String getPostBody(IToXML account) {
        StringBuilder builder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append(account.toXML());

        return builder.toString();
    }


    private boolean doHttpOpAndValuate(HttpEntityEnclosingRequestBase op, String content) {
        try {

           HttpActionResponse response = doHttpOp(op, content);

            int statusCode = response.getStatusCode();
            if (statusCode != HttpStatus.SC_CREATED && statusCode != HttpStatus.SC_OK) {
                logger.warn("Operation Failed {} Status {} Reason {} Content {}", statusCode, response.getReason(), content);
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

    private HttpActionResponse doHttpOp(HttpRequestBase op, String content) throws IOException {
        op.setHeader("Content-Type","application/xml");

        if(StringUtils.hasText(content)) {
            ((HttpEntityEnclosingRequestBase) op).setEntity(new StringEntity(content));
        }


        op.setConfig( RequestConfig.custom().setConnectTimeout(CONNECTION_TIMEOUT).setSocketTimeout(CONNECTION_TIMEOUT).build());

        HttpActionResponse response = submitHttpRequest(op);

        return response;
    }

    private boolean doPut(String url, String content) {
        return doHttpOpAndValuate(new HttpPut(url), content);
    }

    private boolean doPost(String url, String content) {
        return doHttpOpAndValuate(new HttpPost(url), content);
    }

    private String doGet(String url) {
        try {
            HttpActionResponse response = doHttpOp(new HttpGet(url),"");
            return response.getBody();
        } catch (IOException e) {
            logger.error("Unexpected IO Error", e);
        }
        return "";
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
