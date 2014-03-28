package com.nextinstruction;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.client.apache.ApacheHttpClient;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.spi.client.ClientFactory;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class PersonTestJerseyTestIT extends JerseyTest {
    private long timeout = 1000L;

    public PersonTestJerseyTestIT() throws Exception {
        super("com.nextinstruction");
        if(System.getProperty("test.timeout") != null) {
            timeout = Long.parseLong(System.getProperty("test.timeout"));
        }
        //System.setProperty("javax.net.debug", "ssl");
        System.setProperty("javax.net.ssl.trustStore",
                ResourceUtils.getFile("classpath:certs/truststore_client.jks").toString());
        System.setProperty("javax.net.ssl.trustStorePassword", "secret");
        System.setProperty("javax.net.ssl.keyStore",
                ResourceUtils.getFile("classpath:certs/keystore_client.jks").toString());
        System.setProperty("javax.net.ssl.keyStorePassword", "secret");
        System.out.println(System.getProperty("javax.new.ss.trustStore"));
        long timeoutSecs = (timeout >= 1000)? timeout/1000 : timeout;
        System.out.println(String.format("sleeping for %d secs before starting test...",
                timeoutSecs));
        Thread.sleep(timeoutSecs * 1000);

    }

    @Override
    protected URI getBaseURI() {
        final URI uri = UriBuilder.fromUri("https://localhost:{port}").build(9443);
        System.out.println("BaseURI: "+uri);
        return uri;
    }

    @Test
    public void testQuotes() throws Exception {
        Client client = client();
        URI quotesURI = UriBuilder.fromUri(getBaseURI()).path("/quotes").build();
        String response = client.resource(quotesURI)
              .accept(MediaType.APPLICATION_JSON,
                      MediaType.APPLICATION_XML)
              .get(String.class);
        System.out.println(response);
    }

    @Override
    protected ClientFactory getClientFactory() {
        return new ClientFactory() {
            @Override
            public Client create(ClientConfig clientConfig) {
                return ApacheHttpClient.create();
            }
        };
    }
}
