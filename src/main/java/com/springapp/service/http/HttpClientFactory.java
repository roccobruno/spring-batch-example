package com.springapp.service.http;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Creates HTTP Clients
 */
@Component
public class HttpClientFactory {

    @Value("${mop.username}")
    private String connectionUsername;
    @Value("${mop.password}")
    private String connectionPassword;





    public CloseableHttpClient createPooledConnectionHttpClient(int maxTotalConnections, int maxConnectionsPerHost) {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(maxTotalConnections);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(maxConnectionsPerHost);

        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
                new UsernamePasswordCredentials(connectionUsername, connectionPassword));

        return HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider)
                .setConnectionManager(poolingHttpClientConnectionManager).build();
    }

}
