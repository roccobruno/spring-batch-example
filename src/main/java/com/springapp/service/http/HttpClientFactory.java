package com.springapp.service.http;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.*;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Creates HTTP Clients
 */
@Component
public class HttpClientFactory {

    private Logger logger = LoggerFactory.getLogger(HttpClientFactory.class);

    @Value("${mop.username}")
    private String connectionUsername;
    @Value("${mop.password}")
    private String connectionPassword;





    public CloseableHttpClient createPooledConnectionHttpClient(int maxTotalConnections, int maxConnectionsPerHost) {

        SSLContextBuilder builder = SSLContexts.custom();
        Registry<ConnectionSocketFactory> socketFactoryRegistry = null;
        try {
            builder.loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                    return true;
                }
            });

            SSLContext sslContext = builder.build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslContext, new X509HostnameVerifier() {
                @Override
                public void verify(String host, SSLSocket ssl)
                        throws IOException {
                }

                @Override
                public void verify(String host, X509Certificate cert)
                        throws SSLException {
                }

                @Override
                public void verify(String host, String[] cns,
                                   String[] subjectAlts) throws SSLException {
                }

                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });

          socketFactoryRegistry = RegistryBuilder
                    .<ConnectionSocketFactory> create().register("https", sslsf)
                    .build();

            PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            poolingHttpClientConnectionManager.setMaxTotal(maxTotalConnections);
            poolingHttpClientConnectionManager.setDefaultMaxPerRoute(maxConnectionsPerHost);

            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(
                    new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
                    new UsernamePasswordCredentials(connectionUsername, connectionPassword));

            return HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).setSSLSocketFactory(sslsf)
                    .setConnectionManager(poolingHttpClientConnectionManager).build();

        } catch (NoSuchAlgorithmException e) {
            logger.error("Error in creating HttpCLient Connection : {}",e);
        } catch (KeyStoreException e) {
            logger.error("Error in creating HttpCLient Connection : {}",e);
        } catch (KeyManagementException e) {
            logger.error("Error in creating HttpCLient Connection : {}",e);
        }


        return null;

    }

}
